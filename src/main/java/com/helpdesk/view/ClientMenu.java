package com.helpdesk.view;

import com.helpdesk.model.Called;
import com.helpdesk.model.Client;
import com.helpdesk.model.enums.Priority;
import com.helpdesk.service.ServiceCalled;

import java.util.List;
import java.util.Scanner;

import static com.helpdesk.model.Called.FORMATTER;

public class ClientMenu {

    private final ServiceCalled serviceCalled;

    public ClientMenu(ServiceCalled serviceCalled) {
        this.serviceCalled = serviceCalled;
    }

    public void showClientMenu(Scanner sc){
        System.out.print("Digite seu nome: ");
        String name = sc.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = sc.next();

        System.out.println();
        sc.nextLine();

        Client newClient = new Client(name, email);
        serviceCalled.addClient(newClient);

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
                    System.out.println();

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
                    serviceCalled.addCalled(c);

                    System.out.println(); //Line break
                    System.out.println("Chamado criado com sucesso!");
                    System.out.printf("ID do chamado: %d\nStatus: %s\nData de abertura: %s", c.getId(), c.getStatus(), c.getOpeningDate().format(FORMATTER) + "\n");
                    break;
                }

                case 2 ->{
                    List<Called> openCalls = serviceCalled.getOpenAndProgressCalls();

                    if (!openCalls.isEmpty()){
                        System.out.println("--- SEUS CHAMADOS EM_ANDAMENTO/ABERTOS ---");
                        openCalls.forEach(System.out::println);
                    }else {
                        System.out.println("Não possuí chamados abertos.");
                    }
                    System.out.println();
                    break;
                }

                case 3 ->{
                    List<Called> closedCalls = serviceCalled.closedCalls();
                    if (!closedCalls.isEmpty()){
                        System.out.println("--- SEUS CHAMADOS FINALIZADOS ---");
                        closedCalls.forEach(System.out::println);
                    }else System.out.println("Não possuí chamados finalizados.");
                }

                case 0 ->{
                    System.out.println("Retornando ao Menu Principal...\n");
                    return;
                }
            }
        }while (true);

    }

}
