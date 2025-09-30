/**
 The Cart class represents a cart object that can be drawn on the screen.
 It implements the DrawingObject class to render the cart.

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

public class Cart implements DrawingObject {

    private int xPosition;
    private int yPosition;
    private Color color;
    private double[] xPoints1, yPoints1, xPoints2, yPoints2;

    /**
     * Constructs a Cart object with the specified position and color.
     * Initializes the points for drawing the cart.
     *
     * @param xPosition the x-coordinate of the cart
     * @param yPosition the y-coordinate of the cart
     * @param color the color of the cart
     */
    public Cart(int xPosition, int yPosition, Color color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        initializePoints();

    }

    /**
     * Initializes the points for drawing the cart.
     */
    private void initializePoints() {
        xPoints1 = new double[]{xPosition - 5, xPosition + 15, xPosition + 35, xPosition + 35, xPosition + 15, xPosition - 5, xPosition - 25, xPosition - 25};
        yPoints1 = new double[]{yPosition + 175, yPosition + 175, yPosition + 195, yPosition + 215, yPosition + 235, yPosition + 235, yPosition + 215, yPosition + 195};

        xPoints2 = new double[]{xPosition + 15, xPosition + 20, xPosition + 40, xPosition + 40, xPosition + 20, xPosition + 15, xPosition + 35, xPosition + 35};
        yPoints2 = new double[]{yPosition + 175, yPosition + 175, yPosition + 195, yPosition + 215, yPosition + 235, yPosition + 235, yPosition + 215, yPosition + 195};

    }

    /**
     * Draws the cart using the provided Graphics2D object.
     * This method sets up the coordinates for the cart and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Octagon cart = new Octagon(xPoints1, yPoints1, color);
        Octagon cartShading = new Octagon(xPoints2, yPoints2, color.darker());
        cart.draw(g2d);
        cartShading.draw(g2d);
    }

    /**
     * Updates the state of the cart.
     * This method is empty because the cart requires no movement.
     */
    @Override
    public void update() {

    }
}


