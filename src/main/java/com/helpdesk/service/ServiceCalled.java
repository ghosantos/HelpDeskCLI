package com.helpdesk.service;

import com.helpdesk.entities.Called;
import com.helpdesk.entities.Client;
import com.helpdesk.entities.Technical;
import com.helpdesk.entities.User;
import com.helpdesk.enums.Priority;
import com.helpdesk.enums.StatusCalled;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceCalled {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final List<User> listUsers;
    private final List<Called> listCalleds;

    public ServiceCalled() {
        this.listUsers = new ArrayList<>();
        this.listCalleds = new ArrayList<>();
    }

    public void clientMenu(Scanner sc){
        System.out.print("Digite seu nome: ");
        String name = sc.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = sc.next();

        System.out.println(); //Line break
        sc.nextLine(); //Clear buffer

        Client newClient = new Client(name, email);
        this.listUsers.add(newClient);

        System.out.println("Bem-vindo, " + name + "!");
        int option;
        do {
            System.out.print("""
                    \n--- MENU CLIENTE ---
                    1 - Abrir novo chamado
                    2 - Ver chamados em andamento/abertos
                    3 - Chamados finalizados
                    0 - Sair
                    """);
            System.out.print("Opção: ");
            option = sc.nextInt();

            System.out.println();

            sc.nextLine();
            switch (option){
                case 1 ->{
                    System.out.print("Descrição do problema: ");
                    String description = sc.nextLine();
                    System.out.println(); //Line break

                    System.out.print("""
                            Escolha a prioridade:
                            1 - Baixa
                            2 - Média
                            3 - Alta
                            Opção:""");

                    int p = sc.nextInt();
                    sc.nextLine();

                    Priority priority;
                    switch (p) {
                        case 1 -> priority = Priority.LOW;
                        case 2 -> priority = Priority.MEDIUM;
                        case 3 -> priority = Priority.HIGH;
                        default -> {
                            System.out.println("Opção inválida de prioridade.");
                            return;
                        }
                    }

                    Called c = new Called(newClient, description, priority);
                    listCalleds.add(c);

                    System.out.println(); //Line break
                    System.out.println("Chamado criado com sucesso!");
                    System.out.printf("ID do chamado: %d\nStatus: %s\nData de abertura: %s", c.getId(), c.getStatus(), c.getOpeningDate().format(FORMATTER) + "\n");
                    break;
                }

                case 2 ->{
                    boolean hasOpen = false;

                    System.out.println("--- SEUS CHAMADOS EM_ANDAMENTO/ABERTOS ---");
                    for (Called c : listCalleds){
                        if (c.getStatus().equals(StatusCalled.OPEN) || c.getStatus().equals(StatusCalled.PROGRESS)){
                            System.out.println(c);
                            hasOpen = true;
                        }
                    }

                    if (!hasOpen){
                        System.out.println("Não possuí chamados EM_ANDAMENTO/ABERTOS.");
                    }
                }

                case 3 ->{
                    boolean hasClosed = false;

                    System.out.println("--- SEUS CHAMADOS FINALIZADOS ---");
                    for (Called c : listCalleds){
                        if (c.getStatus().equals(StatusCalled.CLOSED)){
                            System.out.println(c);
                            hasClosed = true;
                        }
                    }

                    if (!hasClosed){
                        System.out.println("Não possuí chamados finalizados.");
                    }
                }
            }

        }while (option != 0);
    }

    public void technicalMenu(Scanner sc){
        System.out.print("Digite seu nome: ");
        String name = sc.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = sc.next();

        System.out.println(); //Line break
        sc.nextLine(); //Clear buffer

        Technical technical = new Technical(name, email);
        this.listUsers.add(technical);

        System.out.println("Bem-vindo, " + name + "!\n");
        int option;
        do {

            System.out.print("""
                    --- MENU TÉCNICO ---
                    1 - Ver chamados abertos
                    2 - Atribuir-se a um chamado
                    3 - Meus chamados em andamento
                    4 - Buscar chamado por ID
                    5 - Fechar um chamado
                    0 - Sair
                    """);
                System.out.print("Opção: ");
                option = sc.nextInt();

                System.out.println(); //Break line

                switch (option){
                    case 1 ->{
                        boolean foundOpen = false;

                        System.out.println("--- CHAMADOS ABERTOS ---");
                        for (Called c : listCalleds) {
                            if (c.getStatus().equals(StatusCalled.OPEN)) {
                                System.out.println(c);
                                foundOpen = true;
                            }
                        }

                        System.out.println(); //Break line

                        if (!foundOpen) {
                            System.out.println("Não possui chamados abertos.\n");
                        }

                        break;
                    }

                    case 2 ->{
                        System.out.print("Digite o ID do chamado que deseja assumir: ");
                        int id = sc.nextInt();

                        Called called = findCall(id);

                        if (called != null){
                            called.assignTechnician(technical);
                            System.out.println("Chamado atribuído com sucesso.");
                            System.out.println("Status atualizado para: EM_ANDAMENTO\n");
                        }

                        break;
                    }

                    case 3 ->{
                        boolean hasProgress = false;

                        System.out.println("--- SEUS CHAMADOS EM ANDAMENTO ---");
                        for (Called c : listCalleds){
                            if (c.getStatus().equals(StatusCalled.PROGRESS)) {
                                System.out.println(c);
                                hasProgress = true;
                            }
                        }

                        System.out.println(); // Break line

                        if (!hasProgress){
                            System.out.println("Você não possuí chamados em andamento.\n");
                        }
                        break;
                    }

                    case 4 ->{
                        System.out.print("Digite o ID do chamado: ");
                        int id = sc.nextInt();
                        sc.nextLine(); //Clear buffer

                        Called called = findCall(id);
                        if (called != null){
                            System.out.println("Chamado encontrado!\n" + called + "\n");
                        }else System.out.println("ID inexistente.\n");
                    }

                    case 5 ->{
                        System.out.print("Digite o ID do chamado que deseja encerrar: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // Clear buffer

                        Called called = findCall(id);

                        if (called != null) {
                            called.closeTicket();
                            System.out.println("Chamado finalizado com sucesso!");
                            System.out.println("Data de fechamento: " + called.getClosedDate().format(FORMATTER) + "\n");
                        } else {
                            System.out.println("ID ou chamado inexistente.\n");
                        }
                        break;
                    }
                }

        }while (option != 0);


    }

    public Called findCall(int id){
        return listCalleds.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }


}
