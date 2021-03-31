package softwareproblems.week1.assignment2;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {

    private File fileNameWithHighestPerimeter = null;

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

    public void testPerimeter ()
    {
        FileResource fileResource = new FileResource();
        Shape shape = new Shape(fileResource);
        double length = getPerimeter(shape);
        System.out.println("perimeter = " + length);

        System.out.println("Total number of points in a shape = "+getNumPoints(shape));

        System.out.println("Average length of the shape = "+getAverageLength(shape));

        System.out.println("Length of largest side in the shape = "+getLargestSide(shape));

        System.out.println("Largest X coordinate among all the points of the shape = "+getLargestX(shape));
    }

    public double getLargestPerimeterMultipleFiles() {

        double largestPerimeterMultipleFiles = -1.0 ;

        double currentPerimeter;

        DirectoryResource directoryResource = new DirectoryResource();

        for(File currentFile: directoryResource.selectedFiles())
        {
            FileResource fileResource = new FileResource(currentFile);
            Shape shape = new Shape(fileResource);

            currentPerimeter = getPerimeter(shape);
            if(largestPerimeterMultipleFiles < currentPerimeter){
                largestPerimeterMultipleFiles = currentPerimeter;
                fileNameWithHighestPerimeter = currentFile;
            }
        }
        return largestPerimeterMultipleFiles;

    }

    public File getFileWithLargestPerimeter() {

        getLargestPerimeterMultipleFiles();
        return fileNameWithHighestPerimeter;
    }

    public void testPerimeterMultipleFiles() {

        System.out.println("Largest perimeter among all the selected shapes = "+getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {

        String fileWithLargestPerimeter = getFileWithLargestPerimeter().getName();
        System.out.println("File name with a shape having the largest perimeter among all the selected files = "+fileWithLargestPerimeter);
    }
    public static void main (String[] args) {
        PerimeterAssignmentRunner perimeterAssignmentRunner = new PerimeterAssignmentRunner();
        perimeterAssignmentRunner.testPerimeter();
        perimeterAssignmentRunner.testPerimeterMultipleFiles();
        perimeterAssignmentRunner.testFileWithLargestPerimeter();
    }
}