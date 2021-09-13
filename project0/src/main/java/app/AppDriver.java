package app;

import models.UPJoin;
import repositories.*;
import services.InstrumentServices;
import services.LoopServices;
import services.ProjectServices;
import services.UserServices;

import java.util.List;
import java.util.Scanner;

public class AppDriver {

    // several static variables to use the different services and repos throughout the application's execution
    public static Scanner scanner = new Scanner(System.in);

    private static UserServices userServices = new UserServices();

    private static ProjectServices projectServices = new ProjectServices();

    private static InstrumentServices instrumentServices = new InstrumentServices();

    private static LoopServices loopServices = new LoopServices();

    private static UserRepo userRepo = new UserRepo();

    private static ProjectRepo projectRepo = new ProjectRepo();

    private static InstrumentRepo instrumentRepo = new InstrumentRepo();

    private static LoopRepo loopRepo = new LoopRepo();

    private static UPJoinRepo upJoinRepo = new UPJoinRepo();

    public static void main(String[] args) {

        System.out.println("Welcome.");

        // while loop to continue looping the command line until user manually quits
        // this is the user while loop
        while (true) {
            System.out.println("Please choose one of the following options:\n1. Login\\n2. Create an Account\\n3. Quit\"");

            // setting up the scanner for user input
            int input = scanner.nextInt();

            // first switch statement with its 3 cases of user input (need defaults)
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

                        // continues to the projects while loop that will go until the user quits manually
                        while (true) {
                            System.out.println("Please choose one of the following options:\n1. Open Project\n2.Create New Project\n3. Logout");
                            int input2 = scanner.nextInt();

                            // 3 cases here that seek out a project, create a project, or asks you to exit (need defaults)
                            switch (input2) {

                                // case 1 asks for a project name, continuing forward into the project if it exists
                                case 1: {
                                    scanner.nextLine();

                                    System.out.println("Please enter your project name: ");
                                    String pName = scanner.nextLine();

                                    // checks to see if the project exists
                                    boolean projectExist = projectServices.projectExist(pName);

                                    // if the project exists, we enter the next code block within the project
                                    if (projectExist) {
                                        System.out.println("Entering project...");

                                        // continue to the instruments & loops while loop that will go until the user quits manually
                                        while (true) {
                                            System.out.println("Please choose one of the following options:\n1. Select an Instrument\n2. Select a Loop\n3.Save & Quit");
                                            int input3 = scanner.nextInt();

                                            // 3 switch cases that ask for instrument wanted, loop wanted, and then an exit case (need default)
                                            switch (input3) {

                                                // case 1 asks for an instrument to use based on if the user has the permission to use it and if it's in the database
                                                case 1: {
                                                    scanner.nextLine();

                                                    System.out.println("Which instrument would you like to use?: ");
                                                    String instrument = scanner.nextLine();

                                                    // first, must check if the instrument exists
                                                    boolean instExist = instrumentServices.instExist(instrument.toLowerCase());

                                                    // after seeing that it exists, we need to run the permissions check
                                                    if (instExist) {

                                                        // this if returns true when the instrument requested requires no subscription, and the user has any subscriptions
                                                        if (!(instrumentRepo.getByInstrument(instrument).getStdSub() || instrumentRepo.getByInstrument(instrument).getPrmSub()))
                                                            System.out.println(instrument + " has been added to your project.");

                                                        // this if returns true when the instrument requested requires a standard subscription, and the user and either standard or premium subscription
                                                        else if ((userRepo.getByUsername(username).getPrmSub() || userRepo.getByUsername(username).getStdSub()) && instrumentRepo.getByInstrument(instrument).getStdSub())
                                                            System.out.println(instrument + " has been added to your project.");

                                                        // this if returns true when the instrument requested requires a premium subscription, and th user has a premium subscription
                                                        else if (userRepo.getByUsername(username).getPrmSub() && instrumentRepo.getByInstrument(instrument).getStdSub())
                                                            System.out.println(instrument + " has been added to your project.");

                                                        // this returns if the user does not have permissions for the requested instrument
                                                        else
                                                            System.out.println("You do not have permission to use this instrument");
                                                    } else { // if the instrument does not exist, this returns
                                                        System.out.println(instrument + " does not exist in our database.");
                                                    }
                                                    break;
                                                }

                                                // case 2 asks for a loop to use based on if the user has the permission to use it and if it's in the database
                                                case 2: {
                                                    scanner.nextLine();

                                                    System.out.println("Which loop would you like to use?: ");
                                                    String loop = scanner.nextLine();

                                                    // first, must check if the loop exists
                                                    boolean loopExist = loopServices.loopExist(loop.toLowerCase());

                                                    // after seeing that it exists, we need to run the permissions check
                                                    if (loopExist) {

                                                        // this if returns true when the loop requested requires no subscription, and the user has any subscriptions
                                                        if (!(loopRepo.getByLoop(loop).getStdSub() || loopRepo.getByLoop(loop).getPrmSub()))
                                                            System.out.println(loop + " has been added to your project.");

                                                            // this if returns true when the loop requested requires a standard subscription, and the user and either standard or premium subscription
                                                        else if ((userRepo.getByUsername(username).getPrmSub() || userRepo.getByUsername(username).getStdSub()) && loopRepo.getByLoop(loop).getStdSub())
                                                            System.out.println(loop + " has been added to your project.");

                                                            // this if returns true when the loop requested requires a premium subscription, and the user has a premium subscription
                                                        else if (userRepo.getByUsername(username).getPrmSub() && loopRepo.getByLoop(loop).getStdSub())
                                                            System.out.println(loop + " has been added to your project.");

                                                            // this returns if the user does not have permissions for the requested loop
                                                        else
                                                            System.out.println("You do not have permission to use this loop");
                                                    } else { // if the loop does not exist, this returns
                                                        System.out.println(loop + " does not exist in our database.");
                                                    }
                                                    break;
                                                }

                                                // case 3 displays that the project has saved and will exit
                                                case 3: {
                                                    System.out.println("Project saved successfully. Exiting project...");
                                                    break;
                                                }
                                            }

                                            // following after case 3, the while loop will manually break out and return to the project loop
                                            if (input3 == 3)
                                                break;
                                        }
                                    } else { // if project does not exist, the case will break and the project loop will start again
                                        System.out.println(pName + " does not exist.");
                                    }
                                    break;
                                }

                                // case 2 creates a new project
                                case 2: {
                                    scanner.nextLine();

                                    // we need to query by username to see how many project is under that name
                                    List<UPJoin> userProjects = upJoinRepo.getAll(username);

                                    // after querying, we need to make sure a subscriber still has project space
                                    if (!(userRepo.getByUsername(username).getPrmSub() || (userRepo.getByUsername(username).getStdSub() && userProjects.size() < 20) || userProjects.size() < 5)) {
                                        System.out.println("You are out of project space.");
                                        break;
                                    }

                                    // continues to ask for a title if user has project space
                                    System.out.println("Please title your new project: ");
                                    String pName = scanner.nextLine();

                                    projectRepo.add(pName, userProjects.size() + 1, username);
                                    System.out.println("New project created.");
                                    break;
                                }

                                // case 3 exits the project loop
                                case 3: {
                                    System.out.println("Logging out...");
                                    break;
                                }
                            }

                            // input2 exists for this loop
                            if (input2 == 3)
                                break;
                        }
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
                    break;
                }

            }

            // when user input is 3, which executes the exiting case, the loop will break due to this statement
            if (input == 3)
                break;
        }
        scanner.close();
    }
}
