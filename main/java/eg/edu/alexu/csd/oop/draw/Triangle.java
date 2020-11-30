package eg.edu.alexu.csd.oop.draw;

import java.util.ArrayList;
import java.util.Map;

public class Triangle implements IShape{
    Number [] x;
    Number []y;
    int id;
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX((Number[])(((ArrayList<Number>)map.get("x")).toArray()));
        setY((Number[])(((ArrayList<Number>)map.get("y")).toArray()));
    }
}
