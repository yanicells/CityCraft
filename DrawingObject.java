/**
The DrawingObject interface defines methods for drawing and updating graphical objects. 
It includes an abstract method for drawing using a Graphics2D context and another for updating the object's state.

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

public interface DrawingObject {

    /**
     * Draws the graphical object using the specified Graphics2D context.
     *
     * @param g2d the Graphics2D context to use for drawing
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * Updates the state of the graphical object.
     */
    public abstract void update();
}
