public class Circle extends Shape {

    public Point position;
    public int radius;
    public boolean isFilled;
    public Point centrePoint;
    Point[] boundingBox = new Point[4];

    public Circle(int radius, Point centrePoint, boolean isFilled) {
        this.radius = radius;
        this.isFilled = isFilled;
        centrePoint = new Point(centrePoint.coordX, centrePoint.coordY);

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
        System.out.println("Circle");
        System.out.println(position.getCoordX() + " " + position.getCoordY());
        System.out.println(radius);
        System.out.println(isFilled);
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
