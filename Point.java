
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new slopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

//    private Point current;

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /*  method should return the slope between 
        the invoking point (x0, y0) and the argument 
        point (x1, y1), which is given by the formula 
        (y1 − y0) / (x1 − x0). Treat the slope of a 
        horizontal line segment as positive zero 
        [added 7/29]; treat the slope of a vertical 
        line segment as positive infinity; treat the 
        slope of a degenerate line segment (
        between a point and itself) as negative infinity.
        */

		if (that.x==this.x){
			if(this.y==that.y){
				return Double.NEGATIVE_INFINITY;
			}
			else{
				return Double.POSITIVE_INFINITY;
			}
		}
		if(that.y==this.y){
			return 0.0;
		}
		//System.out.println(this.y+" and "+that.y+"\n");
		double slope=(double)(that.y-this.y)/(that.x - this.x);
		return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* The compareTo() method should compare points by their y-coordinates, 
        breaking ties by their x-coordinates. Formally, 
        the invoking point (x0, y0) is less than the argument 
        point (x1, y1) if and only if either 
        y0 < y1 or if y0 = y1 and x0 < x1.*/

        if (this.y<that.y)return -1;
        if (this.y>that.y)return 1;
        if (this.x<that.x)return -1;
        if (this.x>that.x)return 1;
        return 0;

    }

    // return string representation of this point
    public String toString() 
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class slopeOrder implements Comparator<Point>
    {
	    /*The SLOPE_ORDER comparator should compare 
	    points by the slopes they make with the invoking 
	    point (x0, y0). Formally, the point (x1, y1) is 
	    less than the point (x2, y2) if and only if the 
	    slope (y1 − y0) / (x1 − x0) is less than the 
	    slope (y2 − y0) / (x2 − x0). Treat horizontal, 
	    vertical, and degenerate line segments as in the 
	    slopeTo() method */

    	public int compare(Point p1, Point p2)
    	{
    		if(p1==null)throw new java.lang.NullPointerException("p1 is null");
    		if(p2==null)throw new java.lang.NullPointerException("p2 is null");

    		double slopep1 = Point.this.slopeTo(p1);
    		double slopep2 = Point.this.slopeTo(p2);

    		if (slopep1 < slopep2)  return -1;
    		if (slopep1 > slopep2) return 1;
			return 0;
    	}
    }



    // unit test
    public static void main(String[] args) {
        
		//StdDraw.setYscale(0, 32768);        
		//StdDraw.setXscale(0, 32768);        		
        Point p1=new Point(19000,10000);
        Point p2=new Point(1234,5678);
        Point p3=new Point(18000, 10000);
        int cmp = p1.compareTo(p2);
        System.out.print("p1p3 "+p1.slopeTo(p3)+"\n");
        System.out.print("p2p3 "+p2.slopeTo(p3)+"\n");
        System.out.print("the comparison is "+cmp);
        //p1.drawTo(p2);
    }

}