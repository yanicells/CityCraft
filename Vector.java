/**
The Vector class represents a 2D vector with x and y components.
It provides methods for vector addition and scalar multiplication.

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

public class Vector{
    private double x, y;

    /**
     * Constructs a Vector object with the specified x and y components.
     *
     * @param x the x-component of the vector
     * @param y the y-component of the vector
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Vector object with random direction and unit length.
     */
    public Vector(){
        double angle = Math.random() * 2 * Math.PI;
        this.x = Math.cos(angle);
        this.y = Math.sin(angle);
    }

    /**
     * Adds the specified vector to this vector.
     *
     * @param v the vector to be added
     * @return this vector after addition
     */
    public Vector add(Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }

    /**
     * Multiplies this vector by the specified scalar.
     *
     * @param scalar the scalar to multiply by
     * @return this vector after multiplication
     */
    public Vector multiply(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    /**
     * Gets the x-component of this vector.
     *
     * @return the x-component of this vector
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-component of this vector.
     *
     * @return the y-component of this vector
     */
    public double getY() {
        return y;
    }
}