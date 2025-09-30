/**
 The Water class represents a water object that can be drawn on the screen.
 It implements the DrawingObject class to render the water.

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

public class Water implements DrawingObject {
    private double xStartPosition;
    private double yStartPosition;
    private double waveOscillation;
    private double period;
    private boolean movingUp;
    private Color color;
    private double originalWaveOscillation;
    private int waves;
    private Person player;
    private Sound sound;

    /**
     * Constructs a Water object with the specified position, color, number of waves, wave oscillation, and player.
     * Initializes the water with the given parameters.
     *
     * @param xStartPosition the x-coordinate of the water's starting position
     * @param yStartPosition the y-coordinate of the water's starting position
     * @param color the color of the water
     * @param waves the number of waves
     * @param waveOscillation the oscillation of the waves
     * @param player the player interacting with the water
     */
    public Water(double xStartPosition, double yStartPosition, Color color, int waves, double waveOscillation, Person player) {
        this.xStartPosition = xStartPosition;
        this.yStartPosition = yStartPosition;
        this.color = color;
        this.waves = waves;
        this.waveOscillation = waveOscillation;
        this.originalWaveOscillation = waveOscillation;
        this.period = 1500.0 / waves * 2;
        this.movingUp = true;
        this.player = player;
        sound = new Sound("sea.wav");
    }

    /**
     * Draws the water using the provided Graphics2D object.
     * This method sets up the coordinates for the water and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Path2D.Double wave = new Path2D.Double();
        wave.moveTo(xStartPosition, yStartPosition);

        double currentWaveOscillation = waveOscillation;

        wave.curveTo(xStartPosition + (int) (period / 3), yStartPosition + currentWaveOscillation, xStartPosition + (int) (period * 2 / 3), yStartPosition + currentWaveOscillation, xStartPosition + period, yStartPosition);

        for (int i = 1; i < waves; i++) {
            currentWaveOscillation = -currentWaveOscillation;
            wave.curveTo(xStartPosition + (period * i) + (int) (period / 3), yStartPosition + currentWaveOscillation, xStartPosition + (period * i) + (int) (period * 2 / 3), yStartPosition + currentWaveOscillation, xStartPosition + period * (i + 1), yStartPosition);
        }

        wave.lineTo(xStartPosition + (period * waves), yStartPosition + 100);
        wave.lineTo(xStartPosition, yStartPosition + 100);
        wave.closePath();

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(10));
        g2d.fill(wave);
        update();
    }

    /**
     * Updates the state of the water.
     * This method handles the movement of the waves and the sound based on the player's position.
     */
    @Override
    public void update() {
        if (player.xPosition > 2400 && player.xPosition < 3200) {
            sound.loop(); 
        }
        else {
            sound.stop(); 
        }
        if (movingUp) {
            waveOscillation += 1;
            if (waveOscillation >= -(originalWaveOscillation)) {
                movingUp = false;
            }
        } else {
            waveOscillation -= 1;
            if (waveOscillation <= originalWaveOscillation) {
                movingUp = true;
            }
        }
    }
}
