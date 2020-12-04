package eg.edu.alexu.csd.oop.draw;

public abstract class MultiplePointsShape extends Shape{
    protected Number [] x;
    protected Number [] y;

    public Number[] getX() {
        return x;
    }

    public void setX(Number[] x) {
        this.x = x;
    }

    public Number[] getY() {
        return y;
    }

    public void setY(Number[] y) {
        this.y = y;
    }
}