package views;

import repositories.*;
import services.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuView {

    // necessary static objects
    private static UserServices userServices = new UserServices();
    private static UserRepo userRepo = new UserRepo();

    public static void mainMenuLoop() {

        Boolean running = true;

        // while loop to continue looping the command line until user manually quits
        // this is the main menu while loop
        while (running) {

            // need to catch an important InputMismatchException using this try/catch
            try {

                Scanner scanner = new Scanner(System.in);

                System.out.println("Please choose one of the following options:\n1. Login\n2. Create an Account\n3. Quit");

                // taking a valid number
                int input = scanner.nextInt();

                // first switch statement with its 3 cases of user input
                switch (input) {
                    // case 1 asks for login
                    case 1: {
                        scanner.nextLine();
                        System.out.println("Please enter your username: ");
                        String username = scanner.nextLine();

                        System.out.println("Please enter your password: ");
                        String password = scanner.nextLine();

                        // checks to see if the username exists
                        boolean signInResponse = userServices.login(username, password);

                        if (signInResponse) {
                            System.out.println("Successfully logged in. Welcome " + username + ".");

                            // continues to the project loop after logging in
                            ProjectView.projectLoop(username);

                        } else { // if invalid, loop restarts and user input begins again
                            System.out.println("Username and/or password are invalid.");
                        }
                        break;
                    }

                    // case 2 asks for user credentials and desired membership plan
                    case 2: {
                        scanner.nextLine();

                        System.out.println("Please provide your new username: ");
                        String newUsername = scanner.nextLine();

                        System.out.println("Please provide your new password: ");
                        String newPassword = scanner.nextLine();

                        System.out.println("Will you subscribe for a Standard Paid membership? (y/n): ");
                        Boolean newStdSub = scanner.nextLine().equals("y");

                        System.out.println("Will you subscribe for a Premium Paid membership? (y/n): ");
                        Boolean newPrmSub = scanner.nextLine().equals("y");

                        userRepo.add(newUsername, newPassword, newStdSub, newPrmSub);
                        System.out.println("Account created successfully.");
                        break;
                    }

                    // case 3 exits application
                    case 3: {
                        System.out.println("Exiting application...");
                        running = false;
                        break;
                    }

                    // default option in case the user input is invalid
                    default: {
                        System.out.println("Invalid input. Must enter the value 1, 2, or 3");
                        break;
                    }
                }
            } catch (InputMismatchException e) { // this makes sure the user sees that input must be a number, and the application keeps running
                System.out.println("Value must contain only a number.");
            }
        }
    }
}
