/**
 The ActionHandler class is responsible for handling
 keyboard and mouse events. It implements the KeyListener
 and MouseListener interfaces to respond to user inputs.

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionHandler implements KeyListener, MouseListener {
    protected boolean rain, firework, lantern, bird, placeLantern, showInstructions;
    protected Point mousePosition;
    protected boolean isMousePressed;
    protected int dialogueTrack;
    protected boolean animate;

    /**
     * Constructs an ActionHandler object and initializes the state variables.
     */
    public ActionHandler() {
        rain = false;
        firework = false;
        lantern = false;
        bird = false;
        placeLantern = false;
        isMousePressed = false;
        animate = true;
        showInstructions = true;
        dialogueTrack = 1;
    }

    /**
     * This method is called when a key is typed.
     * It is currently not used but must be implemented as part of the KeyListener interface.
     *
     * @param e the KeyEvent object containing details of the key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method is called when a key is pressed.
     * It handles specific key events to toggle various states.
     *
     * @param e the KeyEvent object containing details of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_F) {
            firework = true;
        } else if (code == KeyEvent.VK_L) {
            lantern = true;
        } else if (code == KeyEvent.VK_B) {
            bird = true;
        } else if (code == KeyEvent.VK_SPACE) {
            if (!showInstructions) {
                showInstructions = true;
            }
            if (dialogueTrack == 4) {
                showInstructions = false;
                dialogueTrack = -1;
            }
            dialogueTrack++;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            animate = !animate;
        }
    }

    /**
     * This method is called when a key is released.
     * It handles specific key events to reset various states.
     *
     * @param e the KeyEvent object containing details of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_F) {
            firework = false;
        } else if (code == KeyEvent.VK_R) {
            rain = false;
        } else if (code == KeyEvent.VK_L) {
            lantern = false;
        } else if (code == KeyEvent.VK_B) {
            bird = false;
        }
    }

    /**
     * This method is called when the mouse is clicked.
     * It toggles the rain state when the right mouse button is clicked.
     *
     * @param e the MouseEvent object containing details of the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            rain = !rain;
        }
    }

    /**
     * This method is called when a mouse button is pressed.
     * It sets the placeLantern state and records the mouse position when the left mouse button is pressed.
     *
     * @param e the MouseEvent object containing details of the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            placeLantern = true;
            mousePosition = e.getPoint();
            isMousePressed = true;
        }
    }

    /**
     * This method is called when a mouse button is released.
     * It resets the placeLantern state when the left mouse button is released.
     *
     * @param e the MouseEvent object containing details of the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            placeLantern = false;
            isMousePressed = false;
        }
    }

    /**
     * This method is called when the mouse enters a component.
     * It is currently not used but must be implemented as part of the MouseListener interface.
     *
     * @param e the MouseEvent object containing details of the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * This method is called when the mouse exits a component.
     * It is currently not used but must be implemented as part of the MouseListener interface.
     *
     * @param e the MouseEvent object containing details of the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
