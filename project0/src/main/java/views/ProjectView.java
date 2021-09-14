package views;

import models.UPJoin;
import repositories.*;
import services.*;

import java.util.List;
import java.util.Scanner;

public class ProjectView {

    // necessary static objects
    public static Scanner scanner = new Scanner(System.in);
    private static ProjectServices projectServices = new ProjectServices();
    private static UserRepo userRepo = new UserRepo();
    private static ProjectRepo projectRepo = new ProjectRepo();
    private static UPJoinRepo upJoinRepo = new UPJoinRepo();

    public static void projectLoop(String username) {

        Boolean running = true;

        // while loop for the project menu
        while (running) {
            System.out.println("Please choose one of the following options:\n1. Open Project\n2. Create New Project\n3. Logout");

            // taking a valid number
            int input2 = scanner.nextInt();

            // 3 cases here that seek out a project, create a project, or asks you to exit
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

                        // continue to the projects' creation loop
                        CreationView.creationLoop(username);

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
                    running = false;
                    break;
                }

                // default option in case the user input is invalid
                default: {
                    System.out.println("Invalid input. Must enter the value 1, 2, or 3");
                    break;
                }
            }
        }
    }
}
