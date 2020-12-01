package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Map;

public class Rectangle extends OnePointShape{

    private Number width;
    private Number height;


    public Rectangle(){
        setType("rectangle");
    }





    public Number getWidth() {
        return width;
    }

    public void setWidth(Number width) {
        this.width = width;
    }

    public Number getHeight() {
        return height;
    }

    public void setHeight(Number height) {
        this.height = height;
    }

    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX(((ArrayList<Number>)map.get("x")).get(0));
        setY(((ArrayList<Number>)map.get("y")).get(0));
        setWidth((Number) map.get("width"));
        setHeight((Number) map.get("height"));


    }
    @Override
        public IShape clone(){
        Rectangle shape=new Rectangle();

        shape.setColor(this.getColor());
        shape.setHeight(this.getHeight());
        shape.setWidth(this.getWidth());
        shape.setX(this.getX());
        shape.setY(this.getY());
        return shape;
    }
}
