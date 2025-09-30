/**
The Road class represents a road object that can be drawn on the screen.
It implements the DrawingObject class to render the road.

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

public class Road implements DrawingObject{

    /**
     * Constructs a Road object.
     * Initializes the road.
     */
    public Road(){
    }

    /**
     * Draws the road using the provided Graphics2D object.
     * This method sets up the coordinates for the road and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();

        Rectangle road = new Rectangle(-1000, 520, 4800, 80, Color.BLACK);
        Rectangle pavement = new Rectangle(-800, 500, 3200, 20, new Color(50,52,56));
        Rectangle pavementShading = new Rectangle(-800, 520, 800, 5, new Color(33,35,38));
        Line pavementLine1 = new Line(-740, 500, -720, 520, 2, new Color(16,18,17));
        Line pavementLine2 = new Line(-720, 520,-720, 525, 2, new Color(16,18,17));

        Path2D.Double lines = new Path2D.Double();
        lines.moveTo(-750,550);
        lines.lineTo(-630, 550);
        lines.lineTo(-610,570);
        lines.lineTo(-730,570);
        lines.closePath();

        road.draw(g2d);
        g2d.setColor(Color.YELLOW);
        g2d.fill(lines);
        pavement.draw(g2d);
        pavementShading.draw(g2d);
        pavementLine1.draw(g2d);
        pavementLine2.draw(g2d);

        for (int i = 0; i<25; i++){
            g2d.translate(180,0);
            g2d.setColor(Color.YELLOW);
            g2d.fill(lines);
            pavement.draw(g2d);
            pavementShading.draw(g2d);
            pavementLine1.draw(g2d);
            pavementLine2.draw(g2d);
        }
        g2d.setTransform(reset);
    }

    /**
     * Updates the state of the road.
     * This method is currently empty because the road requires no movement.
     */
    @Override
    public void update() {
    }
}
