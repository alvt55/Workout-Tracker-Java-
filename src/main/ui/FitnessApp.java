package ui;

import model.GymExercise;
import model.GymSession;
import model.PersonalBest;
import model.WorkoutLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// app which uses user interactions and the WorkoutLog class
// NOTE: based on Teller App class given in Phase 1 Module
public class FitnessApp {
    private static final String JSON_STORE = "workoutlog.json"; // make file for this?


    private WorkoutLog log;
    private ArrayList<PersonalBest> personalBests;
    private ArrayList<GymSession> gymSessions;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


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
        if (command.equals("amount")) {
            System.out.println();
            doAmountOfGymSessions();
        } else if (command.equals("most")) {
            System.out.println();
            doMostWeightLifted();
        } else if (command.equals("pbs")) {
            System.out.println();
            doAllPersonalBests();
        } else if (command.equals("allgs")) {
            System.out.println();
            doAllGymSessions();
        } else if (command.equals("addpb")) {
            System.out.println();
            doAddPersonalBest();
        } else if (command.equals("addgs")) {
            System.out.println();
            doAddGymSession();
        } else if (command.equals("s")) {
            System.out.println();
            saveWorkoutLog();
        } else if (command.equals("l")) {
            System.out.println();
            loadWorkoutLog();
        } else {
            System.out.println();
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the workoutlog to file
    private void saveWorkoutLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(log);
            jsonWriter.close();
            System.out.println("Saved log to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workoutlog from file
    private void loadWorkoutLog() {
        try {
            log = jsonReader.read();
            System.out.println("Loaded log from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes objects
    private void start() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        personalBests = new ArrayList<PersonalBest>();
        gymSessions = new ArrayList<GymSession>();

        log = new WorkoutLog(personalBests, gymSessions);


        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tamount -> number of gym sessions");
        System.out.println("\tmost -> most weight lifted in a session");
        System.out.println("\tpbs -> list of all personal bests");
        System.out.println("\tallgs -> list of all gym sessions");
        System.out.println("-------------------------------------");
        System.out.println("\taddpb -> add a personal best");
        System.out.println("\taddgs -> add a gymSession");
        System.out.println("\ts -> save log state");
        System.out.println("\tl -> load previous state");
        System.out.println("\tq -> quit");
        // add here

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




