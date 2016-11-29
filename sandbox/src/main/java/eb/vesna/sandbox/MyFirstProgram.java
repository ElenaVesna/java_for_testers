package eb.vesna.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 7);
        System.out.println("расстояние между точками с координатами (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") равно " + distance(p1,p2));
	}
	public static double distance(Point p1, Point p2){
	    double disX = p1.x - p2.x;
	    double disY = p1.y - p2.y;
	    double disX2 = disX * disX;
	    double disY2 = disY * disY;
	    return Math.sqrt(disX2 + disY2);
    }



}
