
/**
The Person class represents a person object that can be drawn on the screen.
It implements the DrawingObject class to render the person.

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
import java.util.Random;

public class Person implements DrawingObject {

    protected int xPosition;
    private int yPosition;
    private int originalYPosition;
    private int speed;
    private KeyHandler keyListener;
    protected String direction;
    private int position;
    private int tracker;
    private Graphics2D graphics2D;
    private AffineTransform resetTransform;
    private Color hairColor;
    private Color shirtColor;
    private Color legsColor;
    private boolean boy;

    private boolean jumping;
    private boolean falling;
    private double gravity;
    private double velocity;

    private int maxLeftMovement;
    private int maxRightMovement;

    private Random random;

    private Sound walkingSound;
    private Sound jumpingSound;

    private int movement;

    /**
     * Constructs a Person object with the specified position, key listener, and
     * colors.
     * Initializes the person with the given parameters.
     *
     * @param xPosition   the x-coordinate of the person
     * @param yPosition   the y-coordinate of the person
     * @param keyListener the key listener for user inputs
     * @param hairColor   the color of the person's hair
     * @param shirtColor  the color of the person's shirt
     * @param legsColor   the color of the person's legs
     * @param boy         whether the person is a boy
     */
    public Person(int xPosition, int yPosition, KeyHandler keyListener, Color hairColor, Color shirtColor,
            Color legsColor, boolean boy) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        originalYPosition = yPosition;
        speed = 2;
        this.keyListener = keyListener;
        position = 1;
        direction = "left";
        this.hairColor = hairColor;
        this.shirtColor = shirtColor;
        this.legsColor = legsColor;
        this.boy = boy;
        jumping = false;
        falling = false;
        velocity = 0;
        gravity = 0.5;
        maxLeftMovement = -100;
        maxRightMovement = 3150;
        walkingSound = new Sound("assets/step.wav");
        jumpingSound = new Sound("assets/jump.wav");
        random = new Random();
        movement = 4;
    }

    /**
     * Draws the person using the provided Graphics2D object.
     * This method sets up the coordinates for the person and uses transformations
     * to position and draw them on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        resetTransform = reset;
        graphics2D = g2d;

        tracker++;

        if (tracker > 12) {
            if (position == 1) {
                position = 2;
            } else if (position == 2) {
                position = 3;
            } else if (position == 3) {
                position = 4;
            } else if (position == 4) {
                position = 1;
            }
            tracker = 0;
        }
        update();
    }

    /**
     * Updates the state of the person.
     * This method handles the movement and jumping logic based on user input or
     * random movement.
     */
    @Override
    public void update() {
        if (keyListener == null) {
            moveNPC();
            return;
        }

        if ((keyListener.leftPressed || keyListener.rightPressed || keyListener.upPressed)) {
            if (keyListener.leftPressed) {
                moveLeft();
            } else if (keyListener.rightPressed) {
                moveRight();
            } else if (keyListener.upPressed && !jumping && !falling) {
                triggerJump();
            }
        } else {
            walkingSound.stop();
            drawIdle();
        }
        drawJump();
    }

    /**
     * Checks if the person can move left.
     *
     * @return true if the person can move left, false otherwise
     */
    public boolean canMoveLeft() {
        return xPosition > maxLeftMovement;
    }

    /**
     * Checks if the person can move right.
     *
     * @return true if the person can move right, false otherwise
     */
    public boolean canMoveRight() {
        return xPosition < maxRightMovement;
    }

    /**
     * Moves the non-player character (NPC) based on random movement logic.
     * The NPC can move left, right, or jump based on random conditions.
     * If no movement is triggered, the NPC will idle.
     */
    private void moveNPC() {
        movement = (random.nextInt(1, 90) == 10) ? random.nextInt(1, 5) : movement;
        if (movement == 1) {
            moveLeft();
        } else if (movement == 2 || movement == 4) {
            moveRight();
        } else if (movement == 3 && !jumping && !falling && random.nextInt(1, 180) == 3) {
            triggerJump();
            movement = 0;
        } else {
            walkingSound.stop();
            drawIdle();
        }
        drawJump();
    }

    /**
     * Moves the person to the left.
     * This method updates the position and direction of the person and plays the
     * walking animation.
     */
    private void moveLeft() {
        xPosition -= (xPosition > maxLeftMovement) ? speed : 0;
        direction = "left";
        if (position == 1) {
            walkingSound.loop();
            leftSideView(graphics2D, resetTransform);
        } else if (position == 2) {
            walkingLeftOne(graphics2D, resetTransform);
        } else if (position == 3) {
            leftSideView(graphics2D, resetTransform);
        } else if (position == 4) {
            walkingLeftTwo(graphics2D, resetTransform);
        }
    }

    /**
     * Moves the person to the right.
     * This method updates the position and direction of the person and plays the
     * walking animation.
     */
    private void moveRight() {
        walkingSound.loop();
        xPosition += (xPosition < maxRightMovement) ? speed : 0;
        direction = "right";
        if (position == 1) {
            rightSideView(graphics2D, resetTransform);
        } else if (position == 2) {
            walkingRightOne(graphics2D, resetTransform);
        } else if (position == 3) {
            rightSideView(graphics2D, resetTransform);
        } else if (position == 4) {
            walkingRightTwo(graphics2D, resetTransform);
        }
    }

    /**
     * Triggers the jump action for the person.
     * This method sets the jumping state and plays the jumping sound.
     */
    private void triggerJump() {
        jumpingSound.play(0);
        jumping = true;
        if (keyListener != null) {
            keyListener.upPressed = false;
        }
        velocity = -speed * 3;
        if (direction.equals("left")) {
            drawBackLeft(graphics2D, resetTransform);
        } else if (direction.equals("right")) {
            drawBackRight(graphics2D, resetTransform);
        }
    }

    /**
     * Draws the jumping animation for the person.
     * This method updates the position and velocity of the person during the jump.
     */
    private void drawJump() {
        if (jumping || falling) {
            if (jumping) {
                yPosition += velocity;
                velocity += gravity;

                if (velocity >= 0) { // Switch from jumping to falling
                    jumping = false;
                    falling = true;
                }
            } else if (falling) {
                yPosition += velocity;
                velocity += gravity; // Apply gravity effect

                if (yPosition >= originalYPosition) { // Land safely
                    yPosition = originalYPosition;
                    falling = false;
                    velocity = 0; // Reset velocity
                }
            }

            if (keyListener != null) {
                if (keyListener.upPressed) {
                    drawIdle();
                }
            }
        }
    }

    /**
     * Draws the idle animation for the person.
     * This method sets the person's position to idle based on the direction they
     * are facing.
     */
    private void drawIdle() {
        if (direction.equals("left")) {
            drawBackLeft(graphics2D, resetTransform);
        } else if (direction.equals("right")) {
            drawBackRight(graphics2D, resetTransform);
        }
    }

    /**
     * Draws the back left view of the person.
     * This method sets up the coordinates for the back left view and uses
     * transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void drawBackLeft(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(165, 126, 110));
        Rectangle hair = new Rectangle(xPosition, yPosition, 100, boy ? 90 : 100, hairColor.darker());
        Rectangle hairExtension = new Rectangle(boy ? xPosition + 10 : xPosition + 30,
                boy ? yPosition + 90 : yPosition + 130, boy ? 80 : 40, 10, hairColor.darker());
        Rectangle body = new Rectangle(xPosition + 10, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 30, yPosition + 150, 40, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 10, yPosition + 150, 70, 50, legsColor.darker());
        Rectangle shoes = new Rectangle(xPosition + 10, yPosition + 200, 70, 10, new Color(64, 64, 64));
        Rectangle handSleeveShading = new Rectangle(xPosition + 80, yPosition + 100, 10, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 80, yPosition + 120, 10, 45, new Color(165, 126, 110));
        Square handSleeveLeft = new Square(xPosition - 20, yPosition + 100, 20, shirtColor);
        Rectangle handLeft = new Rectangle(xPosition - 20, yPosition + 120, 20, 45, new Color(174, 138, 122));
        Rectangle sideLegs = new Rectangle(xPosition - 10, yPosition + 165, 20, 35, legsColor);
        Rectangle sideShoes = new Rectangle(xPosition - 10, yPosition + 200, 20, 10, new Color(96, 96, 96));
        Rectangle sideHead = new Rectangle(xPosition - 30, yPosition, 30, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition - 30, yPosition, 30, 40, hairColor);
        Square sideHair2 = new Square(xPosition - 30, yPosition + 40, 10, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition - 10, yPosition + 40, 10, 30, hairColor);
        Line legsPartition = new Line(xPosition + 50, yPosition + 162, xPosition + 50, yPosition + 209, 2, Color.BLACK);

        head.draw(g2d);
        hair.draw(g2d);
        legs.draw(g2d);
        body.draw(g2d);
        bodyTuck.draw(g2d);
        hairExtension.draw(g2d);
        if (!boy) {
            Rectangle longHair = new Rectangle(xPosition + 10, yPosition + 100, 80, 30, hairColor.darker());
            longHair.draw(g2d);
        }
        shoes.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.translate(-80, 0);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.setTransform(reset);
        handSleeveLeft.draw(g2d);
        handLeft.draw(g2d);
        sideLegs.draw(g2d);
        sideShoes.draw(g2d);
        sideHead.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        legsPartition.draw(g2d);
    }

    /**
     * Draws the back right view of the person.
     * This method sets up the coordinates for the back right view and uses
     * transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void drawBackRight(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(165, 126, 110));
        Rectangle hair = new Rectangle(xPosition, yPosition, 100, boy ? 90 : 100, hairColor.darker());
        Rectangle hairExtension = new Rectangle(boy ? xPosition + 10 : xPosition + 30,
                boy ? yPosition + 90 : yPosition + 130, boy ? 80 : 40, 10, hairColor.darker());
        Rectangle body = new Rectangle(xPosition + 10, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 30, yPosition + 150, 40, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 10, yPosition + 150, 70, 50, legsColor.darker());
        Rectangle shoes = new Rectangle(xPosition + 10, yPosition + 200, 70, 10, new Color(64, 64, 64));
        Rectangle handSleeveShading = new Rectangle(xPosition + 80, yPosition + 100, 10, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 80, yPosition + 120, 10, 45, new Color(165, 126, 110));
        Square handSleeveLeft = new Square(xPosition - 20, yPosition + 100, 20, shirtColor);
        Rectangle handLeft = new Rectangle(xPosition - 20, yPosition + 120, 20, 45, new Color(174, 138, 122));
        Rectangle sideLegs = new Rectangle(xPosition - 10, yPosition + 165, 20, 35, legsColor);
        Rectangle sideShoes = new Rectangle(xPosition - 10, yPosition + 200, 20, 10, new Color(96, 96, 96));
        Rectangle sideHead = new Rectangle(xPosition - 30, yPosition, 30, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition - 30, yPosition, 30, 40, hairColor);
        Square sideHair2 = new Square(xPosition - 30, yPosition + 40, 10, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition - 10, yPosition + 40, 10, 30, hairColor);
        Line legsPartition = new Line(xPosition + 50, yPosition + 162, xPosition + 50, yPosition + 209, 2, Color.BLACK);

        head.draw(g2d);
        hair.draw(g2d);
        hairExtension.draw(g2d);
        g2d.translate(10, 0);
        legs.draw(g2d);
        body.draw(g2d);
        shoes.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.translate(-80, 0);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);

        g2d.setTransform(reset);
        bodyTuck.draw(g2d);
        legsPartition.draw(g2d);
        hairExtension.draw(g2d);
        if (!boy) {
            Rectangle longHair = new Rectangle(xPosition + 10, yPosition + 100, 80, 30, hairColor.darker());
            longHair.draw(g2d);
        }

        g2d.setTransform(reset);
        g2d.translate(130, 0);
        sideHead.draw(g2d);
        sideHair1.draw(g2d);
        g2d.translate(20, 0);
        sideHair2.draw(g2d);
        g2d.translate(-40, 0);
        sideHair3.draw(g2d);

        g2d.setTransform(reset);
        g2d.translate(120, 0);
        handSleeveLeft.draw(g2d);
        handLeft.draw(g2d);
        g2d.translate(-20, 0);
        sideLegs.draw(g2d);
        sideShoes.draw(g2d);
        g2d.setTransform(reset);
    }

    /**
     * Draws the left side view of the person.
     * This method sets up the coordinates for the left side view and uses
     * transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void leftSideView(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition, yPosition, 100, 40, hairColor);
        Square sideHair2 = new Square(xPosition + 60, yPosition + 40, 40, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition, yPosition + 40, 40, 20, hairColor);
        Rectangle body = new Rectangle(xPosition + 20, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 85, yPosition + 150, 3, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 20, yPosition + 150, 70, 50, legsColor);
        Rectangle shoes = new Rectangle(xPosition + 20, yPosition + 200, 70, 10, new Color(96, 96, 96));
        Rectangle handSleeve = new Rectangle(xPosition + 35, yPosition + 100, 35, 20, shirtColor);
        Rectangle hand = new Rectangle(xPosition + 35, yPosition + 120, 35, 45, new Color(174, 138, 122));
        Rectangle handSleeveShading = new Rectangle(xPosition + 65, yPosition + 100, 6, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 65, yPosition + 120, 6, 45, new Color(165, 126, 110));
        Square shoesShading = new Square(xPosition + 80, yPosition + 200, 10, new Color(64, 64, 64));
        Rectangle bodyShading = new Rectangle(xPosition + 80, yPosition + 100, 10, 50, shirtColor);
        Rectangle legShading = new Rectangle(xPosition + 80, yPosition + 150, 10, 50, legsColor.darker());
        Rectangle hairShading1 = new Rectangle(xPosition + 90, yPosition, 10, boy ? 80 : 130, hairColor.darker());
        Rectangle hairShading2 = new Rectangle(xPosition + 95, yPosition + 80, 5, 10, hairColor.darker());
        Rectangle headShading = new Rectangle(xPosition + 90, yPosition + 80, 10, 20, new Color(165, 126, 110));

        head.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        body.draw(g2d);
        legs.draw(g2d);
        shoes.draw(g2d);
        shoesShading.draw(g2d);
        bodyShading.draw(g2d);
        legShading.draw(g2d);
        hairShading1.draw(g2d);
        if (!boy) {
            Rectangle hairExtension = new Rectangle(xPosition + 90, yPosition + 130, 5, 10, hairColor.darker());
            hairExtension.draw(g2d);
        } else {
            headShading.draw(g2d);
            hairShading2.draw(g2d);
        }
        bodyTuck.draw(g2d);
        hand.draw(g2d);
        handSleeve.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
    }

    /**
     * Draws the first walking left animation frame for the person.
     * This method sets up the coordinates for the first walking left frame and uses
     * transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void walkingLeftOne(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition, yPosition, 100, 40, hairColor);
        Square sideHair2 = new Square(xPosition + 60, yPosition + 40, 40, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition, yPosition + 40, 40, 20, hairColor);
        Rectangle body = new Rectangle(xPosition + 20, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 85, yPosition + 150, 3, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 20, yPosition + 150, 70, 50, legsColor);
        Rectangle shoes = new Rectangle(xPosition + 20, yPosition + 200, 70, 10, new Color(96, 96, 96));
        Rectangle handSleeve = new Rectangle(xPosition + 35, yPosition + 100, 35, 20, shirtColor);
        Rectangle hand = new Rectangle(xPosition + 35, yPosition + 120, 35, 45, new Color(174, 138, 122));
        Rectangle handSleeveShading = new Rectangle(xPosition + 65, yPosition + 100, 6, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 65, yPosition + 120, 6, 45, new Color(165, 126, 110));
        Square shoesShading = new Square(xPosition + 80, yPosition + 200, 10, new Color(64, 64, 64));
        Rectangle bodyShading = new Rectangle(xPosition + 80, yPosition + 100, 10, 50, shirtColor);
        Rectangle legShading = new Rectangle(xPosition + 80, yPosition + 150, 10, 50, legsColor.darker());
        Rectangle hairShading1 = new Rectangle(xPosition + 90, yPosition, 10, boy ? 80 : 130, hairColor.darker());
        Rectangle hairShading2 = new Rectangle(xPosition + 95, yPosition + 80, 5, 10, hairColor.darker());
        Rectangle headShading = new Rectangle(xPosition + 90, yPosition + 80, 10, 20, new Color(165, 126, 110));

        head.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);

        g2d.rotate(Math.toRadians(25), xPosition + 20, yPosition + 150);
        g2d.translate(0, -20);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);
        g2d.setTransform(reset);

        body.draw(g2d);
        bodyShading.draw(g2d);
        hairShading1.draw(g2d);
        if (!boy) {
            Rectangle hairExtension = new Rectangle(xPosition + 90, yPosition + 130, 5, 10, hairColor.darker());
            hairExtension.draw(g2d);
        } else {
            headShading.draw(g2d);
            hairShading2.draw(g2d);
        }
        bodyTuck.draw(g2d);

        g2d.rotate(Math.toRadians(-25), xPosition + 35, yPosition + 100);
        hand.draw(g2d);
        handSleeve.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.setTransform(reset);
    }

    /**
     * Draws the second walking left animation frame for the person.
     * This method sets up the coordinates for the second walking left frame and
     * uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void walkingLeftTwo(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition, yPosition, 100, 40, hairColor);
        Square sideHair2 = new Square(xPosition + 60, yPosition + 40, 40, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition, yPosition + 40, 40, 20, hairColor);
        Rectangle body = new Rectangle(xPosition + 20, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 85, yPosition + 150, 3, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 20, yPosition + 150, 70, 50, legsColor);
        Rectangle shoes = new Rectangle(xPosition + 20, yPosition + 200, 70, 10, new Color(96, 96, 96));
        Rectangle handSleeve = new Rectangle(xPosition + 35, yPosition + 100, 35, 20, shirtColor);
        Rectangle hand = new Rectangle(xPosition + 35, yPosition + 120, 35, 45, new Color(174, 138, 122));
        Rectangle handSleeveShading = new Rectangle(xPosition + 65, yPosition + 100, 6, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 65, yPosition + 120, 6, 45, new Color(165, 126, 110));
        Square shoesShading = new Square(xPosition + 80, yPosition + 200, 10, new Color(64, 64, 64));
        Rectangle bodyShading = new Rectangle(xPosition + 80, yPosition + 100, 10, 50, shirtColor);
        Rectangle legShading = new Rectangle(xPosition + 80, yPosition + 150, 10, 50, legsColor.darker());
        Rectangle hairShading1 = new Rectangle(xPosition + 90, yPosition, 10, boy ? 80 : 130, hairColor.darker());
        Rectangle hairShading2 = new Rectangle(xPosition + 95, yPosition + 80, 5, 10, hairColor.darker());
        Rectangle headShading = new Rectangle(xPosition + 90, yPosition + 80, 10, 20, new Color(165, 126, 110));

        head.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);

        g2d.rotate(Math.toRadians(-25), xPosition + 90, yPosition + 150);
        g2d.translate(0, -20);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);
        g2d.setTransform(reset);

        body.draw(g2d);
        bodyShading.draw(g2d);
        hairShading1.draw(g2d);
        if (!boy) {
            Rectangle hairExtension = new Rectangle(xPosition + 90, yPosition + 130, 5, 10, hairColor.darker());
            hairExtension.draw(g2d);
        } else {
            headShading.draw(g2d);
            hairShading2.draw(g2d);
        }
        bodyTuck.draw(g2d);

        g2d.rotate(Math.toRadians(25), xPosition + 35, yPosition + 100);
        hand.draw(g2d);
        handSleeve.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.setTransform(reset);

    }

    /**
     * Draws the right side view of the person.
     * This method sets up the coordinates for the right side view and uses
     * transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void rightSideView(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition, yPosition, 100, 40, hairColor);
        Square sideHair2 = new Square(xPosition, yPosition + 40, 40, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition + 60, yPosition + 40, 40, 20, hairColor);
        Rectangle body = new Rectangle(xPosition + 20, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 10, yPosition + 150, 3, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 20, yPosition + 150, 70, 50, legsColor);
        Rectangle shoes = new Rectangle(xPosition + 20, yPosition + 200, 70, 10, new Color(96, 96, 96));
        Rectangle handSleeve = new Rectangle(xPosition + 35, yPosition + 100, 35, 20, shirtColor);
        Rectangle hand = new Rectangle(xPosition + 35, yPosition + 120, 35, 45, new Color(174, 138, 122));
        Rectangle handSleeveShading = new Rectangle(xPosition + 35, yPosition + 100, 6, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 35, yPosition + 120, 6, 45, new Color(165, 126, 110));
        Square shoesShading = new Square(xPosition + 10, yPosition + 200, 10, new Color(64, 64, 64));
        Rectangle bodyShading = new Rectangle(xPosition + 10, yPosition + 100, 10, 50, shirtColor);
        Rectangle legShading = new Rectangle(xPosition + 10, yPosition + 150, 10, 50, legsColor.darker());
        Rectangle hairShading1 = new Rectangle(xPosition, yPosition, 10, boy ? 80 : 130, hairColor.darker());
        Rectangle hairShading2 = new Rectangle(xPosition, yPosition + 80, 5, 10, hairColor.darker());
        Rectangle headShading = new Rectangle(xPosition, yPosition + 80, 10, 20, new Color(165, 126, 110));

        head.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        body.draw(g2d);
        legs.draw(g2d);
        shoes.draw(g2d);
        shoesShading.draw(g2d);
        bodyShading.draw(g2d);
        legShading.draw(g2d);
        hairShading1.draw(g2d);
        if (!boy) {
            Rectangle hairExtension = new Rectangle(xPosition + 5, yPosition + 130, 5, 10, hairColor.darker());
            hairExtension.draw(g2d);
        } else {
            headShading.draw(g2d);
            hairShading2.draw(g2d);
        }
        bodyTuck.draw(g2d);
        hand.draw(g2d);
        handSleeve.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
    }

    /**
     * Draws the first walking right animation frame for the person.
     * This method sets up the coordinates for the first walking right frame and
     * uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void walkingRightOne(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition, yPosition, 100, 40, hairColor);
        Square sideHair2 = new Square(xPosition, yPosition + 40, 40, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition + 60, yPosition + 40, 40, 20, hairColor);
        Rectangle body = new Rectangle(xPosition + 20, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 10, yPosition + 150, 3, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 20, yPosition + 150, 70, 50, legsColor);
        Rectangle shoes = new Rectangle(xPosition + 20, yPosition + 200, 70, 10, new Color(96, 96, 96));
        Rectangle handSleeve = new Rectangle(xPosition + 35, yPosition + 100, 35, 20, shirtColor);
        Rectangle hand = new Rectangle(xPosition + 35, yPosition + 120, 35, 45, new Color(174, 138, 122));
        Rectangle handSleeveShading = new Rectangle(xPosition + 35, yPosition + 100, 6, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 35, yPosition + 120, 6, 45, new Color(165, 126, 110));
        Square shoesShading = new Square(xPosition + 10, yPosition + 200, 10, new Color(64, 64, 64));
        Rectangle bodyShading = new Rectangle(xPosition + 10, yPosition + 100, 10, 50, shirtColor);
        Rectangle legShading = new Rectangle(xPosition + 10, yPosition + 150, 10, 50, legsColor.darker());
        Rectangle hairShading1 = new Rectangle(xPosition, yPosition, 10, boy ? 80 : 130, hairColor.darker());
        Rectangle hairShading2 = new Rectangle(xPosition, yPosition + 80, 5, 10, hairColor.darker());
        Rectangle headShading = new Rectangle(xPosition, yPosition + 80, 10, 20, new Color(165, 126, 110));

        head.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);

        g2d.rotate(Math.toRadians(-25), xPosition + 90, yPosition + 150);
        g2d.translate(0, -20);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);
        g2d.setTransform(reset);

        body.draw(g2d);
        bodyShading.draw(g2d);
        hairShading1.draw(g2d);
        if (!boy) {
            Rectangle hairExtension = new Rectangle(xPosition + 5, yPosition + 130, 5, 10, hairColor.darker());
            hairExtension.draw(g2d);
        } else {
            headShading.draw(g2d);
            hairShading2.draw(g2d);
        }
        bodyTuck.draw(g2d);

        g2d.rotate(Math.toRadians(25), xPosition + 35, yPosition + 100);
        hand.draw(g2d);
        handSleeve.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.setTransform(reset);
    }

    /**
     * Draws the second walking right animation frame for the person.
     * This method sets up the coordinates for the second walking right frame and
     * uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d   the Graphics2D object used for drawing
     * @param reset the AffineTransform object used to reset transformations
     */
    private void walkingRightTwo(Graphics2D g2d, AffineTransform reset) {
        Square head = new Square(xPosition, yPosition, 100, new Color(174, 138, 122));
        Rectangle sideHair1 = new Rectangle(xPosition, yPosition, 100, 40, hairColor);
        Square sideHair2 = new Square(xPosition, yPosition + 40, 40, hairColor);
        Rectangle sideHair3 = new Rectangle(xPosition + 60, yPosition + 40, 40, 20, hairColor);
        Rectangle body = new Rectangle(xPosition + 20, yPosition + 100, 70, 50, shirtColor);
        Rectangle bodyTuck = new Rectangle(xPosition + 10, yPosition + 150, 3, 10, shirtColor);
        Rectangle legs = new Rectangle(xPosition + 20, yPosition + 150, 70, 50, legsColor);
        Rectangle shoes = new Rectangle(xPosition + 20, yPosition + 200, 70, 10, new Color(96, 96, 96));
        Rectangle handSleeve = new Rectangle(xPosition + 35, yPosition + 100, 35, 20, shirtColor);
        Rectangle hand = new Rectangle(xPosition + 35, yPosition + 120, 35, 45, new Color(174, 138, 122));
        Rectangle handSleeveShading = new Rectangle(xPosition + 35, yPosition + 100, 6, 20, shirtColor.darker());
        Rectangle handShading = new Rectangle(xPosition + 35, yPosition + 120, 6, 45, new Color(165, 126, 110));
        Square shoesShading = new Square(xPosition + 10, yPosition + 200, 10, new Color(64, 64, 64));
        Rectangle bodyShading = new Rectangle(xPosition + 10, yPosition + 100, 10, 50, shirtColor);
        Rectangle legShading = new Rectangle(xPosition + 10, yPosition + 150, 10, 50, legsColor.darker());
        Rectangle hairShading1 = new Rectangle(xPosition, yPosition, 10, boy ? 80 : 130, hairColor.darker());
        Rectangle hairShading2 = new Rectangle(xPosition, yPosition + 80, 5, 10, hairColor.darker());
        Rectangle headShading = new Rectangle(xPosition, yPosition + 80, 10, 20, new Color(165, 126, 110));

        head.draw(g2d);
        sideHair1.draw(g2d);
        sideHair2.draw(g2d);
        sideHair3.draw(g2d);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);

        g2d.rotate(Math.toRadians(25), xPosition + 10, yPosition + 150);
        g2d.translate(0, -20);
        legs.draw(g2d);
        shoes.draw(g2d);
        legShading.draw(g2d);
        shoesShading.draw(g2d);
        g2d.setTransform(reset);

        body.draw(g2d);
        bodyShading.draw(g2d);
        hairShading1.draw(g2d);
        if (!boy) {
            Rectangle hairExtension = new Rectangle(xPosition + 5, yPosition + 130, 5, 10, hairColor.darker());
            hairExtension.draw(g2d);
        } else {
            headShading.draw(g2d);
            hairShading2.draw(g2d);
        }
        bodyTuck.draw(g2d);

        g2d.rotate(Math.toRadians(-25), xPosition + 35, yPosition + 100);
        hand.draw(g2d);
        handSleeve.draw(g2d);
        handSleeveShading.draw(g2d);
        handShading.draw(g2d);
        g2d.setTransform(reset);
    }
}
