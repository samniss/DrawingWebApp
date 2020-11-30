package eg.edu.alexu.csd.oop.draw;

import java.util.ArrayList;
import java.util.Map;

public class Square implements IShape{
    Number x;
    Number y;
    Number side;
    int id;
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Number getSide() {
        return side;
    }

    public void setSide(Number side) {
        this.side = side;
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
        setX(((ArrayList<Number>)map.get("x")).get(0));
        setY(((ArrayList<Number>)map.get("y")).get(0));
        setSide((Number) map.get("side"));
    }
}
