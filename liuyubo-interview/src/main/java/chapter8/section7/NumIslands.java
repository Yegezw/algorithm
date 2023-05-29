package chapter8.section7;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/description/">200 - 岛屿数量</a>
 */
@SuppressWarnings("all")
public class NumIslands {

    private static int d[][] = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    private static int m, n; // m 行 n 列
    private static boolean visited[][];

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    /**
     * 从 grid[x][y] 的位置开始, 进行 floodfill
     * 保证 (x, y) 合法, 且 grid[x][y] 是没有被访问过的陆地
     */
    private static void dfs(char[][] grid, int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newx = x + d[i][0];
            int newy = y + d[i][1];
            if (inArea(newx, newy) && !visited[newx][newy] && grid[newx][newy] == '1') dfs(grid, newx, newy);
        }
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        char grid1[][] = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid1)); // 1
        
        char grid2[][] = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid2)); // 3
    }
}
