package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Triangle implements IShape{
    Number [] x;
    Number []y;
    int id;
    String color;
    String type="triangle";

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
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
        setX((((ArrayList<Number>)map.get("x")).toArray(new Number[3])));
        setY((((ArrayList<Number>)map.get("y")).toArray(new Number[3])));
    }
    @Override
        public IShape clone(){
        ObjectMapper objectMapper=new ObjectMapper();
        Map<?,?> map= objectMapper.convertValue(this,Map.class);
        IShape shape=new Triangle();
        shape.draw(map);
        return shape;
    }
}
