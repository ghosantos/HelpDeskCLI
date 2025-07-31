package com.helpdesk.view;

import com.helpdesk.model.Called;
import com.helpdesk.model.Technical;
import com.helpdesk.model.enums.AssignCallResult;
import com.helpdesk.service.ServiceCalled;

import java.util.List;
import java.util.Scanner;

import static com.helpdesk.model.Called.FORMATTER;

public class TechnicalMenu {

    private final ServiceCalled serviceCalled;

    public TechnicalMenu(ServiceCalled serviceCalled) {
        this.serviceCalled = serviceCalled;
    }

    public void showTechnical(Scanner sc){
        System.out.print("Digite seu nome: ");
        String name = sc.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = sc.next();

        System.out.println();
        sc.nextLine();

        Technical newTechnical = new Technical(name, email);
        serviceCalled.addTechnical(newTechnical);

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

            System.out.println();

            switch (option){
                case 1 ->{
                    List<Called> openCalls = serviceCalled.getOpenCalls();

                    if (!openCalls.isEmpty()){
                        System.out.println("--- SEUS CHAMADOS ABERTOS ---");
                        openCalls.forEach(System.out::println);
                    }else {
                        System.out.println("Não possuí chamados abertos.");
                    }
                    System.out.println();
                }

                case 2 ->{
                    System.out.print("Digite o ID do chamado que deseja assumir: ");
                    long id = sc.nextInt();

                    AssignCallResult result = serviceCalled.assignCall(id, newTechnical);
                    System.out.println(result);
                    System.out.println();
                }

                case 3 ->{
                    List<Called> progressCalls = serviceCalled.getProgressCalls();
                    if (!progressCalls.isEmpty()){
                        System.out.println("--- SEUS CHAMADOS EM ANDAMENTO ---");
                        progressCalls.forEach(System.out::println);
                        System.out.println();
                    }else System.out.println("Não possuí chamados em_andamento.\n");
                }

                case 4 ->{
                    System.out.print("Digite o ID do chamado: ");
                    long id = sc.nextInt();
                    sc.nextLine();

                    Called called = serviceCalled.getCallById(id);

                    if (called != null){
                        System.out.println("\nChamado encontrado!\n" + called + "\n");
                    }else System.out.println("ID inexistente.\n");
                }

                case 5 ->{
                    System.out.print("Digite o ID do chamado que deseja encerrar: ");
                    long id = sc.nextInt();
                    sc.nextLine();

                    Called called = serviceCalled.getCallById(id);

                    if (serviceCalled.closeCall(id)){
                        System.out.println("Chamado finalizado com sucesso!");
                        System.out.println("Data de fechamento: " + called.getClosedDate().format(FORMATTER) + "\n");
                    }else {
                        System.out.println("O chamado não pode ser finalizado antes de ser atribuído a um técnico.\n");
                    }
                }

                case 0 -> {
                    System.out.println("Retornando ao Menu Principal...\n");
                    return;
                }
            }

        }while (true);



    }
}
