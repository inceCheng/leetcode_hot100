package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 994. 腐烂的橘子
 */
public class OrangesRotting {

    // 初始化统计，遍历整个网格，将所有初始腐烂的橘子的坐标加入队列，他们相当于第 0 分钟时的感染源
    // 同时统计新鲜的橘子，值为 1 的总数 freshCount，如果初始没有新鲜橘子，直接返回 0
    // 按层 bfs遍历，模拟时间流逝，使用一个变量 minutes 记录经过的分钟数，
    // 当队列不为空，并且还有新鲜橘子时（freshCount>0），开始一层的遍历，代表过了一分钟
    // 记录当前队列的长度size,依次将这size个腐烂橘子出队，并检查他们上下左右的 4 个相邻位置
    // 如果是新鲜橘子，那么就标记腐烂，并且入队，新鲜橘子数量减 1
    // 这一层处理完后，minutes + 1
    // bfs结束后，检查 freshCount是否是 0，如果是 0 ，返回 minutes，否则返回 -1，说明永远不会腐烂
    // 0 代表空，
    // 1 代表新鲜
    // 2 代表腐烂
    // 定义方向数组
    private static final int[][] DIRS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int orangesRotting(int[][] grid) {
        Deque<int[]> queue = new ArrayDeque<>();
        int freshCount = 0;
        int minutes = 0;
        int m = grid.length;
        int n = grid[0].length;
        // 初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.offerLast(new int[]{i, j});
                }
            }
        }

        // 这里需要加上 freshCount>0 的约束，如果不加，那么在最后一批橘子腐烂的时候，他们会被加入队列，还会进入循环，minutes 会额外加 1
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.pollFirst();
                int row = curr[0];
                int col = curr[1];
                for (int[] dir : DIRS) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    if (newRow >= 0 && newRow < m
                            && newCol >= 0 && newCol < n
                            && grid[newRow][newCol] == 1
                    ) {
                        grid[newRow][newCol] = 2;
                        freshCount--;
                        queue.offerLast(new int[]{newRow, newCol});
                    }
                }
            }
            minutes++;
        }

        if (freshCount > 0) {
            return -1;
        }
        return minutes;
    }
}
