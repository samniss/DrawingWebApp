package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Triangle extends MultiplePointsShape{


    public Triangle(){
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
        ObjectMapper objectMapper=new ObjectMapper();
        Map<?,?> map= objectMapper.convertValue(this,Map.class);
        IShape shape=new Triangle();
        shape.draw(map);
        return shape;
    }
}
