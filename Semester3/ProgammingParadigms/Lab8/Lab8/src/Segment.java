import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Segment extends Primitive{

    private Point startPoint;
    private Point endPoint;


    public Segment(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        calculatePosition();
    }



    public Point getPosition() {
        return position;
    }
    public void draw() {
        System.out.println("Segment: ");
        System.out.println(position.getCoordX() + " " + position.getCoordY() + " " + endPoint.getCoordX() + " " + endPoint.getCoordY());
    }

    public void draw(Graphics g) {
        g.drawLine(position.getCoordX(), position.getCoordY(), endPoint.getCoordX(), endPoint.getCoordY());

        if(!partOfComplex) {
            Graphics2D g2d = (Graphics2D) g;
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[0].getCoordY(), maxX - minX, maxY-minY);
        }



    }
    
    public  void translate(Point p) {
        position.setCoordX(p.getCoordX() + position.getCoordX());
        position.setCoordY(p.getCoordY() + position.getCoordY());
        endPoint.setCoordX(p.getCoordX() + endPoint.getCoordX());
        endPoint.setCoordY(p.getCoordY() + endPoint.getCoordY());
    }



    public Point[] getBoundingBox() {
        boundingBox[0] = new Point(position.getCoordX(), position.getCoordY());
        boundingBox[1] = new Point(endPoint.getCoordX(), endPoint.getCoordY());
        boundingBox[2] = new Point(position.getCoordX(), position.getCoordY() + 1);
        boundingBox[3] = new Point(endPoint.getCoordX(), endPoint.getCoordY() + 1);
        return boundingBox;
    }
    

    public double getLength() {
        return Math.sqrt(Math.pow(endPoint.getCoordX() - position.getCoordX(), 2) + Math.pow(endPoint.getCoordY() - position.getCoordY(), 2));
    }


    private void calculatePosition() {
        minX = Math.min(startPoint.getCoordX(), endPoint.getCoordX());
        minY = Math.min(startPoint.getCoordY(), endPoint.getCoordY());
        maxX = Math.max(startPoint.getCoordX(), endPoint.getCoordX());
        maxY = Math.max(startPoint.getCoordY(), endPoint.getCoordY());

        position = new Point(minX, minY);
    }

}
