
/**
 * stores x and y values in one object
 *
 * @author Madhav
 * @version 3
 */
public class PVector
{

    public double x;
    public double y;

    public PVector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * changes the value of the vector quantity
     * @param double, double
     * @return nothing
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * adds a vector to the original vector
     * @param PVector
     * @return nothing
     */
    public void add(PVector p) {
        this.x += p.x;
        this.y += p.y;
    }
    /**
     * subtracts a vector from the original vector
     * @param PVector
     * @return nothing
     */
    public void sub(PVector p) {
        this.x -= p.x;
        this.y -= p.y;
    }
    /**
     * multiplies a value to the original vector's x and y
     * @param double
     * @return nothing
     */
    public void mult(double value) {
        this.x *= value;
        this.y *= value;
    }
    /**
     * divides a vector from the original vector
     * @param PVector
     * @return nothing
     */
    public void div(PVector p) {
        this.x /= p.x;
        this.y /= p.y;
    }
    /**
     * finds the magnitude of the vector using the Pythagorean Theorem
     * @param none
     * @return double
     */
    public double mag() {
        double mag = Math.sqrt(x*x+y*y);
        return mag;
    }
    /**
     * scales the vector's x and y down so that the magnitude equals 1
     * @param none
     * @return nothing
     */
    public void normalize() {
        x /= mag();
        y /= mag();
        mag();
    }
}
