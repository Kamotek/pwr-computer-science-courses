public class Segment extends Primitive{

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
    
    public  void translate(Point p) {
        position.coordX += p.coordX;
        position.coordY += p.coordY;
        endPoint.coordX += p.coordX;
        endPoint.coordY += p.coordY;
    }



    public Point[] getBoundingBox() {
        Point[] boundingBox = new Point[4];
        boundingBox[0] = new Point(position.coordX, position.coordY);
        boundingBox[1] = new Point(endPoint.coordX, endPoint.coordY);
        boundingBox[2] = new Point(position.coordX, position.coordY + 1);
        boundingBox[3] = new Point(endPoint.coordX, endPoint.coordY + 1);
        return boundingBox;
    }
    
    private void calculatePosition() {
        int minX = Math.min(startPoint.coordX, endPoint.coordX);
        int minY = Math.min(startPoint.coordY, endPoint.coordY);

        position = new Point(minX, minY);
    }

}
