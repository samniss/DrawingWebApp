package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public class Square implements IShape{
    float x;
    float y;
    float side;
    int id;
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSide() {
        return side;
    }

    public void setSide(float side) {
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
        setX(((float[])map.get("x"))[0]);
        setY(((float [])map.get("y"))[0]);
        setSide((float)map.get("side"));
    }
}
