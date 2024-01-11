import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Circle extends Shape {

    private int radius;
    private Point centrePoint;

    public Circle(int radius, Point centrePoint, boolean isFilled) {
        this.radius = radius;
        this.isFilled = isFilled;
        this.centrePoint = new Point(centrePoint.getCoordX(), centrePoint.getCoordY());

        calculateBoundingBox();
        position = boundingBox[0];
    }

    public Point getPosition() {
        return position;
    }

    public Point[] getBoundingBox() {

        return boundingBox;
    }

    public void draw() {
        System.out.println("Circle: " + centrePoint.getCoordX() + " " + centrePoint.getCoordY());
        System.out.println("Radius: " + radius);
        System.out.println("BoundingBox0: " + boundingBox[0].getCoordX() + " " + boundingBox[0].getCoordY());
        System.out.println("BoundingBox1: " + boundingBox[1].getCoordX() + " " + boundingBox[1].getCoordY());
        System.out.println("BoundingBox2: " + boundingBox[2].getCoordX() + " " + boundingBox[2].getCoordY());
        System.out.println("BoundingBox3: " + boundingBox[3].getCoordX() + " " + boundingBox[3].getCoordY());
        System.out.println(position.getCoordX() + " " + position.getCoordY());
    }

    public void draw(Graphics g) {
        if (isFilled) {
            g.fillOval(centrePoint.getCoordX() - radius, centrePoint.getCoordY() - radius, radius * 2, radius * 2);
        } else {
            g.drawOval(centrePoint.getCoordX() - radius, centrePoint.getCoordY() - radius, radius * 2, radius * 2);
        }

        if(!partOfComplex) {
            Graphics2D g2d = (Graphics2D) g;
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(position.getCoordX(), position.getCoordY() - radius*2, radius * 2, radius * 2);
        }
    }

    public boolean getFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void translate(Point p) {
        centrePoint.setCoordX(centrePoint.getCoordX() + p.getCoordX());
        centrePoint.setCoordY(centrePoint.getCoordY() + p.getCoordY());


        calculateBoundingBox();
        position = boundingBox[0];
    }

    private void calculateBoundingBox() {
        minX = centrePoint.getCoordX() - radius;
        minY = centrePoint.getCoordY() - radius;
        maxX = centrePoint.getCoordX() + radius;
        maxY = centrePoint.getCoordY() + radius;
        
        boundingBox[3] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[0] = new Point(minX, maxY);
    }


}
