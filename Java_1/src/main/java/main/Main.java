package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public final class Main {
    /**
     * data set 1.
     */
    private static Set<Integer> set1;

    /**
     * data set 2.
     */
    private static Set<Integer> set2;

    /**
     * intersect of data set 1 and data set 2.
     */
    private static Set<Integer> intersect;

    /**
     * union of data set 1 and data set 2.
     */
    private static Set<Integer> union;

    /**
     * capacity of each data set.
     */
    private static final int CONSTANT_CAPACITY1 = 200000;

    /**
     * minimum matching numbers of two data set.
     */
    private static final int CONSTANT_MINIMUM_MATCHING_NUMBERS1 = 10;

    private Main() {

    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        init();
        initValue();
        intersect();
        union();
        writeIntersect();
        writeUnion();
    }

    /**
     * write data of intersect set to Intersect text file.
     */
    private static void writeIntersect() {
        try {
            FileWriter myWriter = new FileWriter("Intersect.txt");
            Iterator<Integer> iterator = intersect.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                myWriter.write(String.valueOf(next) + '\n');
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write data of union set to Union text file.
     */
    private static void writeUnion() {
        try {
            FileWriter myWriter = new FileWriter("Union.txt");
            Iterator<Integer> iterator = union.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                myWriter.write(String.valueOf(next) + '\n');
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * union method.
     */
    private static void union() {
        union.addAll(set1);
//        System.out.println(union.size());
        union.addAll(set2);
//        System.out.println(union);
        System.out.println(union.size());
    }

    /**
     * intersect method.
     */
    private static void intersect() {
        intersect.addAll(set1);
        intersect.retainAll(set2);
//        System.out.println(intersect);
        System.out.println(intersect.size());
    }

    /**
     * initialize value for data set 1 and data set 2.
     */
    private static void initValue() {
        // initialize matching numbers of data set 1 and 2
        for (int i = 0; i < CONSTANT_MINIMUM_MATCHING_NUMBERS1; i++) {
            int rand = getRandomInteger();
            set1.add(rand);
            set2.add(rand);
        }

        while (set1.size() < CONSTANT_CAPACITY1) {
            set1.add(getRandomInteger());
        }

        System.out.println("Set 1 completed " + set1.size());

        while (set2.size() < CONSTANT_CAPACITY1) {
            set2.add(getRandomInteger());
        }

        System.out.println("Set 2 completed " + set2.size());
    }

    /**
     * initialize objects.
     */
    private static void init() {
        set1 = new HashSet<>();
        set2 = new HashSet<>();
        intersect = new HashSet<>();
        union = new HashSet<>();
    }

    /**
     * @return random integer
     */
    private static int getRandomInteger() {
        Random random = new Random();
        int upperbound = Integer.MAX_VALUE;
        return random.nextInt(upperbound);
    }
}
