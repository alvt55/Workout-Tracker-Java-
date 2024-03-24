package ui;

import model.GymExercise;
import model.GymSession;
import model.PersonalBest;
import model.WorkoutLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JLabel mostWeightLabel;




    public GymSessionFrame() {
        gymSessions = new ArrayList<GymSession>();
        pbs = new ArrayList<PersonalBest>();
        log = new WorkoutLog(pbs, gymSessions);
        gymSetup();
        frame.setVisible(true);
    }

    public void gymSetup() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setViewPanel();
        setEntryPanel();



    }

    public void setEntryPanel() {
        entryPanel = new JPanel();
        entryPanel.setBounds(0, 0, 400, 600);
        entryPanel.setBackground(Color.gray);
        frame.add(entryPanel);
        entryPanel.setLayout(null);

        setDateInput();
        frame.setVisible(true);
    }


    public void setViewPanel() {
        viewPanel = new JPanel();
        viewPanel.setBounds(400, 0, 400, 600);
        viewPanel.setBackground(Color.lightGray);
        frame.add(viewPanel);

    }

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

        mostWeightLabel = new JLabel(""); // label
        mostWeightLabel.setBounds(10, 210, 300, 25);
        entryPanel.add(mostWeightLabel);

    }


    private void setExerciseInput() {
        setExerciseNameInput();
        setWeightInput();
        setRepSetInput();
        exerciseButtons();
        frame.setVisible(true);

    }


    public void setExerciseNameInput() {
        exerciseNameLabel = new JLabel("Name"); // label
        exerciseNameLabel.setBounds(10, 50, 80, 25);
        entryPanel.add(exerciseNameLabel);

        nameTextField = new JTextField(); // textbox
        nameTextField.setBounds(130, 50, 165, 25);
        entryPanel.add(nameTextField);
    }

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


    public void addExerciseToLog() {
        currName = nameTextField.getText();
        currWeight = Integer.parseInt(weightTextField.getText());
        currBodyWeight = Integer.parseInt(bodyWeightTextField.getText());
        currSets = (int) setBox.getSelectedItem();
        currReps = (int) repBox.getSelectedItem();

        currSession.addExercise(new GymExercise(currName, currWeight, currBodyWeight, currReps, currSets));
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dateButton) {

            // hiding date page
            dateLabel.setVisible(false);
            dateTextField.setVisible(false);
            dateButton.setVisible(false);
            mostWeightButton.setVisible(false);
            currDate = dateTextField.getText();

            // create new session
            currSession = new GymSession(new ArrayList<GymExercise>(), currDate);
            log.addGymSession(currSession);


            setExerciseInput();

        }

        if (e.getSource() == submitExerciseButton) {

            addExerciseToLog();

            // clear fields
            nameTextField.setText("");
            weightTextField.setText("");
            bodyWeightTextField.setText("");

        }

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

            setDateInput();
        }

        if (e.getSource() == mostWeightButton) {
            mostWeight = log.mostWeightLifted();
            mostWeightLabel.setText(mostWeight);
        }


    }


}
