/**
 The Instructions class represents the instructions screen that can be drawn on the screen.
 It implements the DrawingObject class to render the instructions.

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
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Instructions implements DrawingObject {
    private ActionHandler actionHandler;
    private String player1Name;
    private String player2Name;

    /**
     * Constructs an Instructions object with the specified action handler and player names.
     * Initializes the instructions with the given parameters.
     *
     * @param actionHandler the action handler for user inputs
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    public Instructions(ActionHandler actionHandler, String player1Name, String player2Name) {
        this.actionHandler = actionHandler;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    /**
     * Draws the instructions using the provided Graphics2D object.
     * This method sets up the text for the instructions and uses transformations
     * to position and draw them on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        int textPosition = 96;

        String message = String.format("Once upon a time, %s and %s made a \n" +
                "nether portal, but when they stepped through \n" +
                "the portal, instead of going to the nether, they \n" +
                "were transported to the real world. [Space]", player1Name, player2Name);

        String controls1 = "Press [B] to summon birds.\n" +
                "Press [L] to place lanterns, or left-click to \nadd them at a specific spot.\n" +
                "Right-click to start or stop the rain.";

        String controls2 = String.format("Press [F] to launch fireworks.\n" +
                "Press [Enter] to start/stop animation.\n" +
                "Use [A], [D], and [W] to move %s.\n" +
                "Use [\u2190], [\u2192], and [\u2191] arrow keys to move %s.\n", player1Name, player2Name);

        String outro = String.format("No %s or %s were harmed in the \nmaking of this interactive animation.\n" +
                "No CS students were harmed in the making \nof this project.", player1Name + "s", player2Name + "s");

        ArrayList<String> messages = new ArrayList<>(List.of(message, controls1, controls2, outro));

        if (actionHandler.showInstructions) {
            g2d.setColor(new Color(255, 255, 255, 230));
            g2d.fillRoundRect(50, 50, 700, 200, 35, 35);

            g2d.setColor(new Color(0, 0, 0));
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRoundRect(55, 55, 690, 190, 25, 25);

            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32F));
            for (String line : messages.get(actionHandler.dialogueTrack - 1).split("\n")) {
                g2d.drawString(line, 78, textPosition);
                textPosition += 40;
            }
            g2d.setTransform(reset);
        }
    }

    /**
     * Updates the state of the instructions.
     * This method is currently empty because the instructions require no movement.
     */
    @Override
    public void update() {

    }
}
