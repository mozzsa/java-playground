import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {

        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {

        int i =0 ;
        for(Point currPt : s.getPoints()){
            i += 1;
        }
        return i;
    }

    public double getAverageLength(Shape s) {

        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {

        Point prevPt = s.getLastPoint();
        double prevDist = 0 ;
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
              double currDist = prevPt.distance(currPt);
              if(currDist>=prevDist){
                  prevDist = currDist;
              }
              // Update prevPt to be currPt
            prevPt = currPt;
        }
        return prevDist;
    }

    public double getLargestX(Shape s) {

        double prevX = 0.0 ;
        for(Point currPt : s.getPoints()){
            double currX=currPt.getX();
            if(currX>=prevX){
                prevX = currX;
            }
        }
        return prevX;
    }

    public double getLargestPerimeterMultipleFiles() {

        DirectoryResource dr = new DirectoryResource();
        double prevlength = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currlength = getPerimeter(s);
            if(currlength>prevlength){
                prevlength= currlength;
            }
        }
        return prevlength;
    }

    public String getFileWithLargestPerimeter() {

        DirectoryResource dr = new DirectoryResource();
        double prevlength = 0.0;
        String file_n = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currlength = getPerimeter(s);
            if(currlength>prevlength){
                prevlength= currlength;
                file_n= f.getName();
            }
        }
        return file_n;
    }

    public void testPerimeter () {

        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int num_points = getNumPoints(s);
        double average_length = getAverageLength(s);
        double longer_side = getLargestSide(s);
        double largest_X = getLargestX(s);

        System.out.println("perimeter = " + length);
        System.out.println("num_points = " + num_points);
        System.out.println("average_length= " + average_length);
        System.out.println("longer_side= " + longer_side);
        System.out.println("largest_X= " + largest_X);
    }

    public void testPerimeterMultipleFiles() {

        double multiperi = getLargestPerimeterMultipleFiles();

        System.out.println("largest_perimeter_multiple "+multiperi);
    }

    public void testFileWithLargestPerimeter() {

        String file_name = getFileWithLargestPerimeter();

        System.out.println(file_name);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){

        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {

        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr. testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();

    }
}
