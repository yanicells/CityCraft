
/**
 The CityBackground class represents the background of a city scene.
 It implements the DrawingObject class to render the cityscape, including buildings and sound effects.

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
import java.util.Random;

public class CityBackground implements DrawingObject {
    private Building[] buildings;
    private Sound sound;
    private Person player;

    /**
     * Constructs a CityBackground object with the specified base Y-coordinate and
     * player.
     * Initializes the buildings and sound for the city background.
     *
     * @param baseY  the base Y-coordinate for the city background
     * @param player the player object interacting with the city background
     */
    public CityBackground(int baseY, Person player) {
        this.player = player;
        buildings = new Building[] {
                new Building(800, baseY, 10, 9),
                new Building(820, baseY, 12, 8),
                new Building(900, baseY, 8, 6),
                new Building(970, baseY, 15, 9),
                new Building(1050, baseY, 10, 6),
                new Building(1120, baseY, 14, 5),
                new Building(1200, baseY, 9, 7),
                new Building(1270, baseY, 13, 6),
                new Building(1340, baseY, 11, 5),
                new Building(1410, baseY, 12, 7),
                new Building(1510, baseY, 8, 9)
        };
        sound = new Sound("assets/traffic.wav");
    }

    /**
     * Draws the city background using the provided Graphics2D object.
     * This method iterates through the buildings and draws each one on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        for (Building building : buildings) {
            building.draw(g2d);
        }
        update();
    }

    /**
     * Updates the state of the city background.
     * This method controls the looping of the traffic sound based on the player's
     * position.
     */
    @Override
    public void update() {
        if (player.xPosition > 800 && player.xPosition < 1600) {
            sound.loop();
        } else {
            sound.stop();
        }
    }

    /**
     * The Building class represents a building object within the city background.
     * It implements the DrawingObject class to render the building.
     */
    class Building implements DrawingObject {
        private int x;
        private int baseY;
        private int width;
        private int height;
        private Random random;

        /**
         * Constructs a Building object with the specified position and dimensions.
         * Initializes the building with the given parameters.
         *
         * @param x      the x-coordinate of the building
         * @param baseY  the base Y-coordinate of the building
         * @param width  the width of the building
         * @param height the height of the building
         */
        public Building(int x, int baseY, int width, int height) {
            this.x = x;
            this.baseY = baseY;
            this.width = width * 10; // Scale up
            this.height = height * 30;
            random = new Random();
        }

        /**
         * Draws the building using the provided Graphics2D object.
         * This method sets up the coordinates for the building and uses transformations
         * to position and draw it on the screen.
         *
         * @param g2d the Graphics2D object used for drawing
         */
        @Override
        public void draw(Graphics2D g2d) {
            AffineTransform reset = g2d.getTransform();

            Rectangle buildingShading = new Rectangle(x, baseY - height, width, height, new Color(50, 50, 50));
            Rectangle building = new Rectangle(x, baseY - height, width, height, new Color(30, 30, 30));

            buildingShading.draw(g2d);
            g2d.translate(10, 0);
            building.draw(g2d);
            g2d.setTransform(reset);

            for (int i = baseY - height + 10; i < baseY - 30; i += 30) {
                for (int j = x + 20; j < x + width - 5; j += 30) {
                    int blink = random.nextInt(1, 250);
                    Rectangle window = new Rectangle(j, i, 15, 10, blink == 10 ? Color.yellow : Color.yellow.darker());
                    window.draw(g2d);
                }
            }
        }

        /**
         * Updates the state of the building.
         * This method is currently empty because the building requires no movement.
         */
        @Override
        public void update() {
        }
    }
}
