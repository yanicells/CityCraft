/**
The SceneFrame class represents the main frame of the application.
It sets up the GUI and contains the SceneCanvas where the scene is drawn.

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

import javax.swing.*;

public class SceneFrame{
    private JFrame frame;
    private SceneCanvas sceneCanvas;

    /**
     * Constructs a SceneFrame object and initializes the JFrame.
     */
    public SceneFrame(){
        this.frame = new JFrame();
    }

    /**
     * Sets up the GUI by initializing the StartFrame.
     */
    public void setUpGUI(){
        StartFrame startFrame = new StartFrame(this);
    }

    /**
     * Sets up the canvas with the specified player names and number of NPCs.
     * Configures the JFrame and adds the SceneCanvas to it.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     * @param NPCNumber the number of NPCs
     */
    public void setUpCanvas(String player1Name, String player2Name, int NPCNumber){
        frame.setTitle("Midterm Project - Capistrano - Torres");
        sceneCanvas = new SceneCanvas(800, 600, player1Name, player2Name, NPCNumber);

        frame.add(sceneCanvas);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
