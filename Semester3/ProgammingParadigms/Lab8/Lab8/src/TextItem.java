import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

    public class TextItem extends Item {

        private String text;

        public TextItem(String text, int coordX, int coordY) {
            position = new Point(coordX, coordY);
            this.text = text;

            calculateBoundingBox();
        }

        public Point getPosition() {
            return position;
        }

        public void setPosition(Point position) {
            this.position = position;
        }

        public Point[] getBoundingBox() {
            return boundingBox;
        }

        public void setBoundingBox(Point[] boundingBox) {
            this.boundingBox = boundingBox;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void draw() {
            System.out.println(text + " " + position.getCoordX() + " " + position.getCoordY());
            System.out.println("BoundingBox0: " + boundingBox[0].getCoordX() + " " + boundingBox[0].getCoordY());
            System.out.println("BoundingBox1: " + boundingBox[1].getCoordX() + " " + boundingBox[1].getCoordY());
            System.out.println("BoundingBox2: " + boundingBox[2].getCoordX() + " " + boundingBox[2].getCoordY());
            System.out.println("BoundingBox3: " + boundingBox[3].getCoordX() + " " + boundingBox[3].getCoordY());
        }

        public void draw(Graphics g) {
            g.drawString(text, position.getCoordX(), position.getCoordY());

            if (!partOfComplex) {
                Graphics2D g2d = (Graphics2D) g;
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
                g2d.setStroke(dashed);
                g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[3].getCoordY() - 10, text.length() * 10, position.getCoordY() + 10);
            }
        }

        public void translate(Point p) {
            position.setCoordX(position.getCoordX() + p.getCoordX());
            position.setCoordY(position.getCoordY() + p.getCoordY());

            calculateBoundingBox();
        }

        private void calculateBoundingBox() {
            boundingBox[0] = new Point(position.getCoordX(), position.getCoordY());
            boundingBox[1] = new Point(position.getCoordX() + text.length(), position.getCoordY());
            boundingBox[2] = new Point(position.getCoordX(), position.getCoordY() + 1);
            boundingBox[3] = new Point(position.getCoordX() + text.length(), position.getCoordY() + 1);
        }
    }
