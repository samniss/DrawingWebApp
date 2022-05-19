package com.painter.backendpaint;

public class Factory {
    public IShape getShape(String type){
        if(type==null){
            return null;
        }
        if(type.equalsIgnoreCase("circle")){
            return new Circle();
        }
        else if (type.equalsIgnoreCase("rectangle")){
            return new Rectangle();
        }
        else if (type.equalsIgnoreCase("square")){
            return new Square();
        }
        else if (type.equalsIgnoreCase("triangle")){
            return new Triangle();
        }
        else if (type.equalsIgnoreCase("line")){
            return new Line();
        }
        else if (type.equalsIgnoreCase("ellipse")){
            return new Ellipse();
        }
        return null;
    }
}
