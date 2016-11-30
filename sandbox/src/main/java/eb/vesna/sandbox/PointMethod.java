package eb.vesna.sandbox;

/*
 класс Point с методом вычисления расстояния
 */
public class PointMethod {
    public double x;
    public double y;
    public PointMethod(double x, double y){
        this.x = x;
        this.y = y;

    }
    public double dist(PointMethod nextPointMethod){
        double dx = this.x - nextPointMethod.x;
        double dy = this.y - nextPointMethod.y;
        return Math.sqrt(dx * dx + dy * dy);

    }


}
