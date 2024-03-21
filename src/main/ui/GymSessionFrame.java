package ui;

import model.GymSession;
import model.PersonalBest;
import model.WorkoutLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GymSessionFrame extends JFrame implements ActionListener{

    // initialize workout log
    ArrayList<GymSession> gymSessions;
    ArrayList<PersonalBest> pbs;
    WorkoutLog log;

    // Frames and Panels
    private JFrame frame;
    private JPanel entryPanel;
    private JPanel viewPanel;

    // Date page
    private JLabel dateLabel;
    private JTextField dateTextField;
    private JButton dateButton;


    // Exercise Page
    private JLabel exerciseNameLabel;
    private JTextField nameTextField;
    private JLabel weightLabel;
    private JTextField weightTextField;

    private JLabel repLabel;
    private JComboBox repBox;
    private JComboBox setBox;

    public GymSessionFrame() {
        gymSessions = new ArrayList<GymSession>();
        pbs = new ArrayList<PersonalBest>();
        log = new WorkoutLog(pbs, gymSessions);
        gymSetup();
        frame.setVisible(true);
    }

    public void gymSetup() {
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setEntryPanel();
//        setViewPanel();

    }

    public void setEntryPanel() {
        entryPanel = new JPanel();
        entryPanel.setBounds(0, 0, 300, 600);
        entryPanel.setBackground(Color.gray);
        frame.add(entryPanel);
        entryPanel.setLayout(null);

        setDateInput();

    }

//
//    public void setViewPanel() {
//        viewPanel = new JPanel();
//        viewPanel.setBounds(300, 0, 300, 600);
//        viewPanel.setBackground(Color.lightGray);
//        frame.add(viewPanel);
//    }

    public void setDateInput() {
        // label and text field
        dateLabel = new JLabel("Date"); // label
        dateLabel.setBounds(10, 20, 80, 25);
        entryPanel.add(dateLabel);
        dateTextField = new JTextField(); // textbox
        dateTextField.setBounds(100, 20, 100, 25);
        entryPanel.add(dateTextField);

        // button
        dateButton = new JButton("Record PB");
        dateButton.setBounds(10, 160, 160, 25);
        entryPanel.add(dateButton);
        dateButton.addActionListener(this);


    }


    private void setExerciseInput() {
        setExerciseNameInput();
        setWeightInput();
        setRepSetInput();

        // start here : next exercise or next session
    }


    public void setExerciseNameInput() {
        exerciseNameLabel = new JLabel("Exercise Name"); // label
        exerciseNameLabel.setBounds(10, 50, 100, 25);
        entryPanel.add(exerciseNameLabel);

        nameTextField = new JTextField(); // textbox
        nameTextField.setBounds(130, 50, 165, 25);
        entryPanel.add(nameTextField);
    }

    public void setWeightInput() {
        weightLabel = new JLabel("Weight"); // label
        weightLabel.setBounds(10, 80, 80, 25);
        entryPanel.add(weightLabel);

        weightTextField = new JTextField(); // textbox
        weightTextField.setBounds(100, 80, 165, 25);
        entryPanel.add(weightTextField);
    }

    private void setRepSetInput() {

        repLabel = new JLabel("Reps/Sets");
        repLabel.setBounds(10, 110, 80, 25);
        entryPanel.add(repLabel);

        Integer[] numberOptions = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        repBox = new JComboBox(numberOptions);
        repBox.setBounds(100, 110, 50, 25);
        entryPanel.add(repBox);


        setBox = new JComboBox(numberOptions);
        setBox.setBounds(160, 110, 50, 25);
        entryPanel.add(setBox);

    }





    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dateButton) {
            dateLabel.setVisible(false);
            dateTextField.setVisible(false);
            String date = dateTextField.getText();
            setExerciseInput();
        }

        // for later : Integer date = Integer.valueOf(dateTextField.getText());



    }




}
