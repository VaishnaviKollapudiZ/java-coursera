
package softwareproblems.week1.assignment1;
import edu.duke.*;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape shape) {

        double totalPerimeter = 0.0;
        Point previousPoint = shape.getLastPoint();
        for (Point currentPoint : shape.getPoints()) {
            double currentDist = previousPoint.distance(currentPoint);
            totalPerimeter = totalPerimeter + currentDist;
            previousPoint = currentPoint;
        }
        return totalPerimeter;
    }

    public int getNumPoints (Shape shape) {

        return shape.shapeSize();
    }

    public double getAverageLength(Shape shape) {

        return getPerimeter(shape)/getNumPoints(shape);

    }

    public double getLargestSide(Shape shape) {

        double largestSide = -1;
        Point previousPoint = shape.getLastPoint();
        for (Point currentPoint : shape.getPoints()) {
            double currDist = previousPoint.distance(currentPoint);
            largestSide = Math.max(largestSide,currDist);
            previousPoint = currentPoint;
        }
        return largestSide;
    }

    public double getLargestX(Shape shape) {

        double largestX = Double.MIN_VALUE;
        for(Point point:shape.getPoints()){
            largestX = Math.max(largestX,point.getX());
        }
        return largestX;
    }

    public void testPerimeter () {
        FileResource fileResource = new FileResource();
        Shape shape = new Shape(fileResource);
        double perimeter = getPerimeter(shape);
        System.out.println("perimeter = " + perimeter);

        System.out.println("Total number of points in a shape = "+getNumPoints(shape));

        System.out.println("Average length of the shape = "+getAverageLength(shape));

        System.out.println("Length of largest side in the shape = "+getLargestSide(shape));

        System.out.println("Largest X coordinate among all the points of the shape = "+getLargestX(shape));
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner perimeterAssignmentRunner = new PerimeterAssignmentRunner();
        perimeterAssignmentRunner.testPerimeter();
    }
}