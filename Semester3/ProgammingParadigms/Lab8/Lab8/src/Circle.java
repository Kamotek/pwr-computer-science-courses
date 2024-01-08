import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Circle extends Shape {

    public Point position;
    public int radius;
    public boolean isFilled;
    public Point centrePoint;
    Point[] boundingBox = new Point[4];

    public Circle(int radius, Point centrePoint, boolean isFilled) {
        this.radius = radius;
        this.isFilled = isFilled;
        this.centrePoint = new Point(centrePoint.coordX, centrePoint.coordY);

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
        System.out.println("BoundingBox0: " + boundingBox[0].coordX + " " + boundingBox[0].coordY);
        System.out.println("BoundingBox1: " + boundingBox[1].coordX + " " + boundingBox[1].coordY);
        System.out.println("BoundingBox2: " + boundingBox[2].coordX + " " + boundingBox[2].coordY);
        System.out.println("BoundingBox3: " + boundingBox[3].coordX + " " + boundingBox[3].coordY);
        System.out.println(position.getCoordX() + " " + position.getCoordY());
    }

    public void draw(Graphics g) {
        if (isFilled) {
            g.fillOval(centrePoint.coordX - radius, centrePoint.coordY - radius, radius * 2, radius * 2);
        } else {
            g.drawOval(centrePoint.coordX - radius, centrePoint.coordY - radius, radius * 2, radius * 2);
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

    public void translate(Point p) {
        centrePoint.setCoordX(centrePoint.coordX + p.coordX);
        centrePoint.setCoordY(centrePoint.coordY + p.coordY);


        calculateBoundingBox();
        position = boundingBox[0];
    }

    private void calculateBoundingBox() {
        int minX = centrePoint.getCoordX() - radius;
        int minY = centrePoint.getCoordY() - radius;
        int maxX = centrePoint.getCoordX() + radius;
        int maxY = centrePoint.getCoordY() + radius;
        
        boundingBox[3] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[0] = new Point(minX, maxY);
    }


}
