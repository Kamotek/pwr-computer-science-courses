


public class App {

   

    public static void main(String[] args) {
        Scene scene = new Scene();

        Circle circle1 = new Circle(50, new Point(250, 250), false);
        Circle circle2 = new Circle(40, new Point(250, 160), false);
        Circle circle3 = new Circle(30, new Point(250, 90), false);
        Circle eye1 = new Circle(5, new Point(265, 85), true);
        Circle eye2 = new Circle(5, new Point(235, 85), true);
        Segment mouth = new Segment(new Point(235, 100), new Point(265, 100));
        Segment arm1 = new Segment(new Point(170, 120), new Point(210, 150));
        Segment arm2 = new Segment(new Point(290, 160), new Point(300, 200));
        Triangle hatFragment1 = new Triangle(new Point(200, 60), new Point(300, 60), new Point(250, 30), true);

        Segment hatFragment = new Segment(new Point(200, 60), new Point(300, 60));
        Rect hat = new Rect(new Point(220, 30), new Point(280, 60), true);

        NVerStar star1 = new NVerStar(20, new Point(170, 120), 18, true);

        ComplexItem complexItem = new ComplexItem();
        complexItem.addChild(circle1);
        complexItem.addChild(circle2);
        complexItem.addChild(circle3);
        complexItem.addChild(eye1);
        complexItem.addChild(eye2);
        complexItem.addChild(mouth);
        complexItem.addChild(arm1);
        complexItem.addChild(arm2);
        complexItem.addChild(hatFragment);
        complexItem.addChild(hat);
        complexItem.addChild(hatFragment1);
        complexItem.addChild(star1);


        complexItem.translate(new Point(0, 100));
        scene.addChildren(new TextItem("null", 10, 10));
        scene.addChildren(complexItem);
        scene.addChildren(star1);
        scene.draw();
    }
}
