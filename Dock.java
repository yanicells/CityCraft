/**
 The Dock class represents a dock object that can be drawn on the screen.
 It implements the DrawingObject class to render the dock.

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
import java.awt.geom.Path2D;

public class Dock implements DrawingObject{
    private int start = 2400;

    /**
     * Draws the dock using the provided Graphics2D object.
     * This method sets up the coordinates for the dock and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        Fence fence1 = new Fence(400,2400);
        Fence fence2 = new Fence(600,3000);

        Path2D.Double ledge = new Path2D.Double();
        ledge.moveTo(start+420,500);
        ledge.lineTo(start+480, 400);
        ledge.lineTo(start+540, 400);
        ledge.lineTo(start+600, 500);
        ledge.closePath();

        Rectangle post1 = new Rectangle(start+480, 375, 10, 30, new Color(46, 20, 5));
        Rectangle post2 = new Rectangle(start+453, 400, 15, 50, new Color(46, 20, 5));
        Rectangle post3 = new Rectangle(start+425, 425, 20, 70, new Color(46, 20, 5));

        Line fishingRod = new Line(start+460, 300,start+500, 410, 2, new Color(130, 61, 21));
        Line fishingLine = new Line(start+460, 300,start+460, 500, 1, Color.BLACK);
        Circle fishingReel = new Circle(start+490, 390, 5, new Color(71, 60, 54), true);
        Line wood1 = new Line(start+430, 475, start+580, 475, 2, new Color(33, 14, 3));
        Line wood2 = new Line(start+460, 430, start+560, 430, 2, new Color(33, 14, 3));

        fishingLine.draw(g2d);
        fence1.draw(g2d);
        fence2.draw(g2d);
        g2d.setColor(new Color(61, 28, 9));
        g2d.fill(ledge);
        wood1.draw(g2d);
        wood2.draw(g2d);

        post1.draw(g2d);
        post2.draw(g2d);
        post3.draw(g2d);
        g2d.translate(50, 0);
        post1.draw(g2d);
        g2d.translate(50, 0);
        post2.draw(g2d);
        g2d.translate(50, 0);
        post3.draw(g2d);
        g2d.setTransform(reset);
        fishingRod.draw(g2d);
        fishingReel.draw(g2d);
    }

    /**
     * Updates the state of the dock.
     * This method is currently empty because the dock requires no movement.
     */
    @Override
    public void update() {
    }
}
