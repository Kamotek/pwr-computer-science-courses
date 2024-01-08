import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Triangle extends Shape{


    public Point position;
    public Point p1;
    public Point p2;
    public Point p3;
    public boolean isFilled;
    Point[] boundingBox = new Point[4];

    int minX;
    int minY;
    int maxX;
    int maxY;



    public Triangle(Point p1, Point p2, Point p3, boolean isFilled) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.isFilled = isFilled;

        calculateBoundingBox();
        position = boundingBox[0];
    }

    public Point getPosition() {
        return position;
    }
    public Point[] getBoundingBox() {

        return boundingBox;
    }

    public  void draw() {
        System.out.println("Triangle");
        System.out.println(p1.getCoordX() + " " + p1.getCoordY());
        System.out.println(p2.getCoordX() + " " + p2.getCoordY());
        System.out.println(p3.getCoordX() + " " + p3.getCoordY());
        System.out.println(isFilled);
    }

    public void draw(Graphics g) {
        int[] xPoints = {p1.coordX, p2.coordX, p3.coordX};
        int[] yPoints = {p1.coordY, p2.coordY, p3.coordY};

        if (isFilled) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }

        if(!partOfComplex) {
            Graphics2D g2d = (Graphics2D) g;
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[0].getCoordY(), maxX - minX, maxY - minY);
        }

        
    }
 

    public boolean getFilled() {
        return isFilled;
    }

    public void translate(Point p) {
        p1.setCoordX(p1.coordX + p.coordX);
        p1.setCoordY(p1.coordY + p.coordY);
        p2.setCoordX(p2.coordX + p.coordX);
        p2.setCoordY(p2.coordY + p.coordY);
        p3.setCoordX(p3.coordX + p.coordX);
        p3.setCoordY(p3.coordY + p.coordY);

        calculateBoundingBox();
        position = boundingBox[0];
    }

    private void calculateBoundingBox() {
        minX = Math.min(Math.min(p1.getCoordX(), p2.getCoordX()), p3.getCoordX());
        minY = Math.min(Math.min(p1.getCoordY(), p2.getCoordY()), p3.getCoordY());
        maxX = Math.max(Math.max(p1.getCoordX(), p2.getCoordX()), p3.getCoordX());
        maxY = Math.max(Math.max(p1.getCoordY(), p2.getCoordY()), p3.getCoordY());

        boundingBox[0] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, maxY);
        boundingBox[2] = new Point(minX, maxY);
        boundingBox[3] = new Point(maxX, minY);
    }
}
