package eb.vesna.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
 Created by Elena_Bogomolova on 30.11.2016.
 */
public class PointTests {

    @Test
        public void testArea() {
        PointMethod p3 = new PointMethod(8, 7);
        PointMethod p4 = new PointMethod(5,3);
        Assert.assertEquals(p3.dist(p4), 5.0);

    }
    @Test
    public void testArea2() {
        PointMethod p5 = new PointMethod(10, 10);
        PointMethod p6 = new PointMethod(4,18);
        Assert.assertEquals(p5.dist(p6), 10.0);

    }

}

