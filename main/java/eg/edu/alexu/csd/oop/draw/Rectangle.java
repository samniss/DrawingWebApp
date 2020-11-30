package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public class Rectangle implements IShape{
   float x;
   float y;
   float width;
   float height;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX(((float[])map.get("x"))[0]);
        setY(((float [])map.get("y"))[0]);
        setWidth((float)map.get("width"));
        setHeight((float)map.get("height"));

    }
}
