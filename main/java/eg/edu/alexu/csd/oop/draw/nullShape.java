package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public class nullShape implements IShape {//Used to indicate that a shape has been remove
    int id;
    String type="null";

    @Override
    public void draw(Map<?, ?> map) {

    }

    @Override
    public int getId() {
        return this.id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id){
        this.id=id;
    }
    @Override
    public IShape clone(){
        return null;
    }
}
