/**
 The KeyHandler class handles keyboard input for movement controls.
 It implements the KeyListener interface to respond to key events.

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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    protected boolean upPressed, downPressed, leftPressed, rightPressed;
    private int upKey, downKey, leftKey, rightKey;

    /**
     * Constructs a KeyHandler object with the specified key bindings.
     * Initializes the key bindings based on the provided keyBind parameter.
     *
     * @param keyBind the key binding type ("key" for WASD, "arrow" for arrow keys)
     */
    public KeyHandler(String keyBind) {
        setKeyBindings(keyBind);
    }

    /**
     * Sets the key bindings based on the provided keyBind parameter.
     *
     * @param keyBind the key binding type ("key" for WASD, "arrow" for arrow keys)
     */
    private void setKeyBindings(String keyBind) {
        if (keyBind.equalsIgnoreCase("key")) {
            this.upKey = KeyEvent.VK_W;
            this.downKey = KeyEvent.VK_S;
            this.leftKey = KeyEvent.VK_A;
            this.rightKey = KeyEvent.VK_D;
        } else if (keyBind.equalsIgnoreCase("arrow")) {
            this.upKey = KeyEvent.VK_UP;
            this.downKey = KeyEvent.VK_DOWN;
            this.leftKey = KeyEvent.VK_LEFT;
            this.rightKey = KeyEvent.VK_RIGHT;
        }
    }

    /**
     * This method is called when a key is typed.
     * It is currently not used but must be implemented as part of the KeyListener interface.
     *
     * @param e the KeyEvent object containing details of the key event
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * This method is called when a key is pressed.
     * It updates the state of the corresponding key to true.
     *
     * @param e the KeyEvent object containing details of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == upKey) {
            upPressed = true;
        } else if (code == downKey) {
            downPressed = true;
        } else if (code == leftKey) {
            leftPressed = true;
        } else if (code == rightKey) {
            rightPressed = true;
        }
    }

    /**
     * This method is called when a key is released.
     * It updates the state of the corresponding key to false.
     *
     * @param e the KeyEvent object containing details of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == upKey) {
            upPressed = false;
        } else if (code == downKey) {
            downPressed = false;
        } else if (code == leftKey) {
            leftPressed = false;
        } else if (code == rightKey) {
            rightPressed = false;
        }
    }
}
