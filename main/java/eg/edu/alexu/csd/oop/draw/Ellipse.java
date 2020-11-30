package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Ellipse implements IShape {
    Number x;
    Number y;
    Number radiusX;
    Number radiusY;
    Number rotationAngle;
    String color;
    int id;
    String type="ellipse";

    public Number getX() {
        return x;
    }

    public String getType() {
        return type;
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

    public Number getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(Number radiusX) {
        this.radiusX = radiusX;
    }

    public Number getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(Number radiusY) {
        this.radiusY = radiusY;
    }

    public Number getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Number rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

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
    public void draw(Map<?,?> map){
        setRadiusX((Number)map.get("radiusX"));
        setRadiusY((Number) map.get("radiusY"));
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX(((ArrayList<Number>) map.get("x")).get(0));
        setY(((ArrayList<Number>)map.get("y")).get(0));
        setRotationAngle((Number) map.get("rotationAngle"));
    }
    @Override
    public IShape clone(){
        ObjectMapper objectMapper=new ObjectMapper();
        Map<?,?> map= objectMapper.convertValue(this,Map.class);
        IShape shape=new Ellipse();
        shape.draw(map);
        return shape;
    }
}
