package ui;

import model.GymExercise;
import model.GymSession;
import model.PersonalBest;
import model.WorkoutLog;

import java.util.ArrayList;
import java.util.Scanner;

// app which uses user interactions and the WorkoutLog class
public class FitnessApp {
//n

    private WorkoutLog log;
    private ArrayList<PersonalBest> personalBests;
    private ArrayList<GymSession> gymSessions;
    private Scanner input;


    // EFFECTS: runs the teller application
    public FitnessApp() {
        runFitnessApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFitnessApp() {
        boolean keepGoing = true;
        String command = null;

        start();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAmountOfGymSessions();
        } else if (command.equals("m")) {
            doMostWeightLifted();
        } else if (command.equals("p")) {
            doAllPersonalBests();
        } else if (command.equals("g")) {
            doAllGymSessions();
        } else if (command.equals("b")) {
            doAddPersonalBest();
        } else if (command.equals("gs")) {
            doAddGymSession();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes objects
    private void start() {

        personalBests = new ArrayList<PersonalBest>();
        gymSessions = new ArrayList<GymSession>();

        log = new WorkoutLog(personalBests, gymSessions);


        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> number of gym sessions");
        System.out.println("\tm -> most weight lifted in a session");
        System.out.println("\tp -> list of all personal bests");
        System.out.println("\tg -> list of all gym sessions");
        System.out.println("\tb -> add a personal best");
        System.out.println("\tgs -> add a gymSession");
    }

    // EFFECTS: displays int amount of total gym sessions
    private void doAmountOfGymSessions() {
        System.out.println(log.amountOfGymSessions());
    }

    // EFFECTS: displays most weight lifted for gym sessions
    private void doMostWeightLifted() {
        System.out.println(log.mostWeightLifted());
    }

    // EFFECTS: displays all personal best s
    private void doAllPersonalBests() {
        System.out.println(log.allPersonalBests());
    }

    // EFFECTS: displays all gym sessions
    private void doAllGymSessions() {
        System.out.println(log.allGymSessions());
    }

    // REQUIRES: date is in day month, year format, weight is > 0
    // MODIFIES: this
    // EFFECTS: adds a personal best
    private void doAddPersonalBest() {
        System.out.println("Enter the exercise: ");
        input.nextLine();
        String name = input.nextLine();

        System.out.println("Enter the date: ");
        String date = input.nextLine();
        System.out.println("Enter the weight in lbs: ");
        int weight = input.nextInt();

        log.addPersonalBest(new PersonalBest(name, date, weight));

    }


    // REQUIRES: date is in day month, year format, weight, sets, reps are all > 0
    // MODIFIES: this
    // EFFECTS: adds a new session
    private void doAddGymSession() {
        System.out.println("Enter the date: ");
        input.nextLine();
        String date = input.nextLine();


        ArrayList<GymExercise> listOfGymExercise = new ArrayList<GymExercise>();

        while (true) {

            doAddExercise(listOfGymExercise);
            System.out.println("k -> keep adding exercises, e -> done");
            String loop = input.next();
            if (loop.equals("e")) {
                break;
            }
        }
        log.addGymSession(new GymSession(listOfGymExercise, date));


    }


    // REQUIRES: date is in day month, year format, weight, sets, reps are all > 0
    // MODIFIES: this
    // EFFECTS: adds exercise to list of exercises for gym session
    private void doAddExercise(ArrayList<GymExercise> list) {
        System.out.println("Enter the exercise ");
        String name = input.next();
        System.out.println();
        System.out.println("Enter the weight (0 if body weight) ");
        int weight = input.nextInt();
        System.out.println("Enter the bodyweight (0 if weights) ");
        int bodyWeight = input.nextInt();
        System.out.println("Enter the amount of sets ");
        int sets = input.nextInt();
        System.out.println("Enter the amount of reps ");
        int reps = input.nextInt();
        list.add(new GymExercise(name, weight, bodyWeight, sets, reps));
    }

}




