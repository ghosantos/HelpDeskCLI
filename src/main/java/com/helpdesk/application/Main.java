package com.helpdesk.application;

import com.helpdesk.service.ServiceCalled;

import java.util.Scanner;

public class Main {
    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);

        ServiceCalled serviceCalled = new ServiceCalled();

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
                    serviceCalled.clientMenu(sc);
                }
                case 2 -> {
                    serviceCalled.technicalMenu(sc);
                }
                case 0 -> {
                    System.out.println("Encerrando o programa...");
                    return;
                }
            }
        }while (true);
    }
}