package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 200. 岛屿数量
 */
public class NumIslands {
    // 方向数组
    private static final int[][] DIRS = {
            {1, 0}, // 向下
            {-1, 0}, // 向上
            {0, 1}, // 向右
            {0, -1} // 向左
    };

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    count++;
                    queue.offerLast(new int[]{i, j});
                    // while 循环在发现新岛屿后才开始，避免放外面每一次都需要对队列进行判空
                    while (!queue.isEmpty()) {
                        int[] curr = queue.pollFirst();
                        int row = curr[0];
                        int col = curr[1];
                        for (int[] dir : DIRS) {
                            int newRow = row + dir[0];
                            int newCol = col + dir[1];
                            if (newRow >= 0 && newRow < m
                                    && newCol >= 0 && newCol < n
                                    && grid[newRow][newCol] == '1'
                            ) {
                                grid[newRow][newCol] = '0';
                                queue.offerLast(new int[]{newRow, newCol});
                            }

                        }

                    }
                }
            }
        }
        return count;
    }
}
