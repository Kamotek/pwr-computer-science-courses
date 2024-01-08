import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Segment extends Primitive{

    int minX;
    int minY;
    int maxX;
    int maxY;
    Point[] boundingBox = new Point[4];


    public Segment(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        calculatePosition();
    }

    public Point position;
    public Point startPoint;
    public Point endPoint;
    public Point getPosition() {
        return position;
    }
    public void draw() {
        System.out.println("Segment: ");
        System.out.println(position.coordX + " " + position.coordY + " " + endPoint.coordX + " " + endPoint.coordY);
    }

    public void draw(Graphics g) {
        g.drawLine(position.coordX, position.coordY, endPoint.coordX, endPoint.coordY);

        if(!partOfComplex) {
            Graphics2D g2d = (Graphics2D) g;
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[0].getCoordY(), maxX - minX, maxY-minY);
        }



    }
    
    public  void translate(Point p) {
        position.coordX += p.coordX;
        position.coordY += p.coordY;
        endPoint.coordX += p.coordX;
        endPoint.coordY += p.coordY;
    }



    public Point[] getBoundingBox() {
        boundingBox[0] = new Point(position.coordX, position.coordY);
        boundingBox[1] = new Point(endPoint.coordX, endPoint.coordY);
        boundingBox[2] = new Point(position.coordX, position.coordY + 1);
        boundingBox[3] = new Point(endPoint.coordX, endPoint.coordY + 1);
        return boundingBox;
    }
    
    private void calculatePosition() {
        minX = Math.min(startPoint.coordX, endPoint.coordX);
        minY = Math.min(startPoint.coordY, endPoint.coordY);
        maxX = Math.max(startPoint.coordX, endPoint.coordX);
        maxY = Math.max(startPoint.coordY, endPoint.coordY);

        position = new Point(minX, minY);
    }

}
