package eg.edu.alexu.csd.oop.draw;
import java.lang.Number;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Circle implements IShape {
    Number x;
    Number y;
    Number radius;
    int id;
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Number getX() {
        return x;
    }

    public void setX(Number x) {
        this.x = x;
    }

    public Number getY() {
        return y;
    }

    public void setY(Number y) {
        this.y = y;
    }

    public Number getRadius() {
        return radius;
    }

    public void setRadius(Number radius) {
        this.radius = radius;
    }
    public void draw(Map<?,?> map){
        setRadius((Number)map.get("radius"));
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX(((ArrayList<Number>) map.get("x")).get(0));
        setY(((ArrayList<Number>)map.get("y")).get(0));
    }
}
