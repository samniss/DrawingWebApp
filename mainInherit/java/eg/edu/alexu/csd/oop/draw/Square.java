package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Square extends OnePointShape{

    private Number side;

    public Square(){
        setType("square");
    }

    

    public Number getSide() {
        return side;
    }

    public void setSide(Number side) {
        this.side = side;
    }


    public void draw(Map<?,?> map){
        setId((int)map.get("id"));
        setColor((String)map.get("color"));
        setX(((ArrayList<Number>)map.get("x")).get(0));
        setY(((ArrayList<Number>)map.get("y")).get(0));
        setSide((Number) map.get("side"));

    }
    @Override
        public IShape clone(){
        ObjectMapper objectMapper=new ObjectMapper();
        Map<?,?> map= objectMapper.convertValue(this,Map.class);
        IShape shape=new Square();
        shape.draw(map);
        return shape;
    }
}
