package ui;

import model.GymExercise;
import model.GymSession;
import model.PersonalBest;
import model.WorkoutLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// JFrame for adding new gym sessions
public class GymSessionFrame extends JFrame implements ActionListener {

    // initialize workout log
    ArrayList<GymSession> gymSessions;
    GymSession currSession;
    ArrayList<PersonalBest> pbs;
    WorkoutLog log;
    private String currDate;
    private String currName;
    private int currWeight;
    private int currBodyWeight;
    private int currSets;
    private int currReps;

    // Frames and Panels
    private JFrame frame;
    private JPanel entryPanel;
    private JPanel viewPanel;

    // Date page
    private JLabel dateLabel;
    private JTextField dateTextField;
    private JButton dateButton;

    private JButton mostWeightButton;
    private String mostWeight;


    // Exercise Page
    private JLabel exerciseNameLabel;
    private JTextField nameTextField;
    private JLabel weightLabel;
    private JTextField weightTextField;
    private JLabel bodyWeightLabel;
    private JTextField bodyWeightTextField;

    private JLabel repLabel;
    private JComboBox repBox;
    private JComboBox setBox;

    private JButton submitExerciseButton;
    private JButton doneAddingButton;

    // Viewing
    private JTextArea mostWeightText;
    private JTextArea sessionText;

    // image
    ImageIcon arcImg;
    JLabel display;

    // Json
    private static final String JSON_STORE = "data/workoutlog.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton saveButton;
    private JButton loadButton;



    // EFFECTS: initializes some fields, calls gym setup for panels
    public GymSessionFrame() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        gymSessions = new ArrayList<GymSession>();
        pbs = new ArrayList<PersonalBest>();
        log = new WorkoutLog(pbs, gymSessions);
        gymSetup();
        frame.setVisible(true);
    }


    // EFFECTS: sets up view panel and entry panel, also creates JFrame
    public void gymSetup() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setViewPanel();
        setEntryPanel();
        frame.setVisible(true);
    }

    // EFFECTS: starts menu "loop" for entry panel inputs
    public void setEntryPanel() {
        entryPanel = new JPanel();
        entryPanel.setBounds(0, 0, 400, 600);
        entryPanel.setBackground(Color.gray);
        frame.add(entryPanel);
        entryPanel.setLayout(null);

//        arcImg = new ImageIcon("arc-image.jpg");
//        display = new JLabel(arcImg);
//        display.setBounds(0, 300, 200, 200);
//        entryPanel.add(display);

        setDateInput();
        setJsonButtons();

    }

    // EFFECTS: initializes values for viewPanel
    public void setViewPanel() {
        viewPanel = new JPanel();
        viewPanel.setBounds(400, 0, 400, 600);
        viewPanel.setBackground(Color.lightGray);
        frame.add(viewPanel);

        updateViewSessions();


    }


    // MODIFIES: this
    // EFFECTS: updates GymSessions on view panel
    public void updateViewSessions() {
        sessionText = new JTextArea();
        sessionText.setBounds(400, 0, 400, 600);
        sessionText.setText(log.allGymSessions());
        viewPanel.add(sessionText);
    }

    // REQUIRES: dateTextField is a String
    // MODIFIES: this
    // EFFECTS: sets up all values and inputs on the date screen
    public void setDateInput() {

        // label and text field
        dateLabel = new JLabel("Date"); // label
        dateLabel.setBounds(10, 20, 80, 25);
        entryPanel.add(dateLabel);
        dateTextField = new JTextField(); // textbox
        dateTextField.setBounds(100, 20, 100, 25);
        entryPanel.add(dateTextField);

        // next button
        dateButton = new JButton("Next");
        dateButton.setBounds(10, 160, 160, 25);
        entryPanel.add(dateButton);
        dateButton.addActionListener(this);

        // display most weight button
        mostWeightButton = new JButton("Most Weight Lifted");
        mostWeightButton.setBounds(10, 190, 180, 25);
        entryPanel.add(mostWeightButton);
        mostWeightButton.addActionListener(this);

        mostWeightText = new JTextArea(10, 3); // label
        mostWeightText.setBounds(10, 230, 300, 80);
        entryPanel.add(mostWeightText);



    }

    public void setJsonButtons() {
        saveButton = new JButton("Save");
        saveButton.setBounds(10, 400, 160, 25);
        entryPanel.add(saveButton);
        saveButton.addActionListener(this);

        loadButton = new JButton("Load");
        loadButton.setBounds(10, 450, 160, 25);
        entryPanel.add(loadButton);
        loadButton.addActionListener(this);
    }


    // EFFECTS: calls methods for screen when inputting exercises
    private void setExerciseInput() {
        setExerciseNameInput();
        setWeightInput();
        setRepSetInput();
        exerciseButtons();
        frame.setVisible(true);

    }

    // REQUIRES: nameTextField is a String
    // MODIFIES: this
    // EFFECTS: sets up text and text fields for exercise name
    public void setExerciseNameInput() {
        exerciseNameLabel = new JLabel("Name"); // label
        exerciseNameLabel.setBounds(10, 50, 80, 25);
        entryPanel.add(exerciseNameLabel);

        nameTextField = new JTextField(); // textbox
        nameTextField.setBounds(130, 50, 165, 25);
        entryPanel.add(nameTextField);
    }

    // REQUIRES: weightTextField and bodyWeightTextField are both int
    // MODIFIES: this
    // EFFECTS: sets up input/labels for weight/bodyweight values
    public void setWeightInput() {
        weightLabel = new JLabel("Weight"); // label
        weightLabel.setBounds(10, 80, 80, 25);
        entryPanel.add(weightLabel);

        weightTextField = new JTextField(); // textbox
        weightTextField.setBounds(100, 80, 165, 25);
        entryPanel.add(weightTextField);


        bodyWeightLabel = new JLabel("BodyWeight"); // label
        bodyWeightLabel.setBounds(10, 120, 80, 25);
        entryPanel.add(bodyWeightLabel);

        bodyWeightTextField = new JTextField(); // textbox
        bodyWeightTextField.setBounds(100, 120, 165, 25);
        entryPanel.add(bodyWeightTextField);
    }

    // MODIFIES: this
    // EFFECTS: sets up combo box and labels for reps/sets
    private void setRepSetInput() {

        repLabel = new JLabel("Reps/Sets");
        repLabel.setBounds(10, 150, 80, 25);
        entryPanel.add(repLabel);

        Integer[] numberOptions = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        repBox = new JComboBox(numberOptions);
        repBox.setBounds(100, 150, 50, 25);
        entryPanel.add(repBox);


        setBox = new JComboBox(numberOptions);
        setBox.setBounds(160, 150, 50, 25);
        entryPanel.add(setBox);

    }


    // MODIFIES: this
    // EFFECTS: sets up buttons on exercise page for submitting and adding
    public void exerciseButtons() {
        submitExerciseButton = new JButton("Submit Exercise");
        submitExerciseButton.setBounds(10, 220, 160, 25);
        entryPanel.add(submitExerciseButton);
        submitExerciseButton.addActionListener(this);

        doneAddingButton = new JButton("Done");
        doneAddingButton.setBounds(10, 250, 160, 25);
        entryPanel.add(doneAddingButton);
        doneAddingButton.addActionListener(this);

    }


    // MODIFIES: this
    // EFFECTS: adds a new exercise to the current session using the current exercise values
    public void addExerciseToLog() {
        currName = nameTextField.getText();
        currWeight = Integer.parseInt(weightTextField.getText());
        currBodyWeight = Integer.parseInt(bodyWeightTextField.getText());
        currSets = (int) setBox.getSelectedItem();
        currReps = (int) repBox.getSelectedItem();

        currSession.addExercise(new GymExercise(currName, currWeight, currBodyWeight, currReps, currSets));
    }


    @Override
    // MODIFIES: this
    // EFFECTS: changes behaviour based on buttons pressed
    public void actionPerformed(ActionEvent e) {

        dateButtonReaction(e);

        submitExerciseReaction(e);

        doneAddingReaction(e);

        mostWeightReaction(e);

        if (e.getSource() == saveButton) {
            saveWorkoutLog();
        }

        if (e.getSource() == loadButton) {
            loadWorkoutLog();
        }

    }



    // MODIFIES: this
    // EFFECTS: hides date page, calls the exercise page and creates a new session with date
    public void dateButtonReaction(ActionEvent e) {
        if (e.getSource() == dateButton) {
            // hiding date page and json buttons
            dateLabel.setVisible(false);
            dateTextField.setVisible(false);
            dateButton.setVisible(false);
            mostWeightText.setVisible(false);
            mostWeightButton.setVisible(false);
            saveButton.setVisible(false);
            loadButton.setVisible(false);
            // create new session
            currDate = dateTextField.getText();
            currSession = new GymSession(new ArrayList<GymExercise>(), currDate);
            log.addGymSession(currSession);
            setExerciseInput();
        }
    }


    // MODIFIES: this
    // EFFECTS: logs exercise, clears fields
    public void submitExerciseReaction(ActionEvent e) {
        if (e.getSource() == submitExerciseButton) {

            addExerciseToLog();

            // clear fields
            nameTextField.setText("");
            weightTextField.setText("");
            bodyWeightTextField.setText("");

        }
    }

    // MODIFIES: this
    // EFFECTS: calls date page, hides exercise inputs
    private void doneAddingReaction(ActionEvent e) {
        if (e.getSource() == doneAddingButton) {

            exerciseNameLabel.setVisible(false);
            nameTextField.setVisible(false);
            weightLabel.setVisible(false);
            weightTextField.setVisible(false);
            bodyWeightLabel.setVisible(false);
            bodyWeightTextField.setVisible(false);
            repLabel.setVisible(false);
            repBox.setVisible(false);
            setBox.setVisible(false);
            submitExerciseButton.setVisible(false);
            doneAddingButton.setVisible(false);
            sessionText.setVisible(false);

            updateViewSessions();
            setDateInput();
            setJsonButtons();


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
    // EFFECTS: sets mostWeightText to the most weight lifted in the log
    private void mostWeightReaction(ActionEvent e) {
        if (e.getSource() == mostWeightButton) {
            mostWeight = log.mostWeightLifted();
            mostWeightText.setText(mostWeight);
        }
    }



}
