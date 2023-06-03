package practice1_array;

/**
 * <a href="https://leetcode.cn/problems/spiral-matrix-ii/">59 - 螺旋矩阵 II</a>
 */
@SuppressWarnings("all")
public class Solution5 {

    public int[][] generateMatrix1(int n) {
        int[][] arr = new int[n][n];
        int num = 1;
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;

        while (num <= n * n) {
            for (int i = left; i <= right; i++) arr[top][i] = num++;    // 向右填充
            top++;
            for (int i = top; i <= bottom; i++) arr[i][right] = num++;  // 向下填充
            right--;
            for (int i = right; i >= left; i--) arr[bottom][i] = num++; // 向左填充
            bottom--;
            for (int i = bottom; i >= top; i--) arr[i][left] = num++;   // 向上填充
            left++;
        }

        return arr;
    }

    public int[][] generateMatrix2(int n) {
        int[][] arr = new int[n][n];

        int a = 0; // 行 0 <= a <= n - 1
        int b = 0; // 列 0 <= b <= n - 1
        int dir = 1; // 方向: 右 = 1, 下 = 2, 左 = 3, 上 = 4

        // arr[a][b] = i
        int i = 1;
        while (i <= n * n) {
            arr[a][b] = i++;

            while (i <= n * n) {
                if (dir == 1) {
                    if (b + 1 < n && arr[a][b + 1] == 0) {
                        b++;
                        break;
                    } else dir = 2;
                }
                if (dir == 2) {
                    if (a + 1 < n && arr[a + 1][b] == 0) {
                        a++;
                        break;
                    } else dir = 3;
                }
                if (dir == 3) {
                    if (b - 1 >= 0 && arr[a][b - 1] == 0) {
                        b--;
                        break;
                    } else dir = 4;
                }
                if (dir == 4) {
                    if (a - 1 >= 0 && arr[a - 1][b] == 0) {
                        a--;
                        break;
                    } else dir = 1;
                }
            }
        }

        return arr;
    }
}
