/**
The Rain class represents a rain effect that can be drawn on the screen.
It implements the DrawingObject class to render the rain.

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

public class Rain implements DrawingObject{
    private ActionHandler actionHandler;
    private Random random;
    private Vector GRAVITY;
    private ArrayList<Particle> rain;
    private Sound sound;
    private Graphics2D g2d;

    /**
     * Constructs a Rain object with the specified action handler.
     * Initializes the rain effect with the given parameters.
     *
     * @param actionHandler the action handler for user inputs
     */
    public Rain(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
        rain = new ArrayList<>();
        random = new Random();
        GRAVITY = new Vector(0, 1);
        sound = new Sound("rain.wav");
    }

    /**
     * Draws the rain effect using the provided Graphics2D object.
     * This method adds new raindrops based on user input and updates the state of existing raindrops.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        if(actionHandler.rain){
            sound.loop();
            rain.add(new Particle(random.nextInt(-200, 3400), random.nextInt(-50, 100), true, true));
        }
        else{
            sound.stop();
        }
        update();
    }

    /**
     * Updates the state of the rain.
     * This method iterates through the list of raindrop particles, applies gravity to each,
     * updates their state, draws them, and removes them if they are dead.
     */
    @Override
    public void update() {
        for(int i = rain.size() - 1; i >= 0; i--){
            Particle droplet = rain.get(i);
            if (droplet != null) {
                droplet.applyForce(GRAVITY);
                droplet.update();
                droplet.draw(g2d);
                if(droplet.isDead()){
                    rain.remove(droplet);
                }
            }
        }
    }
}
