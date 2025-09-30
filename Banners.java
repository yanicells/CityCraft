/**
 The Banners class is responsible for drawing a series of banners on the screen.
 It implements the DrawingObject class to render the banners.

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

public class Banners implements DrawingObject {
    private double[] xPoints1, yPoints, xPoints2, xPoints3;

    /**
     * Draws the banners using the provided Graphics2D object.
     * This method sets up the coordinates for the banners and uses transformations
     * to position and draw them on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        xPoints1 = new double[]{1600, 1650, 1625};
        xPoints2 = new double[]{1650, 1700, 1675};
        xPoints3 = new double[]{1700, 1750, 1725};
        yPoints = new double[]{150, 150, 200};

        Line bannerLine = new Line(1600, 200, 2400, 200, 2, Color.BLACK);
        Triangle banner1 = new Triangle(xPoints1, yPoints, new Color(22, 156, 17));
        Triangle banner2 = new Triangle(xPoints2, yPoints, new Color(204, 37, 87));
        Triangle banner3 = new Triangle(xPoints3, yPoints, Color.WHITE);
        Triangle banner4 = new Triangle(xPoints1, yPoints, new Color(255, 156, 210));
        Triangle banner5 = new Triangle(xPoints2, yPoints, new Color(245, 235, 125));

        g2d.rotate(Math.toRadians(5), 2000, 150);
        g2d.translate(5, -100);
        bannerLine.draw(g2d);

        drawBanners(g2d, reset, banner1, banner2, banner3, 0, 50, 5);

        g2d.rotate(Math.toRadians(-5), 2000, 150);
        bannerLine.draw(g2d);

        drawBanners(g2d, reset, banner4, banner5, banner3, 5, 50, 5);
    }

    /**
     * This method is a helper method for the draw method. This reduces redundant code and make the
     * draw method more readable
     *
     * @param g2d the Graphics2D object used for drawing
     * @param reset
     * @param bannerA
     * @param bannerB
     * @param bannerC
     * @param startX
     * @param startY    
     * @param numBanners
     */
    private void drawBanners(Graphics2D g2d, AffineTransform reset, Triangle bannerA, Triangle bannerB, Triangle bannerC, int startX, int startY, int numBanners) {
        g2d.translate(startX, startY);
        for (int i = 0; i < numBanners; i++) {
            bannerA.draw(g2d);
            bannerB.draw(g2d);
            bannerC.draw(g2d);
            g2d.translate(150, 0);
        }
        g2d.setTransform(reset);
    }

    /**
     * Updates the state of the banners.
     * This method is currently empty because the banners require no movement.
     */
    @Override
    public void update() {
    }
}
