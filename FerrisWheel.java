/**
 The FerrisWheel class represents a Ferris wheel object that can be drawn on the screen.
 It implements the DrawingObject class to render the Ferris wheel.

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
import java.awt.geom.*;

public class FerrisWheel implements DrawingObject{

    private int xPosition;
    private int yPosition;
    private double rotationAngle;
    private int centerX;
    private int centerY;
    private int radius;
    private Person player;
    private Sound sound;

    /**
     * Constructs a FerrisWheel object with the specified position and player.
     * Initializes the Ferris wheel with the given parameters.
     *
     * @param xPosition the x-coordinate of the Ferris wheel
     * @param yPosition the y-coordinate of the Ferris wheel
     * @param player the player object interacting with the Ferris wheel
     */
    public FerrisWheel(int xPosition, int yPosition, Person player) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rotationAngle = 0;
        radius = 180;
        this.player = player;
        sound = new Sound("carnival.wav");
    }

    /**
     * Draws the Ferris wheel using the provided Graphics2D object.
     * This method sets up the coordinates for the Ferris wheel and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        Circle outline = new Circle(xPosition, yPosition, radius * 2, new Color(232, 116, 186), false, true);
        outline.draw(g2d);

        centerX = (int) (xPosition + radius);
        centerY = (int) (yPosition + radius);

        Line supportBelow1 = new Line(xPosition + radius, yPosition+200, xPosition + radius -100, yPosition+450, 10, Color.ORANGE);
        Line supportBelow2 = new Line(xPosition + radius, yPosition+200, xPosition + radius +100, yPosition+450, 10, Color.ORANGE);
        supportBelow1.draw(g2d);
        supportBelow2.draw(g2d);

        Color[] colors = {
                new Color(102, 154, 237), new Color(188, 111, 242),
                new Color(242, 231, 111), new Color(242, 155, 111),
                new Color(177, 242, 124), new Color(242, 124, 146),
                new Color(102, 154, 237), new Color(128, 237, 202)
        };

        for(int i = 0; i < 8; i++){
            double angle = Math.toRadians(i * 45 + rotationAngle);

            int x1 = (int) (centerX + radius * Math.cos(angle));
            int y1 = (int) (centerY + radius * Math.sin(angle));

            Line line = new Line(centerX, centerY, x1, y1, 10, new Color(232, 116, 186));
            Line lineShading = new Line(centerX + 10, centerY, x1 + 10, y1, 10, new Color(232, 116, 186).darker());
            lineShading.draw(g2d);
            line.draw(g2d);
        }

        for(int i = 0; i < 8; i++){
            double angle = Math.toRadians(i * 45 + rotationAngle);

            int cartX = (int) (centerX + radius * Math.cos(angle));
            int cartY = (int) (centerY + radius * Math.sin(angle));

            Cart cart = new Cart(cartX, cartY, colors[i]);
            g2d.translate(-5, -(radius + 20));

            cart.draw(g2d);
            g2d.setTransform(reset);
        }

        update();
    }

    /**
     * Updates the state of the Ferris wheel.
     * This method adjusts the rotation angle to create a spinning animation and controls the looping of the carnival sound based on the player's position.
     */
    @Override
    public void update() {
        rotationAngle += 1;
        rotationAngle %= 360;

        if (player.xPosition > 1600 && player.xPosition < 2400) {
                sound.loop(); 
            }
        else {
            sound.stop(); 
        }
    }
}
