/**
The Portal class represents a portal object that can be drawn on the screen.
It implements the DrawingObject class to render the portal.

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


public class Portal implements DrawingObject{
    
    /**
     * Constructs a Portal object.
     * Initializes the portal.
     */
    public Portal(){
    }

    /**
     * Draws the portal using the provided Graphics2D object.
     * This method sets up the coordinates for the portal and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();

        Square obsidianBlock = new Square(70, 380, 75, new Color(16, 12, 27));
        Square portalBlock = new Square(145, 380, 75, new Color(111,33,194));
        Square obsidianBlockShading = new Square(40, 380, 75, new Color(6, 3, 10));

        obsidianBlockShading.draw(g2d);
        g2d.translate(0, -75);
        obsidianBlockShading.draw(g2d);
        g2d.translate(0, -75);
        obsidianBlockShading.draw(g2d);
        g2d.translate(75, -75);
        obsidianBlockShading.draw(g2d);
        g2d.translate(0, 300);
        obsidianBlockShading.draw(g2d);
        g2d.setTransform(reset);

        obsidianBlock.draw(g2d);
        portalBlock.draw(g2d);

        g2d.translate(0,-75);
        obsidianBlock.draw(g2d);
        portalBlock.draw(g2d);
        g2d.translate(0,-75);
        obsidianBlock.draw(g2d);
        portalBlock.draw(g2d);
        g2d.translate(75,-75);
        obsidianBlock.draw(g2d);
        g2d.translate(0,300);
        obsidianBlock.draw(g2d);
        g2d.setTransform(reset);

        g2d.translate(75,0);
        portalBlock.draw(g2d);
        g2d.translate(0,-75);
        portalBlock.draw(g2d);
        g2d.translate(0,-75);
        portalBlock.draw(g2d);
        g2d.translate(75,-75);
        obsidianBlock.draw(g2d);
        g2d.translate(0,300);
        obsidianBlock.draw(g2d);
        g2d.translate(75,-75);
        obsidianBlock.draw(g2d);
        g2d.translate(0,-75);
        obsidianBlock.draw(g2d);
        g2d.translate(0,-75);
        obsidianBlock.draw(g2d);
        g2d.setTransform(reset);
    }

    /**
     * Updates the state of the portal.
     * This method is currently empty because the portal requires no movement.
     */
    @Override
    public void update() {
    }
}