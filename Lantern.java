/**
 The Lantern class represents a lantern object that can be drawn on the screen.
 It implements the DrawingObject class to render the lantern.

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
import java.util.Random;

public class Lantern implements DrawingObject {
    private double xPosition;
    private double yPosition;
    private double scale;
    private Random random;
    private int direction;

    /**
     * Constructs a Lantern object with the specified position and scale.
     * Initializes the lantern with the given parameters.
     *
     * @param xPosition the x-coordinate of the lantern
     * @param yPosition the y-coordinate of the lantern
     * @param scale the scale of the lantern
     */
    public Lantern(double xPosition, double yPosition, double scale) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.scale = scale;
        random = new Random();
        direction = random.nextInt(1, 3) == 2 ? 1 : -1;
    }

    /**
     * Draws the lantern using the provided Graphics2D object.
     * This method sets up the coordinates for the lantern and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        double rectWidth = 40 * scale;
        double rectHeight = 50 * scale;
        int ellipseWidth = (int) (40 * scale);
        int ellipseHeight = (int) (22 * scale);
        double ellipseOffsetY = 10 * scale; // Offset for ellipse adjustment

        Rectangle rectangle = new Rectangle(xPosition - rectWidth / 2, yPosition - rectHeight / 2, rectWidth, rectHeight, new Color(254, 105, 30), true);
        Ellipse bottomEllipse = new Ellipse(xPosition - (double) ellipseWidth / 2, (yPosition + rectHeight / 2) - ellipseOffsetY, ellipseWidth, ellipseHeight, new Color(255, 162, 3));
        Ellipse topEllipse = new Ellipse(xPosition - (double) ellipseWidth / 2, (yPosition - rectHeight / 2) - (ellipseHeight - ellipseOffsetY), ellipseWidth, ellipseHeight, new Color(254, 105, 30));

        topEllipse.draw(g2d);
        rectangle.draw(g2d);
        bottomEllipse.draw(g2d);
        update();
    }

    /**
     * Updates the state of the lantern.
     * This method adjusts the position of the lantern to create a floating animation.
     */
    @Override
    public void update() {
        yPosition -= random.nextDouble(0, 0.4);
        xPosition += random.nextDouble(0, 0.25) * direction;
    }
}
