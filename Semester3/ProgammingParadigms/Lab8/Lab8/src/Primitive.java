public abstract class Primitive extends Item{

    public Point position;
    public abstract Point getPosition();
    public abstract Point[] getBoundingBox();
    public abstract void draw();
    public abstract void translate(Point p);


}
