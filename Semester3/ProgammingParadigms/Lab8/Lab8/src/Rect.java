public class Rect extends Shape{

    public Point position;
    public boolean isFilled;
    public int width;
    public int height;
    public Point p1;
    public Point p2;
    public Point p3;
    public Point p4;
    Point[] boundingBox = new Point[4];

    public Rect(Point p1, Point p2, Point p3, Point p4, boolean isFilled) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

        this.isFilled = isFilled;

        calculateBoundingBox();
        position = boundingBox[0];

        this.width = boundingBox[2].coordX - boundingBox[0].coordX;
        this.height = boundingBox[2].coordY - boundingBox[0].coordY;

        
    }

    public Point getPosition() {
        return position;
    }

    public Point[] getBoundingBox() {
            return boundingBox;
    }

    public void draw() {
        System.out.println("Drawing a rectangle at position (" + position.getCoordX() + ", " + position.getCoordY() + ") with width " + width + " and height " + height);
    }

    public void translate(Point p) {
        p1.setCoordX(p1.coordX + p.coordX);
        p1.setCoordY(p1.coordY + p.coordY);
        p2.setCoordX(p2.coordX + p.coordX);
        p2.setCoordY(p2.coordY + p.coordY);
        p3.setCoordX(p3.coordX + p.coordX);
        p3.setCoordY(p3.coordY + p.coordY);
        p4.setCoordX(p4.coordX + p.coordX);
        p4.setCoordY(p4.coordY + p.coordY);

        calculateBoundingBox();
        position = boundingBox[0];

    }

    public boolean getFilled() {
        return isFilled;
    }

    private void calculateBoundingBox() {
        int minX = Math.min(Math.min(p1.getCoordX(), p2.getCoordX()), Math.min(p3.getCoordX(), p4.getCoordX()));
        int minY = Math.min(Math.min(p1.getCoordY(), p2.getCoordY()), Math.min(p3.getCoordY(), p4.getCoordY()));
        int maxX = Math.max(Math.max(p1.getCoordX(), p2.getCoordX()), Math.max(p3.getCoordX(), p4.getCoordX()));
        int maxY = Math.max(Math.max(p1.getCoordY(), p2.getCoordY()), Math.max(p3.getCoordY(), p4.getCoordY()));

        boundingBox[3] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[0] = new Point(minX, maxY);
    }



}
