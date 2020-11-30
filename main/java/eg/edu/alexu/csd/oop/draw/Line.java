package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public class Line implements IShape {
   float [] x=new float[2];
   float [] y=new float[2];
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

    public float[] getX() {
        return x;
    }

    public void setX(float[] x) {
        this.x = x;
    }

    public float[] getY() {
        return y;
    }

    public void setY(float[] y) {
        this.y = y;
    }
    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX((float[])map.get("x"));
        setY((float [])map.get("y"));

    }
}
