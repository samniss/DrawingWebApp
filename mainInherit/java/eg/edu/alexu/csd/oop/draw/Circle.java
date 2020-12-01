package eg.edu.alexu.csd.oop.draw;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.lang.Number;
import java.util.ArrayList;
import java.util.Map;

public class Circle extends OnePointShape {

    private Number radius;

public Circle(){
    setType("circle");
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
