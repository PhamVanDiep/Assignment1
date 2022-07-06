package com.mycompany.java_4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author dieppv
 */
public final class Main {

    /**
     * coordinator x of point A.
     */
    private static final int COORX_A = 800;
    /**
     * coordinator y of point A.
     */
    private static final int COORY_A = 800;
    /**
     * coordinator x of point B.
     */
    private static final int COORX_B = 4000;
    /**
     * coordinator y of point B.
     */
    private static final int COORY_B = 800;
    /**
     * coordinator x of point C.
     */
    private static final int COORX_C = 2400;
    /**
     * coordinator y of point C.
     */
    private static final int COORY_C = 2400;

    /**
     * number of points have constraint with point A.
     */
    private static final int NUM_A = 8000;
    /**
     * number of points have constraint with point B.
     */
    private static final int NUM_B = 10000;
    /**
     * number of points have constraint with point A.
     */
    private static final int NUM_C = 12000;

    /**
     * distance constraint to point A.
     */
    private static final int DIS_A = 400;
    /**
     * distance constraint to point B.
     */
    private static final int DIS_B = 500;
    /**
     * distance constraint to point C.
     */
    private static final int DIS_C = 600;

    private Main() {

    }

    /**
     * main function.
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        Point pointA = new Point(COORX_A, COORY_A, 0);
        Point pointB = new Point(COORX_B, COORY_B, 0);
        Point pointC = new Point(COORX_C, COORY_C, 0);

        Set<Point> points = new HashSet<>();
        points.addAll(generate(NUM_A, pointA, DIS_A));
        points.addAll(generate(NUM_B, pointB, DIS_B));
        points.addAll(generate(NUM_C, pointC, DIS_C));

        FileWriter fileWriter = new FileWriter("output4.txt");
        Iterator<Point> itr = points.iterator();
        while (itr.hasNext()) {
            Point next = itr.next();
            fileWriter.write(next.toString());
        }
        fileWriter.close();
    }

    /**
     * get set of points with constraint.
     * @param maxPoints
     * @param des
     * @param maxDis
     * @return set of points
     */
    public static Set<Point> generate(final int maxPoints, final Point des,
            final int maxDis) {
        Set<Point> pointSet = new HashSet<>();
        Random random = new Random();
        while (pointSet.size() < maxPoints) {
            int x = random.nextInt(2 * maxDis) + des.getX() - maxDis;
            int y = random.nextInt(2 * maxDis) + des.getY() - maxDis;
            double dis = Math.sqrt(Math.pow(x - des.getX(), 2))
                    + Math.sqrt(Math.pow(y - des.getY(), 2));
            if (dis <= maxDis) {
                pointSet.add(new Point(x, y, dis));
            }
        }
        return pointSet;
    }
}
