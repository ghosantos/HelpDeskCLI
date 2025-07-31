package com.helpdesk.view;

import com.helpdesk.model.Called;
import com.helpdesk.model.User;
import com.helpdesk.service.ServiceCalled;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<User> userList = new ArrayList<>();
    List<Called> calledList = new ArrayList<>();

    ServiceCalled serviceCalled = new ServiceCalled(userList, calledList);

    ClientMenu clientMenu = new ClientMenu(serviceCalled);
    TechnicalMenu technicalMenu = new TechnicalMenu(serviceCalled);


    public void showMenu(Scanner sc){
        int option;
        do {
            System.out.print("""
                        ==== SISTEMA DE CHAMADOS ====
                        1 - Entrar como CLIENTE
                        2 - Entrar como TÉCNICO
                        0 - Sair
                        """);
            System.out.print("Opção: ");
            option = sc.nextInt();

            System.out.println();
            sc.nextLine(); // Clear buffer

            switch (option){
                case 1 -> {
                    clientMenu.showClientMenu(sc);
                }
                case 2 -> {
                    technicalMenu.showTechnical(sc);
                }
                case 0 -> {
                    System.out.println("Encerrando o programa...");
                    return;
                }
            }
        }while (true);
    }

}