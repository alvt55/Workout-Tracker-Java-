package ui;

import model.GymSession;
import model.PersonalBest;
import model.WorkoutLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PersonalBestFrame implements ActionListener {

    public static final JButton RECORD_PB = new JButton("Record PB");
    // initialize workout log
    private ArrayList<GymSession> gymSessions = new ArrayList<GymSession>();
    private ArrayList<PersonalBest> pbs = new ArrayList<PersonalBest>();
    private WorkoutLog log = new WorkoutLog(pbs, gymSessions);


    private JButton addPbButton;
    private JPanel panel;
    private JFrame frame;
    private JTextField userDate;
    private JTextField exerciseName;
    private JTextField userWeight;
    private JButton recordPbButton;
    private JButton nextButton2;

    public PersonalBestFrame() {
        setup();

        datePrompt();
        exerciseNamePrompt();
        weightPrompt();
        recordPbButton();
        frame.setVisible(true);
    }

    public void setup() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
    }





    public void datePrompt() {
        JLabel dateLabel = new JLabel("Date"); // label
        dateLabel.setBounds(10, 20, 80, 25);
        panel.add(dateLabel);

        userDate = new JTextField(); // textbox
        userDate.setBounds(100, 20, 165, 25);
        panel.add(userDate);

    }
    
    public void exerciseNamePrompt() {
        JLabel nameLabel = new JLabel("Name"); // label
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        exerciseName = new JTextField(); // textbox
        exerciseName.setBounds(100, 50, 165, 25);
        panel.add(exerciseName);
    }

    public void weightPrompt() {

        JLabel userWeight = new JLabel("Weight"); // label
        userWeight.setBounds(10, 80, 80, 25);
        panel.add(userWeight);

        this.userWeight = new JTextField(); // textbox
        this.userWeight.setBounds(100, 80, 165, 25);
        panel.add(this.userWeight);

    }



    private void recordPbButton() {
        recordPbButton = RECORD_PB;
        recordPbButton.setBounds(10, 160, 160, 25);
        panel.add(recordPbButton);

        recordPbButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (recordPbButton.equals(e.getActionCommand())) {
            String name = exerciseName.getText();
            String date = userDate.getText();
            int weight = Integer.parseInt(userWeight.getText());
            log.addPersonalBest(new PersonalBest(name, date, weight));
        }
    }
}
