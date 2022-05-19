package com.painter.backendpaint;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeName;
@SuppressWarnings({"unchecked"})
@JsonTypeName("Triangle")
public class Triangle extends MultiplePointsShape{
    Triangle(){
        setType("triangle");
    }
    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX((((ArrayList<Number>)map.get("x")).toArray(new Number[3])));
        setY((((ArrayList<Number>)map.get("y")).toArray(new Number[3])));
    }
    @Override
    public IShape clone(){
        Triangle shape=new Triangle();
        shape.setX(this.getX());
        shape.setY(this.getY());
        shape.setColor(this.getColor());
        return shape;
    }
    
}
