import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rect extends Shape{

    public Point position;
    public boolean isFilled;
    public int width;
    public int height;
    public Point p1;
    public Point p2;
    public Point p3;
    public Point p4;

    int minX;
    int minY;
    int maxX;
    int maxY;

    Point[] boundingBox = new Point[4];

    public Rect(Point p1, Point p2, boolean isFilled) {
        this.p1 = p1;
        this.p2 = p2;

        this.isFilled = isFilled;

        calculateBoundingBox();
        position = boundingBox[0];

        this.width = maxX - minX;
        this.height = maxY - minY;

        this.p3 = new Point(Math.max(p2.coordX, p1.coordX), Math.min(p2.coordY, p1.coordY));
        this.p4 = new Point(Math.min(p2.coordX, p1.coordX), Math.max(p2.coordY, p1.coordY));

        
    }

    public Point getPosition() {
        return position;
    }

    public Point[] getBoundingBox() {
            return boundingBox;
    }

     public void draw() {
         System.out.println("Rect");
         System.out.println(p1.getCoordX() + " " + p1.getCoordY());
         System.out.println(p2.getCoordX() + " " + p2.getCoordY());
         System.out.println(p3.getCoordX() + " " + p3.getCoordY());
         System.out.println(p4.getCoordX() + " " + p4.getCoordY());

         System.out.println("BoundingBox0: " + boundingBox[0].coordX + " " + boundingBox[0].coordY);
         System.out.println("BoundingBox1: " + boundingBox[1].coordX + " " + boundingBox[1].coordY);
         System.out.println("BoundingBox2: " + boundingBox[2].coordX + " " + boundingBox[2].coordY);
         System.out.println("BoundingBox3: " + boundingBox[3].coordX + " " + boundingBox[3].coordY);

         System.out.println("Width: " + width);
         System.out.println("Height: " + height);

         System.out.println(isFilled);
         System.out.println(position.getCoordX() + " " + position.getCoordY());


     } 

    public void draw(Graphics g) {
        if (isFilled) {
            g.fillRect(position.getCoordX(), position.getCoordY(), width, height);
        } else {
            g.drawRect(position.getCoordX(), position.getCoordY(), width, height);
        }

        // Draw bounding box as dotted line
        if(!partOfComplex) {
            Graphics2D g2d = (Graphics2D) g;
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[0].getCoordY(), width, height);
        }
    }




    public void translate(Point p) {
        p1.setCoordX(p1.coordX + p.coordX);
        p1.setCoordY(p1.coordY + p.coordY);
        p2.setCoordX(p2.coordX + p.coordX);
        p2.setCoordY(p2.coordY + p.coordY);


        calculateBoundingBox();
        position = boundingBox[0];

    }

    public boolean getFilled() {
        return isFilled;
    }

    private void calculateBoundingBox() {
        minX = Math.min(p1.getCoordX(), p2.getCoordX());
        minY = Math.min(p1.getCoordY(), p2.getCoordY());
        maxX = Math.max(p1.getCoordX(), p2.getCoordX());
        maxY = Math.max(p1.getCoordY(), p2.getCoordY());

        boundingBox[0] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[3] = new Point(minX, maxY);
    }


}
