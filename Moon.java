/**
 The Moon class represents a moon object that can be drawn on the screen.
 It implements the DrawingObject class to render the moon.

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

public class Moon implements DrawingObject {
    private double xOffset;
    private double yOffset;
    private static Random random;

    private double[] xPoints1, yPoints1;
    private double[] xPoints2, yPoints2;
    private double[] xPoints3, yPoints3;
    private double[] xPoints1Pentagon, yPoints1Pentagon;
    private double[] xPoints2Pentagon, yPoints2Pentagon;

    private double xPosition;
    private double yPosition;

    private Sun sun;
    private boolean visible;

    /**
     * Constructs a Moon object.
     * Initializes the moon's position and visibility.
     */
    public Moon() {
        initializePoints();
        visible = false;
    }

    /**
     * Initializes the points for drawing the moon and its craters.
     */
    private void initializePoints() {
        xPosition = -100;
        yPosition = 300;

        xPoints1 = new double[]{22, 30, 38, 38, 30, 22, 14, 14};
        yPoints1 = new double[]{360, 360, 368, 376, 384, 384, 376, 368};
        xPoints2 = new double[]{-60, -53, -46, -46, -53, -60, -67, -67};
        yPoints2 = new double[]{340, 340, 347, 354, 361, 361, 354, 347};
        xPoints3 = new double[]{40, 44, 48, 48, 44, 40, 36, 36};
        yPoints3 = new double[]{430, 430, 434, 438, 442, 442, 438, 434};

        xPoints1Pentagon = new double[]{-10, -5, 0, -3, -7};
        yPoints1Pentagon = new double[]{340, 338, 342, 348, 348};
        xPoints2Pentagon = new double[]{-10, -5, 0, -3, -7};
        yPoints2Pentagon = new double[]{440, 438, 442, 448, 448};
    }

    /**
     * Draws the moon using the provided Graphics2D object.
     * This method sets up the coordinates for the moon and its craters and uses transformations
     * to position and draw them on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if(visible){
            random = new Random();

            Circle moon = new Circle(xPosition, yPosition, 180, new Color(179, 176, 170), true);
            Circle moonOuter1 = new Circle(xPosition-10, yPosition-10, 200, new Color(252, 251, 240, 120), true);
            Circle moonOuter2 = new Circle(xPosition-20, yPosition-20, 220, new Color(252, 251, 240, 50), true);

            Octagon smallCrater = new Octagon(xPoints1, yPoints1, new Color(131, 134, 134));
            Octagon smallerCrater2 = new Octagon(xPoints2, yPoints2, new Color(131, 134, 134));
            Octagon smallerCrater3 = new Octagon(xPoints3, yPoints3, new Color(131, 134, 134));

            Pentagon smallerCrater4 = new Pentagon(xPoints1Pentagon, yPoints1Pentagon, new Color(131, 134, 134));
            Pentagon smallerCrater5 = new Pentagon(xPoints2Pentagon, yPoints2Pentagon, new Color(131, 134, 134));

            Circle bigCrater = new Circle(xPosition + 20, yPosition + 70, 70, new Color(131, 134, 134), true);

            moonOuter2.draw(g2d);
            moonOuter1.draw(g2d);
            moon.draw(g2d);
            smallCrater.draw(g2d);
            bigCrater.draw(g2d);
            smallerCrater2.draw(g2d);
            smallerCrater3.draw(g2d);
            smallerCrater4.draw(g2d);
            smallerCrater5.draw(g2d);

            update();
        }
    }

    /**
     * Updates the state of the moon.
     * This method adjusts the position of the moon to create a moving animation.
     */
    @Override
    public void update() {
        if(yPosition > 450){
            reset();
            sun.reset();
        }

        if (yPosition < 50) {
            xOffset = random.nextDouble(0,1.6);
            yOffset = random.nextDouble(0, 0.5);
        } else if (xPosition > 2200) {
            xOffset = random.nextDouble(0,1.2);
            yOffset = random.nextDouble(0, (double) 1 / 2);
        } else {
            xOffset = random.nextDouble(0,1);
            yOffset = -random.nextDouble(0, (double) 1 / 2);
        }

        xPosition += xOffset;
        yPosition += yOffset;

        for (int i = 0; i < 8; i++) {
            xPoints1[i] += xOffset;
            xPoints2[i] += xOffset;
            xPoints3[i] += xOffset;

            yPoints1[i] += yOffset;
            yPoints2[i] += yOffset;
            yPoints3[i] += yOffset;
        }

        for (int i = 0; i < 5; i++) {
            xPoints1Pentagon[i] += xOffset;
            xPoints2Pentagon[i] += xOffset;

            yPoints1Pentagon[i] += yOffset;
            yPoints2Pentagon[i] += yOffset;
        }
    }

    /**
     * Resets the position and visibility of the moon.
     */
    public void reset() {
        initializePoints();
        visible = false;
    }

    /**
     * Sets the visibility of the moon.
     *
     * @param value the visibility state of the moon
     */
    public void setVisible(boolean value) {
        this.visible = value;
    }

    /**
     * Sets the sun object associated with the moon.
     *
     * @param sun the Sun object
     */
    public void setSun(Sun sun) {
        this.sun = sun;
    }

    /**
     * Checks if the moon is visible.
     *
     * @return true if the moon is visible, false otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Gets the x-coordinate of the moon.
     *
     * @return the x-coordinate of the moon
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * Gets the y-coordinate of the moon.
     *
     * @return the y-coordinate of the moon
     */
    public double getxPosition() {
        return xPosition;
    }
}
