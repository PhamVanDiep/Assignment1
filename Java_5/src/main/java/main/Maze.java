package main;

import javax.swing.JFrame;

public final class Maze {

    /**
     * Width of the frame.
     */
    private static final int WIDTH = 650;

    /**
     * Height of the frame.
     */
    private static final int HEIGHT = 470;

    private Maze() {

    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        //
        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MazePanel mp = new MazePanel();
        frame.add(mp);
        frame.setVisible(true);
    }
}
