/**
The Stalls class represents a stall object that can be drawn on the screen.
It implements the DrawingObject class to render the stall.

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

public class Stalls implements DrawingObject{
    private Color color1;
    private Color color2;
    private int xPosition;

    /**
     * Constructs a Stalls object with the specified colors and position.
     * Initializes the stall with the given parameters.
     *
     * @param color1 the first color of the stall
     * @param color2 the second color of the stall
     * @param xPosition the x-coordinate of the stall
     */
    public Stalls (Color color1, Color color2, int xPosition){
        this.color1 = color1;
        this.color2 = color2;
        this.xPosition = xPosition;
    }

    /**
     * Draws the stall using the provided Graphics2D object.
     * This method sets up the coordinates for the stall and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        Path2D.Double roof = new Path2D.Double();
        roof.moveTo(xPosition, 300);
        roof.lineTo(xPosition+50, 255);
        roof.lineTo(xPosition+200, 255);
        roof.lineTo(xPosition+250, 300);
        roof.closePath();

        Square red = new Square(xPosition, 300, 25, color1);
        Square white = new Square(xPosition+25, 300, 25, color2);
        Circle redCircle = new Circle(xPosition, 312, 25, color1, true);
        Circle whiteCircle = new Circle(xPosition+25, 312, 25, color2, true);

        Rectangle support = new Rectangle(xPosition, 300, 15, 250, new Color(105, 54, 50));    
        Rectangle supportShading = new Rectangle(xPosition, 300, 15, 250, new Color(105, 54, 50).darker());    
        
        Path2D.Double tableTop = new Path2D.Double();
        tableTop.moveTo(xPosition-3,410);
        tableTop.lineTo(xPosition-15, 430);
        tableTop.lineTo(xPosition+253, 430);
        tableTop.lineTo(xPosition+265, 430);
        tableTop.lineTo(xPosition+253, 410);
        tableTop.closePath();

        Rectangle tableBottom = new Rectangle(xPosition-15, 430, 280, 10, new Color(74, 43, 40));
        Rectangle redBottom = new Rectangle(xPosition, 440, 25, 80, color1);
        Rectangle whiteBottom = new Rectangle(xPosition+25, 440, 25, 80, color2);
        Rectangle back =  new Rectangle(xPosition,300,250,200,new Color(71, 55, 46));
        back.draw(g2d);

        g2d.translate(5,0);
        supportShading.draw(g2d);
        g2d.setTransform(reset);
        support.draw(g2d);

        g2d.translate(230,0);
        supportShading.draw(g2d);
        g2d.translate(5,0);
        support.draw(g2d);
        g2d.setTransform(reset);
        g2d.setColor(color1);
        g2d.fill(roof);

        for (int i = 0; i <= 4; i++) {
            g2d.translate(i * 50, 0);

            red.draw(g2d);
            white.draw(g2d);
            redCircle.draw(g2d);
            whiteCircle.draw(g2d);
            redBottom.draw(g2d);
            whiteBottom.draw(g2d);

            g2d.setTransform(reset);
        }

        g2d.setColor(new Color(120, 72, 68));
        g2d.fill(tableTop);
        tableBottom.draw(g2d);
}

    /**
     * Updates the state of the stall.
     * This method is currently empty because the stall requires no movement.
     */
    @Override
    public void update() {

    }
}
