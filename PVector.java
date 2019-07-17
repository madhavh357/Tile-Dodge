
/**
 * Write a description of class PVector here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void add(PVector p) {
        this.x += p.x;
        this.y += p.y;
    }
    
    public void sub(PVector p) {
        this.x -= p.x;
        this.y -= p.y;
    }
    
    public void mult(double value) {
        this.x *= value;
        this.y *= value;
    }
    
    public void div(PVector p) {
        this.x /= p.x;
        this.y /= p.y;
    }
    
    public double mag() {
        double mag = Math.sqrt(x*x+y*y);
        return mag;
    }
    
    public void normalize() {
        x /= mag();
        y /= mag();
        mag();
    }
}
