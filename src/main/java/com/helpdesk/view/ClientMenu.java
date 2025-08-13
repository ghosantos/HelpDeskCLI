package com.helpdesk.view;

import com.helpdesk.model.Called;
import com.helpdesk.model.Client;
import com.helpdesk.model.enums.Priority;
import com.helpdesk.model.exceptions.DomainException;
import com.helpdesk.service.ServiceCalled;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.helpdesk.model.Called.FORMATTER;

public class ClientMenu {

    private final ServiceCalled serviceCalled;

    public ClientMenu(ServiceCalled serviceCalled) {
        this.serviceCalled = serviceCalled;
    }

    public void showClientMenu(Scanner sc) {
        String name;
        String email;
        Client client = null;

        while (true) {
            System.out.print("Digite seu nome: ");
            name = sc.nextLine();

            System.out.print("Digite seu e-mail: ");
            email = sc.nextLine();

            try {
                client = new Client(name, email);
                serviceCalled.addClient(client);
                System.out.println("Cliente adicionado com sucesso!");
                break;
            } catch (DomainException e) {
                System.out.println("\n" + e.getMessage());
            }
        }

        System.out.println("\nBem-vindo, " + name + "!");

        int option;
        do {
            System.out.print("""
                    \n--- MENU CLIENTE ---
                    1 - Abrir novo chamado
                    2 - Ver chamados em andamento/abertos
                    3 - Chamados finalizados
                    0 - Sair
                    """);
            option = readInt(sc, "Opção: ");

            switch (option) {
                case 1 -> {
                    System.out.print("\nDescrição do problema: ");
                    String description = sc.nextLine();

                    System.out.println();

                    Priority priority = readPriority(sc);

                    Called c = new Called(client, description, priority);
                    serviceCalled.addCalled(c);

                    System.out.println("\nChamado criado com sucesso!");
                    System.out.printf("ID do chamado: %d\nStatus: %s\nData de abertura: %s\n",
                            c.getId(), c.getStatus(), c.getOpeningDate().format(FORMATTER));
                }

                case 2 -> {
                    List<Called> openCalls = serviceCalled.getOpenAndProgressCalls();
                    if (!openCalls.isEmpty()) {
                        System.out.println("\n--- SEUS CHAMADOS EM ANDAMENTO/ABERTOS ---");
                        openCalls.forEach(System.out::println);
                    } else {
                        System.out.println("\nNão possui chamados abertos.");
                    }
                }

                case 3 -> {
                    List<Called> closedCalls = serviceCalled.closedCalls();
                    if (!closedCalls.isEmpty()) {
                        System.out.println("--- SEUS CHAMADOS FINALIZADOS ---");
                        closedCalls.forEach(System.out::println);
                    } else {
                        System.out.println("Não possui chamados finalizados.");
                    }
                }

                case 0 -> System.out.println("Retornando ao Menu Principal...\n");

                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    // ===== Auxiliary methods =====

    private int readInt(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("\nEntrada inválida. Digite um número: ");
                sc.nextLine();
            }
        }
    }

    private Priority readPriority(Scanner sc) {
        System.out.print("""
                Escolha a prioridade:
                1 - Baixa
                2 - Média
                3 - Alta
                """);

        while (true) {
            int p = readInt(sc, "Opção: ");
            switch (p) {
                case 1 -> {
                    return Priority.LOW;
                }
                case 2 ->{
                    return Priority.MEDIUM;
                }
                case 3 -> {
                    return Priority.HIGH;
                }
                default -> System.out.println("Opção inválida. Escolha 1, 2 ou 3.");
            }
        }
    }
}

