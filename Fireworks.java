
/**
 The Fireworks class represents a collection of fireworks that can be drawn on the screen.
 It implements the DrawingObject class to render the fireworks.

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
import java.util.ArrayList;
import java.util.Random;

public class Fireworks implements DrawingObject {
    private ArrayList<Firework> fireworks;
    private Vector GRAVITY;
    private Random random;
    private ActionHandler actionHandler;
    private Person person;

    /**
     * Constructs a Fireworks object with the specified action handler and player.
     * Initializes the fireworks list, random generator, and gravity vector.
     *
     * @param actionHandler the action handler for user inputs
     * @param person        the Person object interacting with the fireworks
     */
    public Fireworks(ActionHandler actionHandler, Person person) {
        this.actionHandler = actionHandler;
        fireworks = new ArrayList<>();
        random = new Random();
        GRAVITY = new Vector(0, 0.13);
        this.person = person;
    }

    /**
     * Draws the fireworks using the provided Graphics2D object.
     * This method adds new fireworks based on user input and updates the state of
     * existing fireworks.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (actionHandler.firework) {
            fireworks.add(new Firework());
            actionHandler.firework = false;
        }

        for (int i = fireworks.size() - 1; i >= 0; i--) {
            Firework f = fireworks.get(i);
            f.run(g2d, GRAVITY);
            if (f.isDone()) {
                fireworks.remove(i);
            }
        }
    }

    /**
     * Updates the state of the fireworks.
     * This method is currently empty because the fireworks are updated within the
     * draw method.
     */
    @Override
    public void update() {

    }

    /**
     * The Firework class represents a single firework that can be drawn on the
     * screen.
     * It contains particles that simulate the explosion of the firework.
     */
    class Firework {
        private ArrayList<Particle> particles;
        private Particle firework;
        private Sound sound;

        /**
         * Constructs a Firework object.
         * Initializes the firework particle and sound, and creates an empty list for
         * explosion particles.
         */
        public Firework() {
            this.firework = new Particle(random.nextInt(person.xPosition - 400, person.xPosition + 400), 400, true);
            sound = new Sound("assets/firework.wav");
            particles = new ArrayList<>();
        }

        /**
         * Runs the firework simulation, updating and drawing the firework and its
         * particles.
         *
         * @param g       the Graphics2D object used for drawing
         * @param gravity the gravity vector applied to the particles
         */
        public void run(Graphics2D g, Vector gravity) {
            if (firework != null) {
                firework.applyForce(gravity);
                firework.update();
                firework.draw(g);

                if (firework.explode()) {
                    sound.play(800000);
                    for (int i = 0; i < 80; i++) {
                        particles.add(new Particle(firework.getLocation(), false));
                    }
                    firework = null;
                }
            }

            for (int i = particles.size() - 1; i >= 0; i--) {
                Particle p = particles.get(i);
                p.applyForce(gravity);
                p.update();
                p.draw(g);
                if (p.isDead()) {
                    particles.remove(i);
                }
            }
        }

        /**
         * Checks if the firework is done exploding.
         *
         * @return true if the firework and all its particles are done, false otherwise
         */
        public boolean isDone() {
            return firework == null && particles.isEmpty();
        }
    }
}