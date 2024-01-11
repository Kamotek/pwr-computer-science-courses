
import java.util.ArrayList;

public class NVerStar extends Shape {

    private ArrayList<Point> verticles;
    private int radius;
    private Point centrePoint;
    private int n;

    public NVerStar(int radius, Point centrePoint, int n, boolean isFilled) {
        this.radius = radius;
        this.isFilled = isFilled;
        this.centrePoint = new Point(centrePoint.getCoordX(), centrePoint.getCoordY());
        this.n = n;

        generateVerticles();

        calculateBoundingBox();
        position = boundingBox[0];
    }

    public Point getPosition() {
        return position;
    }




    private void generateVerticles() {
        verticles = new ArrayList<Point>();

        double angleIncrement = 2 * Math.PI / n;
        double currentAngle = -Math.PI / 2; // Start from the top

        for (int i = 0; i < n * 2; i++) {
            int r = (i % 2 == 0) ? radius : radius / 2; // Alternating radius

            int x = (int) Math.round(centrePoint.getCoordX() + r * Math.cos(currentAngle));
            int y = (int) Math.round(centrePoint.getCoordY() + r * Math.sin(currentAngle));
            verticles.add(new Point(x, y));

            currentAngle += angleIncrement; // Move to the next angle
        }
        
    }

    private void calculateBoundingBox() {

        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;

        for(Point p : verticles) {
            if (p.getCoordX() < minX) {
                minX = p.getCoordX();
            }
            if (p.getCoordY() < minY) {
                minY = p.getCoordY();
            }
            if (p.getCoordX() > maxX) {
                maxX = p.getCoordX();
            }
            if (p.getCoordY() > maxY) {
                maxY = p.getCoordY();
            }
        }

        boundingBox[0] = new Point(minX, maxY);
        boundingBox[1] = new Point(maxX, maxY);
        boundingBox[2] = new Point(maxX, minY);
        boundingBox[3] = new Point(minX, minY);

    }

    public Point[] getBoundingBox() {
        return boundingBox;
    }

    public void translate(Point p) {
        centrePoint.setCoordX(centrePoint.getCoordX() + p.getCoordX());
        centrePoint.setCoordY(centrePoint.getCoordY() + p.getCoordY());

        generateVerticles();
        calculateBoundingBox();
        position = boundingBox[0];
    }


    public void draw() {
        System.out.println("NVerStar: " + centrePoint.getCoordX() + " " + centrePoint.getCoordY());
        System.out.println("Radius: " + radius);
        System.out.println("N: " + n);
        System.out.println("BoundingBox0: " + boundingBox[0].getCoordX() + " " + boundingBox[0].getCoordY());
        System.out.println("BoundingBox1: " + boundingBox[1].getCoordX() + " " + boundingBox[1].getCoordY());
        System.out.println("BoundingBox2: " + boundingBox[2].getCoordX() + " " + boundingBox[2].getCoordY());
        System.out.println("BoundingBox3: " + boundingBox[3].getCoordX() + " " + boundingBox[3].getCoordY());
        System.out.println(position.getCoordX() + " " + position.getCoordY());
    }

    public void draw(java.awt.Graphics g) {
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];

        for(int i = 0; i<n; i++) {
            xPoints[i] = verticles.get(i).getCoordX();
            yPoints[i] = verticles.get(i).getCoordY();
        }

        if (isFilled) {
            g.fillPolygon(xPoints, yPoints, n);
        } else {
            g.drawPolygon(xPoints, yPoints, n);
        }

        if(!partOfComplex) {
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
            java.awt.Stroke dashed = new java.awt.BasicStroke(1, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2d.setStroke(dashed);
            g2d.drawRect(position.getCoordX(), position.getCoordY() - radius*2, radius * 2, radius * 2);
        }
        
    }


    public boolean getFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }


}
