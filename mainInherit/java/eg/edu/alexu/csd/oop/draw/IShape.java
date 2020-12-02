package eg.edu.alexu.csd.oop.draw;

import java.util.Map;

public interface IShape extends Cloneable {
void draw(Map<?,?> map);
int getId();
void setId(int id);
IShape clone();
}
