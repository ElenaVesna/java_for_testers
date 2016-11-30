package eb.vesna.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 7);
        /*
         выводим расстояние, используя функцию distance
         */
        System.out.println("расстояние между точками с координатами (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") равно " + distance(p1,p2));


        /*
         вычисляем расстояние, используя метод в классе PointMethod
         */
        PointMethod pp1 = new PointMethod(2, 3);
        PointMethod pp2 = new PointMethod(5, 7);
        System.out.println("расстояние между точками с координатами (" + pp1.x + ", " + pp1.y + ") и (" + pp2.x + ", " + pp2.y + ") равно " + pp1.dist(pp2));



    }
	public static double distance(Point p1, Point p2){
	    double disX = p1.x - p2.x;
	    double disY = p1.y - p2.y;
	    double disX2 = disX * disX;
	    double disY2 = disY * disY;
	    return Math.sqrt(disX2 + disY2);
    }


}
