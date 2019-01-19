package notebook;

/**
 * https://blog.csdn.net/wanghaoalain/article/details/78966335
 * @author AlanGogogo
 * 终止条件： <br>
 * 如果所在格子是迷宫的最右下角，且该格子数值为1，那么迷宫走通，递归结束。
 *<br>
 * 递归内容<br>
 * 如果所在格子是迷宫的第m行，第n列，则进行如下判断<br>
 * a. 如果该格子是在迷宫矩阵中，且数值为1，那么我们将齐标记为以判定格子（改写数值为3）<br>
 * b. 然后分别判断该格子的上下左右相邻格子是否为通路 <br>
 * c. 如果相邻格子中有一个递归判断是通路，则当前格子在走出迷宫的道路上（改写数值为7）<br>
 * d. 向上层返回对格子是否在通路上的判断<br>
 */

public class Maze {

    public static int UNBLOCKED = 1;

    public static int TRIED = 3;

    public static int PATH = 7;

    public static int[][] MAZE = { { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

    public static boolean traverse(int row, int column) {
        boolean isValidPath = false;

        if (valid(row, column)) {
            MAZE[row][column] = TRIED;

            if (row == MAZE.length - 1 && column == MAZE[0].length - 1) {
                isValidPath = true;
            } else {
                isValidPath = traverse(row + 1, column); // down
                if (!isValidPath) {
                    isValidPath = traverse(row, column + 1); // right
                }
                if (!isValidPath) {
                    isValidPath = traverse(row - 1, column); // up
                }
                if (!isValidPath) {
                    isValidPath = traverse(row, column - 1); // left
                }
            }

            if (isValidPath) {
                MAZE[row][column] = PATH;
            }
        }

        return isValidPath;
    }

    public static boolean valid(int row, int column) {
        boolean result = false;

        if (row >= 0 && row < MAZE.length && column >= 0 && column < MAZE[row].length) {
            if (MAZE[row][column] == UNBLOCKED) {
                result = true;
            }
        }

        return result;
    }

    public static void printMaze() {
        for (int[] row : Maze.MAZE) {
            for (int column : row) {
                System.out.print("" + column);
            }
            System.out.println();
        }
    }
}
