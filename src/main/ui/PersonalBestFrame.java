package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalBestFrame implements ActionListener {

    JButton addPbButton;
    JPanel panel;
    JFrame frame;
    JTextField userDate;
    JTextField userName;
    JTextField userReps;
    JTextField userSets;
    JButton nextButton1;
    JButton nextButton2;

    PersonalBestFrame() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        datePrompt();
        exerciseNamePrompt();
        repPrompt();
        setPrompt();
        frame.setVisible(true);

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

        userName = new JTextField(); // textbox
        userName.setBounds(100, 50, 165, 25);
        panel.add(userName);
    }

    public void repPrompt() {

        JLabel repsLabel = new JLabel("Repetitions"); // label
        repsLabel.setBounds(10, 80, 80, 25);
        panel.add(repsLabel);

        userReps = new JTextField(); // textbox
        userReps.setBounds(100, 80, 165, 25);
        panel.add(userReps);




    }

    private void setPrompt() {
        JLabel setsLabel = new JLabel("Sets"); // label
        setsLabel.setBounds(10, 110, 80, 25);
        panel.add(setsLabel);

        userSets = new JTextField(); // textbox
        userSets.setBounds(100, 110, 165, 25);
        panel.add(userSets);


        nextButton1 = new JButton("Next");
        nextButton1.setBounds(10, 160, 80, 25);
        panel.add(nextButton1);

        nextButton1.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton1) {

        }
    }
}
