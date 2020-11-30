package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class DrawEngine {//Singleton Class
    private static DrawEngine instance;
    private ArrayList<IShape> shapes=new ArrayList<IShape>();
    IShape shape;
    Stack undo=new Stack();
    Stack redo=new Stack();
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
        Hashtable<String,IShape> shapeOp=new Hashtable<String,IShape>();
        shapeOp.put("Draw",shape);
        undo.push(shapeOp );
    }
    public void updateShape(Map<?,?> map){
        shape=shapes.get((int)map.get("id"));//get the old shape

        Hashtable<String, IShape> shapeOp=new Hashtable<String,IShape>();
        shapeOp.put("Update",shape);
        undo.push(shapeOp);//store the old shape in the undo stack
        shape.draw(map);//update shape
        /**
        TODO check if shape.draw(map) works
         **/
    }
    public IShape removeShape(int id){
        Hashtable<String,IShape> shapeOp=new Hashtable<String,IShape>();
        shape=shapes.get(id);
        shapeOp.put("Remove",shape);
        undo.push(shapeOp);//store the old shape in the undo stack
        nullShape nullShape=new nullShape();
        nullShape.setId(shape.getId());
        shapes.set(id,nullShape);
        return nullShape;
    }
    public IShape copyShape(int id){
        IShape shape=shapes.get(id);
        IShape copy=shape.clone();
        copy.setId(id);
        return copy;
    }
    public ArrayList<IShape> getShapes(){
        return this.shapes;
    }
    public IShape undo(){
        if(!undo.isEmpty()) {
            Hashtable<String, IShape> shapeOp = (Hashtable<String, IShape>) undo.pop();
            if(shapeOp.containsKey("Draw")){
				/*
				to undo the draw,we put the shape in the redo stack
				and remove the shape from the shapes arraylist in the draw engine
				 */
                redo.push(shapeOp);
                IShape shape=shapeOp.get("Draw");
                nullShape nullShape=new nullShape();
                nullShape.setId(shape.getId());
                shapes.set(shape.getId(),nullShape);//put null in the shapes arraylist to indicate that the shape has been removed

                return nullShape;
            }
            else if(shapeOp.containsKey("Update")){
				/*
				ShapeOp contains the old shape
				we take the updated shape which is stored in the shapes arraylist in the draw engine and push it in the redo
				stack and then take the old shape which is stored in shapeOp and put it in the arraylist in the draw engine
				 */
                IShape oldShape=shapeOp.get("Update");//old shape
                IShape newShape=shapes.get(oldShape.getId());//get new shape from array list
                Hashtable<String,IShape>newShapeOp=new Hashtable<String,IShape>();
                newShapeOp.put("Update",newShape);
                redo.push(newShapeOp);//put the new shape in the redo stack
                shapes.set(oldShape.getId(),oldShape);//put the old shape in the arraylist of draw engine
                return oldShape;
            }
            else if (shapeOp.containsKey("Remove")){
				/*
				to undo remove we put the shape in the shapes arraylist in the draw engine  again and push the shape in the redo stack
				 */
                IShape shape=shapeOp.get("Remove");//this is the shape that was removed
                shapes.set(shape.getId(),shape);//put the shape in the arraylist
                redo.push(shapeOp);
                return shape;
            }
        }
        return null;
    }
    public IShape redo(){
        if(!redo.isEmpty()){
            Hashtable<String, IShape> shapeOp = (Hashtable<String, IShape>) redo.pop();
            if(shapeOp.containsKey("Draw")){
                /*
                we put the shape in the arraylist of shapes
                 */
                IShape shape=shapeOp.get("Draw");
                shapes.set(shape.getId(),shape);//put the shape in the arraylist
                undo.push(shapeOp);
                return shape;
            }
            else if (shapeOp.containsKey("Update")){
                /*
                we take the old shape from the arraylist of shapes and put it the undo stack and put the new shape in the arraylist
                of shapes
                 */
                IShape newShape=shapeOp.get("Update");//new Shape
                IShape oldShape=shapes.get(newShape.getId());//oldShape
                Hashtable<String,IShape>oldShapeOp=new Hashtable<String,IShape>();
                oldShapeOp.put("Update",oldShape);
                undo.push(oldShapeOp);
                shapes.set(newShape.getId(),newShape);
                return newShape;
            }
            else if (shapeOp.containsKey("Remove")){
                /*
                we remove the shape that is in the redo stack
                 */
                IShape shape=shapeOp.get("Remove");//shape to be removed
                nullShape nullShape=new nullShape();
                nullShape.setId(shape.getId());
                shapes.set(shape.getId(),nullShape);
                undo.push(shapeOp);
                return nullShape;
            }
        }
        return null;
    }

}
