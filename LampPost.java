/**
 The LampPost class represents a lamp post object that can be drawn on the screen.
 It implements the DrawingObject class to render the lamp post.

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

public class LampPost implements DrawingObject {

    private int xPosition;
    private double[] xPoints, yPoints;

    /**
     * Constructs a LampPost object with the specified x-coordinate.
     * Initializes the lamp post with the given position.
     *
     * @param xPosition the x-coordinate of the lamp post
     */
    public LampPost(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Draws the lamp post using the provided Graphics2D object.
     * This method sets up the coordinates for the lamp post and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        xPoints = new double[]{xPosition + 15, xPosition - 80, xPosition + 70};
        yPoints = new double[]{75, 550, 550};

        Rectangle base1 = new Rectangle(xPosition + 50, 495, 90, 15, new Color(5, 34, 35));
        Rectangle base2 = new Rectangle(xPosition + 65, 375, 60, 120, new Color(12, 63, 64));
        Rectangle base3 = new Rectangle(xPosition + 65, 475, 60, 20, new Color(20, 45, 41));
        Rectangle base1Shading = new Rectangle(xPosition + 50, 495, 90, 15, new Color(5, 34, 35).darker());
        Rectangle base2Shading = new Rectangle(xPosition + 65, 375, 60, 120, new Color(12, 63, 64).darker());
        Rectangle base3Shading = new Rectangle(xPosition + 65, 475, 60, 20, new Color(20, 45, 41).darker());

        Rectangle postBase = new Rectangle(xPosition + 88, 355, 16, 20, new Color(20, 45, 41));
        Rectangle post = new Rectangle(xPosition + 88, 60, 16, 330, new Color(12, 63, 64));
        Rectangle top1 = new Rectangle(xPosition, 60, 90, 15, new Color(12, 63, 64));
        Rectangle top2 = new Rectangle(xPosition - 20, 75, 60, 25, new Color(20, 45, 41));
        Rectangle postBaseShading = new Rectangle(xPosition + 88, 355, 16, 20, new Color(20, 45, 41).darker());
        Rectangle postShading = new Rectangle(xPosition + 88, 60, 16, 330, new Color(12, 63, 64).darker());
        Rectangle top2Shading = new Rectangle(xPosition - 20, 75, 60, 25, new Color(20, 45, 41).darker());

        Circle light = new Circle(xPosition - 15, 75, 50, Color.YELLOW, true);
        Triangle lightRays = new Triangle(xPoints, yPoints, new Color(245, 235, 125, 100));
        Circle lightShading = new Circle(xPosition - 15, 75, 50, Color.YELLOW.darker(), true);

        g2d.translate(10, 0);
        base1Shading.draw(g2d);
        base2Shading.draw(g2d);
        base3Shading.draw(g2d);
        postShading.draw(g2d);
        postBaseShading.draw(g2d);
        lightShading.draw(g2d);
        top2Shading.draw(g2d);

        g2d.setTransform(reset);
        base1.draw(g2d);
        base2.draw(g2d);
        lightRays.draw(g2d);
        light.draw(g2d);
        post.draw(g2d);
        postBase.draw(g2d);
        top1.draw(g2d);
        top2.draw(g2d);
        base3.draw(g2d);
    }

    /**
     * Updates the state of the lamp post.
     * This method is currently empty because the lamp post requires no movement.
     */
    @Override
    public void update() {
    }
}
