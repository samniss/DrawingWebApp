package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public class Ellipse implements IShape {
    float x;
    float y;
    float radiusX;
    float radiusY;
    float rotationAngle;
    String color;
    int id;

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

    public float getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(float radiusX) {
        this.radiusX = radiusX;
    }

    public float getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(float radiusY) {
        this.radiusY = radiusY;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
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
        setRadiusX(Float.parseFloat((String)map.get("radiusX")));
        setRadiusY((float)map.get("radiusY"));
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX(((float[]) map.get("x"))[0]);
        setY(((float[])map.get("y"))[0]);
        setRotationAngle((float)map.get("rotationAngle"));
    }
}
