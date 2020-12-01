package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Ellipse extends OnePointShape {

    private Number radiusX;
    private Number radiusY;
    private Number rotationAngle;

    public Ellipse(){
        setType("ellipse");
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
