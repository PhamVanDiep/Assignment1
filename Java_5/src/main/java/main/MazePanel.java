package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author dieppv
 */
public class MazePanel extends JPanel {
    /**
     * matrix row.
     */
    private static final int MATRIX_ROW = 21;

    /**
     * matrix width.
     */
    private static final int MATRIX_COL = 31;

    /**
     * width and height scale.
     */
    private static final int DIM_SCALE = 20;

    /**
     * coordinator x.
     */
    private static final int COOR_X = 20;

    /**
     * coordinator y.
     */
    private static final int COOR_Y = 20;

    /**
     * serial version uid.
     */
    private static final long SERIAL_VERSION_UID = -566807999447681130L;

    /**
     * start point row.
     */
    private static final int START_R = 10;

    /**
     * start point column.
     */
    private static final int START_C = 0;

    /**
     * marked color value.
     */
    private static final int MARK_COLOR = 3;

    /**
     * initialize matrix.
     */
    private int[][] maze = {// khởi tạo ma trận mảng 2 chiều
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
        {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0,
            0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
        {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
        {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
        {1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1 },
        {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            0, 2, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1 },
        {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0,
            0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 },
        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
            0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 },
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
            0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

    /**
     * visited matrix.
     */
    private boolean[][] visited;

    /**
     * constructor.
     */
    public MazePanel() {
        visited = new boolean[MATRIX_ROW][MATRIX_COL];
        solve();
        repaint(); // vẽ ma trận và lời giải
    }

    /**
    * Implement một phương pháp tìm đường nào đó.
    * <p>
    * Yêu cầu : Vẽ đường đi từ điểm bắt đầu đến điểm kết thúc. Không hiện
    * các phần thừa - là các phần đường cụt hoặc đường đi bị sai. Chỉ vẽ
    * tuyến đường chính đi từ điểm đầu (màu vàng) đến màu đỏ.
    * <p>
    * Đường đi từ điểm đầu đến điểm cuối được tô màu xanh dương, để tô màu
    * xanh dương hãy set giá trị của điểm trên ma trận sang một số > 2
    */
    public void solve() {
        // Hàm này chứa phương pháp tìm đường từ điểm start đến vị
        // trí màu đỏ trên ma trận
        Element startElement = new Element(START_R, START_C, null);
        visited[startElement.getI()][startElement.getJ()] = true;

        Queue<Element> queue = new ArrayDeque<>();
        queue.add(startElement);

        while (!queue.isEmpty()) {
            Element element = queue.poll();
            int rowNum = element.getI();
            int colNum = element.getJ();
//            System.out.println(rowNum + " " + colNum);
            // Destination found.
            if (maze[rowNum][colNum] == 2) {
//                System.out.println("found");
                changeColor(element);
                maze[rowNum][colNum] = 2;
                return;
            }

            if (checkCross(rowNum - 1, colNum)) {
                Element next = new Element(rowNum - 1, colNum, element);
                queue.add(next);
                visited[rowNum - 1][colNum] = true;
            }

            if (checkCross(rowNum + 1, colNum)) {
                Element next = new Element(rowNum + 1, colNum, element);
                queue.add(next);
                visited[rowNum + 1][colNum] = true;
            }

            if (checkCross(rowNum, colNum - 1)) {
                Element next = new Element(rowNum, colNum - 1, element);
                queue.add(next);
                visited[rowNum][colNum - 1] = true;
            }

            if (checkCross(rowNum, colNum + 1)) {
                Element next = new Element(rowNum, colNum + 1, element);
                queue.add(next);
                visited[rowNum][colNum + 1] = true;
            }
        }
    }

    /**
     * router.
     * @param root
     */
    private void changeColor(final Element root) {
        if (root == null) {
            return;
        }
        maze[root.getI()][root.getJ()] = MARK_COLOR;
        changeColor(root.getParentElement());
    }

    /**
     * check if a cell was not visited.
     * @param rowNum
     * @param colNum
     * @return isValid cell
     */
    private boolean checkCross(final int rowNum, final int colNum) {
        if (rowNum >= 0 && colNum >= 0
                && rowNum < MATRIX_ROW && colNum < MATRIX_COL
                && maze[rowNum][colNum] != 1
                && !visited[rowNum][colNum]) {
            return true;
        }
        return false;
    }
    /**
     * render matrix and solution.
     * @param g
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (int j = 0; j < MATRIX_COL; j++) {
            for (int i = 0; i < MATRIX_ROW; i++) {
                if (i == START_R && j == START_C) {
                    g.setColor(Color.yellow);
                } else if (maze[i][j] == 0) {
                    g.setColor(Color.white);
                } else if (maze[i][j] == 1) {
                    g.setColor(Color.black);
                } else if (maze[i][j] == 2) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.blue); // blue là màu của lời giải
                }
                g.fillRect(j * DIM_SCALE, i * DIM_SCALE, COOR_X, COOR_Y);
            }
        }
    }
}
