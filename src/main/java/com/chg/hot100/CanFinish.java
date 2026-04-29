package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 207. 课程表
 */
public class CanFinish {
    // 本质就是判断有向图中是否存在环，如果存在环就返回 false，否则返回 true
    // 1. 统计所有课程的入度，入度为 0 说明这个课程没有前置课程，可以学习
    // 2. 构建邻接表，记录学完课程 a之后，可以解锁哪些课程
    // 3. 队列流转，将所有入度为 0 的课程，放入队列，每次从队列中拿一个课程，代表学完了，然后将它指向的所有课程入度减 1
    // 若减完之后入度为 0 ，则将这个课程入队
    // 统计出队的课程数量，是否是 numCourses，如果是，说明全部学完，返回 true,否则返回 false
    public boolean canFinish(int numCourses, int[][] prerequires) {
        // 存储所有课程的入度
        int[] inDegrees = new int[numCourses];
        // 构建邻接表，记录学完课程a后，可以解锁哪些课程fd
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // 遍历课程数组，对入度表和邻接表初始化
        for (int[] info : prerequires) {
            int cur = info[0];
            int pre = info[1];
            inDegrees[cur]++; // 当前课程入度+1
            adjacency.get(pre).add(cur); // 记录学完 pre 可以解锁 cur
        }
        // 将所有入度为 0 的课程加入队列
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offerLast(i);
            }
        }

        // BFS 拓扑排序
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int pre = queue.pollFirst();
            // 访问过的课程数量+1
            visitedCount++;
            for (int cur : adjacency.get(pre)) {
                // 入度-1
                inDegrees[cur]--;
                // 如果入度为 0，加入访问队列
                if (inDegrees[cur] == 0) {
                    queue.offerLast(cur);
                }
            }
        }
        return visitedCount == numCourses;
    }

    // DFS 染色法
    // 每个节点有三种状态，0-未访问；1- 正在访问； 2- 访问完成
    // 在访问过程中，遇到 0 的节点，继续 DFS
    // 遇到 1 的节点，说明遇到了当前路径上的节点，存在环
    // 遇到 2 的节点，说明已经确认它后面无环，可以跳过
    /*public boolean canFinish(int numCourses, int[][] prerequires) {
        // 邻接表，记录学完课程 a后，解锁的课程
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequires) {
            int course = pre[0];
            int prerequisite = pre[1];
            graph.get(prerequisite).add(course);
        }

        int[] state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(graph, state, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph, int[] state, int cur) {
        if (state[cur] == 1) {
            return true;
        }

        if (state[cur] == 2) {
            return false;
        }
        state[cur] = 1;
        for (int next : graph.get(cur)) {
            if (hasCycle(graph, state, next)) {
                return true;
            }
        }
        state[cur] = 2;
        return false;
    }*/

}
