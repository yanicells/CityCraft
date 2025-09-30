/**
 The Cloud class represents a cloud object that can be drawn on the screen.
 It implements the DrawingObject class to render the cloud.

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
import java.util.Random;

public class Cloud implements DrawingObject {
    private double xPosition;
    private double yPosition;
    private int width;
    private int height;
    private Color color;
    private static Random random;
    private Ellipse[] ellipses;
    private Ellipse[] shadingEllipses;

    /**
     * Constructs a Cloud object with the specified position, size, and color.
     * Initializes the cloud with the given parameters.
     *
     * @param xPosition the x-coordinate of the cloud
     * @param yPosition the y-coordinate of the cloud
     * @param width the width of the cloud
     * @param height the height of the cloud
     * @param color the color of the cloud
     */
    public Cloud(double xPosition, double yPosition, int width, int height, Color color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.color = color;
        random = new Random();
    }

    /**
     * This method sets up the coordinates for the cloud and its shadings
     */
    private void initializeEllipses() {
        ellipses = new Ellipse[]{
                new Ellipse(xPosition, yPosition, width, height - 100, color),
                new Ellipse(xPosition - (0.08 * width), yPosition - 40 + (0.4 * height), (int) (0.5 * width), (int) (0.5 * height), color),
                new Ellipse(xPosition + 10 + (0.6 * width), yPosition - 10 + (0.1 * height), (int) (0.4 * width), (int) (0.4 * height), color),
                new Ellipse(xPosition + (0.3 * width), yPosition - 30 + (0.1 * height), (int) (0.6 * width), (int) (0.7 * height), color),
                new Ellipse(xPosition - 80 + (0.3 * width), yPosition - 20 + (0.5 * height), (int) (2 * width), (int) (0.2 * height), color)
        };

        shadingEllipses = new Ellipse[]{
                new Ellipse(xPosition, yPosition, width, height - 100, color.darker()),
                new Ellipse(xPosition - (0.08 * width), yPosition - 40 + (0.4 * height), (int) (0.5 * width), (int) (0.5 * height), color.darker()),
                new Ellipse(xPosition + 10 + (0.6 * width), yPosition - 10 + (0.1 * height), (int) (0.4 * width), (int) (0.4 * height), color.darker()),
                new Ellipse(xPosition + (0.3 * width), yPosition - 30 + (0.1 * height), (int) (0.6 * width), (int) (0.7 * height), color.darker()),
                new Ellipse(xPosition - 80 + (0.3 * width), yPosition - 20 + (0.5 * height), (int) (2 * width), (int) (0.2 * height), color.darker())
        };
    }

    /**
     * Draws the cloud using the provided Graphics2D object.
     * This method uses the coordinates of the cloud and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();

        initializeEllipses();

        g2d.translate(5, 5);
        for (Ellipse e : shadingEllipses) {
            e.draw(g2d);
        }
        g2d.setTransform(reset);

        for (Ellipse e : ellipses) {
            e.draw(g2d);
        }

        update();
    }

    /**
     * Updates the state of the cloud.
     * This method adjusts the position of the cloud to create a drifting animation.
     */
    @Override
    public void update() {
        xPosition += random.nextDouble(0, 1.1);
        yPosition += random.nextDouble(-0.1, 0.1);

        if (xPosition >= 3600) {
            xPosition = random.nextInt(-300, -100);
        }
    }
}
