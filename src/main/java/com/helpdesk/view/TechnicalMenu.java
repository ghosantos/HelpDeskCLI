package com.helpdesk.view;

import com.helpdesk.model.Called;
import com.helpdesk.model.Technical;
import com.helpdesk.model.enums.AssignCallResult;
import com.helpdesk.model.exceptions.DomainException;
import com.helpdesk.service.ServiceCalled;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.helpdesk.model.Called.FORMATTER;

public class TechnicalMenu {

    private final ServiceCalled serviceCalled;

    public TechnicalMenu(ServiceCalled serviceCalled) {
        this.serviceCalled = serviceCalled;
    }

    public void showTechnical(Scanner sc) {
        String name;
        String email;
        Technical technical = null;

        while (true){
            System.out.print("Digite seu nome: ");
            name = sc.nextLine();

            System.out.print("Digite seu e-mail: ");
            email = sc.next();

            try{
                technical = new Technical(name, email);
                serviceCalled.addTechnical(technical);
                System.out.println("Técnico adicionado com sucesso!\n");
                break;
            }catch (DomainException e){
                System.out.println(e.getMessage() + "\n");
                sc.nextLine();
            }

        }

        System.out.println("Bem-vindo, " + name + "!\n");
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
            int option = readInt(sc, "Opção: ");

            System.out.println();

            switch (option) {
                case 1 -> {
                    List<Called> openCalls = serviceCalled.getOpenCalls();

                    if (!openCalls.isEmpty()) {
                        System.out.println("--- SEUS CHAMADOS ABERTOS ---");
                        openCalls.forEach(System.out::println);
                    } else {
                        System.out.println("Não possuí chamados abertos.");
                    }

                    System.out.println();
                }

                case 2 -> {
                    long id = readInt(sc, "Digite o ID do chamado:  ");

                    AssignCallResult result = serviceCalled.assignCall(id, technical);
                    System.out.println("Status: " + result + "\n");
                }

                case 3 -> {
                    List<Called> progressCalls = serviceCalled.getProgressCalls();
                    if (!progressCalls.isEmpty()) {
                        System.out.println("--- SEUS CHAMADOS EM ANDAMENTO ---");
                        progressCalls.forEach(System.out::println);
                        System.out.println();
                    } else System.out.println("Não possuí chamados em_andamento.\n");
                }

                case 4 -> {
                    long id = readInt(sc, "Digite o ID do chamado: ");

                    Called called = serviceCalled.getCallById(id);

                    if (called != null) {
                        System.out.println("\nChamado encontrado!\n" + called + "\n");
                    } else System.out.println("ID inexistente.\n");
                }

                case 5 -> {
                    long id = readInt(sc, "Digite o ID do chamado: ");
                    Called c = serviceCalled.getCallById(id);

                    try {
                        if (serviceCalled.closeCall(id)) {
                            System.out.println("Chamado finalizado com sucesso!");
                            System.out.println("Data de fechamento: " + c.getClosedDate().format(FORMATTER) + "\n");
                        } else {
                            System.out.println("O chamado não pode ser finalizado antes de ser atribuído a um técnico.\n");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 0 -> {
                    System.out.println("Retornando ao Menu Principal...\n");
                    return;
                }
            }
        }while (true) ;
    }

    // ===== Auxiliary methods =====

    public int readInt(Scanner sc, String message){
        int value;
        while (true){
            System.out.print(message);
            try {
                value = sc.nextInt();
                return value;
            }
            catch (InputMismatchException e){
                System.out.println("\nEntrada inválida. Digite um número.");
                sc.nextLine();
            }
        }
    }
}
