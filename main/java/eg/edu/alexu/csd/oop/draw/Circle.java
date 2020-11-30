package eg.edu.alexu.csd.oop.draw;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.lang.Number;
import java.util.ArrayList;
import java.util.Map;

public class Circle implements IShape {
    Number x;
    Number y;
    Number radius;
    int id;
    String color;
    String type="circle";
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public String getType() {
        return type;
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
    @Override
    public IShape clone()  {
        ObjectMapper objectMapper=new ObjectMapper();
       Map<?,?> map= objectMapper.convertValue(this,Map.class);
       IShape shape=new Circle();
       shape.draw(map);
    return shape;
    }

}