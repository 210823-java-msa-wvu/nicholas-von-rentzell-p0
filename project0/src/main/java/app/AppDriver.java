package app;

import views.MainMenuView;

import java.util.Scanner;

public class AppDriver {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome.");

        MainMenuView.mainMenuLoop();

        scanner.close();
    }
}
