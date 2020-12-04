package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes({
    @JsonSubTypes.Type(value=Circle.class, name= "Circle"),
    @JsonSubTypes.Type(value= Square.class, name="Square"),
    @JsonSubTypes.Type(value= Line.class, name="Line"),
    @JsonSubTypes.Type(value= Ellipse.class, name="Ellipse"),
    @JsonSubTypes.Type(value= Rectangle.class, name="Rectangle"),
    @JsonSubTypes.Type(value= Triangle.class, name="Triangle")
})
public interface IShape extends Cloneable {
void draw(Map<?,?> map);
int getId();
void setId(int id);
IShape clone();
}
