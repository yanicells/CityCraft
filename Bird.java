/**
 The Bird class represents a bird object that can be drawn on the screen.
 It implements the DrawingObject class to render and animate the bird.

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
import java.awt.geom.Path2D;
import java.util.Random;

public class Bird implements DrawingObject {
    private Path2D.Double leftWing;
    private Path2D.Double rightWing;
    private double xStartPosition;
    private double yStartPosition;
    private double xEndPosition;
    private double yEndPosition;
    private Random random;
    private double flapHeight;
    private double flapDirection;
    private boolean right;
    private Sound sound;

    /**
     * Constructs a Bird object with the specified starting position.
     * Initializes the wings, random generator, flap height, flap direction, and sound.
     *
     * @param xStartPosition the starting x-coordinate of the bird
     * @param yStartPosition the starting y-coordinate of the bird
     */
    public Bird(double xStartPosition, double yStartPosition) {
        this.xStartPosition = xStartPosition;
        this.yStartPosition = yStartPosition;
        this.xEndPosition = xStartPosition + 50;
        this.yEndPosition = yStartPosition;
        leftWing = new Path2D.Double();
        rightWing = new Path2D.Double();
        random = new Random();
        flapHeight = -10;
        flapDirection = 0.1; 
        this.right = random.nextInt(1, 3) == 1;
        sound = new Sound("bird.wav");
        sound.loop();
    }

    /**
     * Draws the bird using the provided Graphics2D object.
     * This method sets up the coordinates for the wings and uses transformations
     * to position and draw them on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (yStartPosition < 0) {
            sound.stop();
        }

        leftWing.reset();
        rightWing.reset();

        leftWing.moveTo(xStartPosition, yStartPosition);
        leftWing.curveTo(xStartPosition + 9, yStartPosition + flapHeight, xStartPosition + 18, yStartPosition + flapHeight, (xStartPosition + xEndPosition) / 2, yStartPosition);

        rightWing.moveTo((xStartPosition + xEndPosition) / 2, yStartPosition);
        rightWing.curveTo(xEndPosition - 16, yEndPosition + flapHeight, xEndPosition - 7, yEndPosition + flapHeight, xEndPosition, yEndPosition);

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(leftWing);
        g2d.draw(rightWing);

        update();
    }

    /**
     * Updates the state of the bird.
     * This method adjusts the flap height and position of the bird to create a flying animation.
     */
    @Override
    public void update() {

        flapHeight += flapDirection;

        double yOffset = random.nextDouble(-0.7, 0.1);
        double xOffset = right ? random.nextDouble(0, 0.5) : -random.nextDouble(0, 0.5);

        yStartPosition += yOffset;
        yEndPosition += yOffset;

        xStartPosition += xOffset;
        xEndPosition += xOffset;

        if (flapHeight > -3) {
            flapDirection = -0.5;

        } else if (flapHeight < -9) {
            flapDirection = 0.5;
        }
    }
}
