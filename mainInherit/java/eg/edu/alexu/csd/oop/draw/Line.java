package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Line extends MultiplePointsShape {


    public Line(){
        setType("line");
    }


    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX((((ArrayList<Number>)map.get("x")).toArray(new Number[2])));
        setY((((ArrayList<Number>)map.get("y")).toArray(new Number[2])));


    }
    @Override
        public IShape clone(){
        ObjectMapper objectMapper=new ObjectMapper();
        Map<?,?> map= objectMapper.convertValue(this,Map.class);
        IShape shape=new Line();
        shape.draw(map);
        return shape;
    }
}
