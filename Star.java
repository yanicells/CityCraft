/**
The Star class represents a star object that can be drawn on the screen.
It implements the DrawingObject class to render the star.

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
import java.awt.geom.Path2D;
import java.util.Random;

public class Star implements DrawingObject {
    private double centerX;
    private double centerY;
    private double radiusOuter;
    private double radiusInner;
    private Color color;
    private Random random;
    private Graphics2D graphics2D;
    private Path2D.Double star;
    private Moon moon;
    private int tracker;

    /**
     * Constructs a Star object with the specified center coordinates, outer radius, number of points, color, and moon.
     * Initializes the star with the given parameters.
     *
     * @param centerX the x-coordinate of the star's center
     * @param centerY the y-coordinate of the star's center
     * @param radiusOuter the outer radius of the star
     * @param points the number of points of the star
     * @param color the color of the star
     * @param moon the Moon object
     */
    public Star(double centerX, double centerY, double radiusOuter, Color color, Moon moon) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusOuter = radiusOuter;
        this.radiusInner = radiusOuter / 2;
        this.color = color;
        this.moon = moon;
        random = new Random();
        tracker = random.nextInt(-30, 20);
    }

    /**
     * Draws the star using the provided Graphics2D object.
     * This method sets up the coordinates for the star and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (moon.isVisible()) {
            star = new Path2D.Double();
            graphics2D = g2d;

            for (int i = 0; i < 8; i++) {
                double angle = Math.toRadians(45) * i;
                double radius = (i % 2 == 0) ? radiusOuter : radiusInner;

                double x = centerX + Math.cos(angle) * radius;
                double y = centerY + Math.sin(angle) * radius;

                if (i == 0) {
                    star.moveTo(x, y);
                } else {
                    star.lineTo(x, y);
                }
            }

            star.closePath();
            g2d.setColor(color);
            g2d.fill(star);

            update();
            tracker++;
        }
    }

    /**
     * Updates the state of the star.
     * This method handles the blinking effect of the star.
     */
    @Override
    public void update() {
        if (tracker >= 100 && tracker <= 180) {
            graphics2D.setColor(Color.black);
            graphics2D.fill(star);
        } else if (tracker > 190) {
            tracker = 0;
        }
    }
}
