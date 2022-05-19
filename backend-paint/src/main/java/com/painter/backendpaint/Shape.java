package com.painter.backendpaint;

import java.util.Map;

public abstract class Shape  implements IShape{
    protected  int id;
    protected String color;
    protected String type;

    @Override
    public abstract void draw(Map<?, ?> map);

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public abstract IShape clone();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}