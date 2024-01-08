import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class TextItem extends Item{

    String text;
    Point position;
    Point[] boundingBox = new Point[4];



    public TextItem(String text, int coordX, int coordY) {
        position = new Point(coordX, coordY);
        this.text = text;

        calculateBoundingBox();
    }

    public Point getPosition() {
        return position;
    }

    public Point[] getBoundingBox() {
        return boundingBox;
    }

    public void draw() {
        System.out.println(text + " " + position.coordX + " " + position.coordY);
        System.out.println("BoundingBox0: " + boundingBox[0].coordX + " " + boundingBox[0].coordY);
        System.out.println("BoundingBox1: " + boundingBox[1].coordX + " " + boundingBox[1].coordY);
        System.out.println("BoundingBox2: " + boundingBox[2].coordX + " " + boundingBox[2].coordY);
        System.out.println("BoundingBox3: " + boundingBox[3].coordX + " " + boundingBox[3].coordY);
    }

    public void draw(Graphics g) {
        g.drawString(text, position.coordX, position.coordY);

        if(!partOfComplex) {
            Graphics2D g2d = (Graphics2D) g;
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[3].getCoordY() - 10, text.length()*10, position.coordY + 10);
        }
    }

    public void translate(Point p) {
        position.coordX += p.coordX;
        position.coordY += p.coordY;

        calculateBoundingBox();
    }

    private void calculateBoundingBox() {


        boundingBox[0] = new Point(position.coordX, position.coordY);
        boundingBox[1] = new Point(position.coordX + text.length(), position.coordY);
        boundingBox[2] = new Point(position.coordX, position.coordY + 1);
        boundingBox[3] = new Point(position.coordX + text.length(), position.coordY + 1);
    }

}
