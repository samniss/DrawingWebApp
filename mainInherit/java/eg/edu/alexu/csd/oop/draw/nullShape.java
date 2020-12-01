package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public class nullShape extends Shape {//Used to indicate that a shape has been remove

public nullShape(){
    setType("null");
}
    @Override
    public void draw(Map<?, ?> map) {

    }


    @Override
    public IShape clone(){
        return null;
    }
}
