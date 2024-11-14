import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> linesList = new ArrayList<>();;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.IllegalArgumentException("points is null");
        for (Point i : points) {
            if (i == null) throw new IllegalArgumentException("point null");
        }
        Point[] pointsSorted = points.clone();
        Arrays.sort(pointsSorted);
        for (int i = 0; i < pointsSorted.length-1; i++) {
            if (pointsSorted[i].compareTo(pointsSorted[i+1]) == 0) {
                throw new IllegalArgumentException("no duplicate points");
            }
        }
        int n = pointsSorted.length;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p1 = pointsSorted[i];
                        Point p2 = pointsSorted[j];
                        Point p3 = pointsSorted[k];
                        Point p4 = pointsSorted[l];

                        // Check if these 4 points are collinear
                        if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p2) == p1.slopeTo(p4)) {
                            linesList.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments()        // the number of line segments
    {
        return linesList.size();
    }
    public LineSegment[] segments()                // the line segments 
    {
        return linesList.toArray(new LineSegment[0]);
    }

 }