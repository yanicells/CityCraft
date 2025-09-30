import java.awt.*;
import java.util.Random;

/**
 * The Particle class represents a particle that can be drawn and updated.
 * It implements the DrawingObject interface.
 */
public class Particle implements DrawingObject {
    private Vector location, velocity, acceleration;
    private int lifespan;
    private final boolean seed;
    private Vector previousLocation;
    private Random random;
    private boolean rain;

    /**
     * Constructs a Particle with the specified location and seed status.
     *
     * @param location the initial location of the particle
     * @param seed whether the particle is a seed
     */
    public Particle(Vector location, boolean seed) {
        this(location.getX(), location.getY(), seed);
    }

    /**
     * Constructs a Particle with the specified coordinates and seed status.
     *
     * @param x the initial x-coordinate of the particle
     * @param y the initial y-coordinate of the particle
     * @param seed whether the particle is a seed
     */
    public Particle(double x, double y, boolean seed) {
        this(x, y, seed, false);
    }

    /**
     * Constructs a Particle with the specified coordinates, seed status, and rain status.
     *
     * @param x the initial x-coordinate of the particle
     * @param y the initial y-coordinate of the particle
     * @param seed whether the particle is a seed
     * @param rain whether the particle represents rain
     */
    public Particle(double x, double y, boolean seed, boolean rain) {
        this.seed = seed;
        this.acceleration = new Vector(0, 0);
        random = new Random();
        if (seed) {
            this.velocity = rain ? new Vector(0, -(1 + random.nextDouble())) : new Vector(0, -(5 + random.nextDouble() * 6));
        } else {
            this.velocity = new Vector().multiply(4 + random.nextDouble() * 4);
        }
        this.rain = rain;
        this.location = new Vector(x, y);
        this.previousLocation = new Vector(x, y);
        this.lifespan = 255;
    }

    /**
     * Applies a force to the particle.
     *
     * @param force the force to apply
     */
    public void applyForce(Vector force) {
        acceleration.add(force);
    }

    /**
     * Draws the particle using the specified Graphics2D context.
     *
     * @param g2d the Graphics2D context to use for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        Line line = (rain) ?
                new Line((int) previousLocation.getX(), (int) previousLocation.getY(),
                        (int) location.getX(), (int) location.getY(), 2,
                        new Color(200, 220, 255, lifespan > 0 ? lifespan : 0)) :
                new Line((int) previousLocation.getX(), (int) previousLocation.getY(),
                        (int) location.getX(), (int) location.getY(), 2,
                        new Color(random.nextInt(0, 255), random.nextInt(0, 255),
                                random.nextInt(0, 255), lifespan > 0 ? lifespan : 0));
        line.draw(g2d);
    }

    /**
     * Updates the state of the particle.
     */
    @Override
    public void update() {
        previousLocation = new Vector(location.getX(), location.getY());
        velocity.add(acceleration);
        location.add(velocity);
        if (!seed) {
            lifespan -= 7;
            velocity.multiply(random.nextDouble(0.94, 0.98));
        }
        acceleration.multiply(0);
    }

    /**
     * Checks if the particle should explode.
     *
     * @return true if the particle is a seed and its velocity's y-component is greater than 0, false otherwise
     */
    public boolean explode() {
        return seed && velocity.getY() > 0;
    }

    /**
     * Checks if the particle is dead.
     *
     * @return true if the particle's lifespan is less than or equal to 0, false otherwise
     */
    public boolean isDead() {
        return lifespan <= 0;
    }

    /**
     * Gets the current location of the particle.
     *
     * @return the current location of the particle
     */
    public Vector getLocation() {
        return location;
    }
}
