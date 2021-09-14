package views;

import repositories.*;
import services.*;

import java.util.Scanner;

public class CreationView {

    // necessary static objects
    public static Scanner scanner = new Scanner(System.in);
    private static InstrumentServices instrumentServices = new InstrumentServices();
    private static LoopServices loopServices = new LoopServices();
    private static UserRepo userRepo = new UserRepo();
    private static InstrumentRepo instrumentRepo = new InstrumentRepo();
    private static LoopRepo loopRepo = new LoopRepo();

    public static void creationLoop(String username) {

        Boolean running = true;

        // creation menu while loop
        while (running) {
            System.out.println("Please choose one of the following options:\n1. Select an Instrument\n2. Select a Loop\n3. Save & Quit");

            // taking a valid number
            int input3 = scanner.nextInt();

            // 3 switch cases that ask for instrument wanted, loop wanted, and then an exit case
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
                        else if (userRepo.getByUsername(username).getPrmSub() && instrumentRepo.getByInstrument(instrument).getPrmSub())
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
                        else if (userRepo.getByUsername(username).getPrmSub() && loopRepo.getByLoop(loop).getPrmSub())
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

                // default option in case the user input is invalid
                default: {
                    System.out.println("Invalid input. Must enter the value 1, 2, or 3");
                    running = false;
                    break;
                }
            }
        }
    }
}
