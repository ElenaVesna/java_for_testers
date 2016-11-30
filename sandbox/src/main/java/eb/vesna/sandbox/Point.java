package eb.vesna.sandbox;

/**
 * Created by Elena_Bogomolova on 29.11.2016.
 */
public class Point {

    public double x;
    public double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;

    }
    public double dist(Point nextPoint){
        double dx = this.x - nextPoint.x;
        double dy = this.y - nextPoint.y;
        return Math.sqrt(dx * dx + dy * dy);

    }


}
