package chapter8.section6;

/**
 * <a href="https://leetcode.cn/problems/word-search/">79 - 单词搜索</a>
 */
@SuppressWarnings("all")
public class Exist {

    // 上右下左
    private static int dir[][] = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    private static int m, n; // m - 1 行 n - 1 列
    private static boolean[][] visited;

    public static boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j)) return true;
            }
        }

        return false;
    }

    /**
     * 从 board[startx][starty] 开始, 寻找 word[index ... word.length)
     */
    private static boolean searchWord(char[][] board, String word, int index, int startx, int starty) {
        if (index == word.length() - 1) return board[startx][starty] == word.charAt(index);

        if (board[startx][starty] == word.charAt(index)) {
            visited[startx][starty] = true;  // 做选择
            // 从 (startx, starty) 出发, 向四个方向寻找
            for (int i = 0; i < 4; i++) {
                int newx = startx + dir[i][0];
                int newy = starty + dir[i][1];
                if (inArea(newx, newy) && !visited[newx][newy] && searchWord(board, word, index + 1, newx, newy))
                    return true;
            }
            visited[startx][starty] = false; // 取消选择
        }

        return false;
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String words[] = {"ABCCED", "SEE", "ABCB"};

        for (int i = 0; i < words.length; i++) {
            if (exist(board1, words[i])) System.out.println("found " + words[i]);
            else System.out.println("can not found " + words[i]);
        }

        char[][] board2 = {
                {'A'}
        };
        if (exist(board2, "AB")) System.out.println("found AB");
        else System.out.println("can not found AB");
    }
}
