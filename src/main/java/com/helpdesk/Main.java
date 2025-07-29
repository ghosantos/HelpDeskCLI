package com.helpdesk;

import com.helpdesk.view.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu();
        menu.showMenu(sc);
    }
}