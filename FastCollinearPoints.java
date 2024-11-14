import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FastCollinearPoints {

    private final List<LineSegment> lines = new ArrayList<>();

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null) throw new IllegalArgumentException("points is null");
        
        // Validate each point
        for (Point i : points) {
            if (i == null) throw new IllegalArgumentException("point is null");
        }

        Point[] fastPoints = points.clone();
        Arrays.sort(fastPoints);
        
        for (int i = 0; i < fastPoints.length - 1; i++) {
            if (fastPoints[i].compareTo(fastPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("there are duplicate points");
            }
        }

        for (Point p : points) {
            
            int count = 1;
            Point[] slopesort = points.clone();
            Arrays.sort(slopesort, p.slopeOrder());

            
            while (count < slopesort.length) {
                List<Point> collinear = new ArrayList<>();
                final double slope = p.slopeTo(slopesort[count]);


                while (count < slopesort.length && p.slopeTo(slopesort[count]) == slope) {
                    collinear.add(slopesort[count]);
                    count++;
                }

                if (collinear.size() >= 3 && p.compareTo(collinear.get(0)) < 0) {
                    collinear.add(p);
                    collinear.sort(Point::compareTo); // sort points in ascending order
                    if (p.compareTo(collinear.get(0)) == 0) {
                        lines.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                    }
                }
            }
        }

    }
    public int numberOfSegments()        // the number of line segments
    {
        return lines.size();
    }
    public LineSegment[] segments()                // the line segments
    {
        return lines.toArray(new LineSegment[0]);
    }
 }