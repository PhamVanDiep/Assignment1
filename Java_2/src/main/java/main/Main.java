package main;

import  java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public final class Main {
    /**
     * convert char 'A' to int.
     */
    public static final int FIRST1 = 65;
    /**
     * convert char 'Z' to int.
     */
    public static final int SECOND1 = 90;
    /**
     * convert char 'a' to int.
     */
    public static final int FIRST2 = 97;
    /**
     * convert char 'z' to int.
     */
    public static final int SECOND2 = 122;

    private Main() {

    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        HashMap<String, Integer> result = new HashMap<>();
        String line;

        try {
            // FileReader file = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    Main.class.getResourceAsStream("/input.txt"),
                    StandardCharsets.UTF_8));

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                int length = words.length;
                for (int i = 0; i < length; i++) {
                    String data = fixString(words[i]);
                    // String data = words[i];
                    if (result.containsKey(data)) {
                        result.put(data, result.get(data) + 1);
                    } else {
                        result.put(data, 1);
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("output.txt");
            for (String key: result.keySet()) {
                // System.out.println(key + " " + result.get(key));
                myWriter.write(key + " " + result.get(key) + '\n');
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reformat string to another string that only have alphabet characters.
     *
     * @param s
     * @return a reformat String
     */
    public static String fixString(final String s) {
        if (s.length() < 1) {
            return s;
        }
        char c = s.charAt(s.length() - 1);
        if ((c >= FIRST1 && c <= SECOND1) || (c >= FIRST2 && c <= SECOND2)) {
            return s;
        } else {
            if (s.length() == 1) {
                return s;
            }
            return fixString(s.substring(0, s.length() - 1));
        }
    }
}
