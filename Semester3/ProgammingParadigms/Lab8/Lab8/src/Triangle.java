public class Triangle extends Shape{


    public Point position;
    public Point p1;
    public Point p2;
    public Point p3;
    public boolean isFilled;
    Point[] boundingBox = new Point[4];


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
        int minX = Math.min(Math.min(p1.getCoordX(), p2.getCoordX()), p3.getCoordX());
        int minY = Math.min(Math.min(p1.getCoordY(), p2.getCoordY()), p3.getCoordY());
        int maxX = Math.max(Math.max(p1.getCoordX(), p2.getCoordX()), p3.getCoordX());
        int maxY = Math.max(Math.max(p1.getCoordY(), p2.getCoordY()), p3.getCoordY());

        boundingBox[2] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, maxY);
        boundingBox[0] = new Point(minX, maxY);
        boundingBox[3] = new Point(maxX, minY);
    }
}
