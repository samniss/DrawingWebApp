package eg.edu.alexu.csd.oop.draw;

import java.util.ArrayList;
import java.util.Map;

public class DrawEngine {//Singleton Class
    private static DrawEngine instance;
    private ArrayList<IShape> shapes=new ArrayList<IShape>();
    IShape shape;
    private DrawEngine(){
    }
    public static synchronized DrawEngine getInstance(){
        if(instance==null){
           instance=new DrawEngine();
        }
       return instance;
    }
    public void addShape(Map<?,?> map){
        if(map.get("type").equals("circle")){
            Factory f=new Factory();
            shape=f.getShape("circle");
        }
        else if (map.get("type").equals("rectangle")){
            Factory f=new Factory();
            shape=f.getShape("rectangle");
        }
        else if (map.get("type").equals("triangle")){
            Factory f=new Factory();
            shape=f.getShape("triangle");
        }
        else if (map.get("type").equals("square")){
            Factory f=new Factory();
            shape=f.getShape("square");
        }
        else if (map.get("type").equals("line")){
            Factory f=new Factory();
            shape=f.getShape("line");
        }
        else if (map.get("type").equals("ellipse")){
            Factory f=new Factory();
            shape=f.getShape("ellipse");
        }
        shape.draw(map);
        shapes.add(shape);
    }
    public void updateShape(Map<?,?> map){
        shape=shapes.get((int)map.get("id"));
        shape.draw(map);
    }
    public void removeShape(int id){
        shapes.remove(id);
    }
    public ArrayList<IShape> getShapes(){
        return this.shapes;
    }

}
