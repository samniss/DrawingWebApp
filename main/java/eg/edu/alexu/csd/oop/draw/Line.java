package eg.edu.alexu.csd.oop.draw;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Line implements IShape {
   Number [] x=new Number[2];
   Number [] y=new Number[2];
   int id;
   String color;

    public String getType() {
        return type;
    }

    String type="line";

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
    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX((Number[])(((ArrayList<Number>)map.get("x")).toArray()));
        setY((Number[])(((ArrayList<Number>)map.get("y")).toArray()));

    }
}
