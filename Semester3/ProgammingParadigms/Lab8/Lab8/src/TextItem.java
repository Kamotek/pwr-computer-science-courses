public class TextItem extends Item{

    String text;
    Point position;

    public TextItem(String text, int coordX, int coordY) {
        position = new Point(coordX, coordY);
        this.text = text;
    }

    public Point getPosition() {
        return position;
    }

    public Point[] getBoundingBox() {
        Point[] boundingBox = new Point[4];
        boundingBox[0] = new Point(position.coordX, position.coordY);
        boundingBox[1] = new Point(position.coordX + text.length(), position.coordY);
        boundingBox[2] = new Point(position.coordX, position.coordY + 1);
        boundingBox[3] = new Point(position.coordX + text.length(), position.coordY + 1);
        return boundingBox;
    }

    public void draw() {
        System.out.println(text);
    }

    public void translate(Point p) {
        position.coordX += p.coordX;
        position.coordY += p.coordY;
    }


}
