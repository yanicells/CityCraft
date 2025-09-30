/**
The StartFrame class represents the initial frame of the application where users can input player names and the number of NPCs.
It implements ActionListener to handle button click events.

@author Edrian Miguel E. Capistrano (240939)
@author Sofia Dion Y. Torres (244566)
@version March 5, 2025

I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartFrame implements ActionListener{
    private JFrame frameLaunch;
    private JButton startButton;
    protected JTextField player1NameField;
    protected JTextField player2NameField;
    protected JComboBox<Integer> noOfNPCComboBox;
    protected boolean startAnimation;
    private SceneFrame sceneFrame;

    /**
     * Constructs a StartFrame object and initializes the GUI components.
     *
     * @param sceneFrame the SceneFrame object to set up the main canvas
     */
    public StartFrame(SceneFrame sceneFrame) {
        startAnimation = false;
        this.sceneFrame = sceneFrame;
        frameLaunch = new JFrame("Midterm Project - Capistrano - Torres");
        startButton = new JButton("Click to Start Animation");
        startButton.setFont(new Font("Dialog", Font.BOLD, 15));

        frameLaunch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLaunch.setSize(400, 200);
        frameLaunch.setResizable(false);
        frameLaunch.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        player1NameField = new JTextField("Steve");
        inputPanel.add(new JLabel("Enter Player 1 Name:"));
        inputPanel.add(player1NameField);

        player2NameField = new JTextField("Alex");
        inputPanel.add(new JLabel("Enter Player 2 Name:"));
        inputPanel.add(player2NameField);

        Integer[] npcOptions = {0, 1, 2, 3, 4, 5};
        noOfNPCComboBox = new JComboBox<>(npcOptions);
        inputPanel.add(new JLabel("Number of NPCs"));
        inputPanel.add(noOfNPCComboBox);

        startButton.setBackground(new Color (50, 168, 82));
        startButton.setForeground(Color.WHITE);
        inputPanel.setBackground(new Color(202, 252, 247));
        frameLaunch.add(inputPanel, BorderLayout.CENTER);
        frameLaunch.add(startButton, BorderLayout.SOUTH);
        startButton.addActionListener(this);

        frameLaunch.setLocationRelativeTo(null);
        frameLaunch.setVisible(true);
    }

    /**
     * Starts the scene by setting up the canvas with the specified player names and number of NPCs.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     * @param NPCNumber the number of NPCs
     */
    private void startScene(String player1Name, String player2Name, int NPCNumber) {
        frameLaunch.setVisible(false);
        sceneFrame.setUpCanvas(player1Name, player2Name, NPCNumber);
    }

    /**
     * Handles action events.
     * This method is called when the start button is clicked.
     *
     * @param e the ActionEvent object containing details of the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            startAnimation = true;
            startScene(player1NameField.getText(), player2NameField.getText(), (int) noOfNPCComboBox.getSelectedItem());
        }
    }
}