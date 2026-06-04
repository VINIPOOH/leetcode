package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule207 {

    public static void main(String[] args) {
        System.out.println(new CourseSchedule207().canFinish(3, new int[][] { { 1, 0 }, { 1, 2 }, { 0, 1 } }));
    }

    //мое изначальное решение идея верна реализация не идеально красивая
    Map<Integer, List<Integer>> courseToPreRequirements;
    boolean[] isCourseDoable;

    //13
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        isCourseDoable = new boolean[numCourses];
        courseToPreRequirements = new HashMap<>();

        for (int[] cel : prerequisites) {
            ArrayList<Integer> newPrerequisite = new ArrayList<>();
            newPrerequisite.add(cel[1]);
            courseToPreRequirements.merge(cel[0], newPrerequisite, (old, _) -> {
                old.add(cel[1]);
                return old;
            });
        }

        for (int i = 0; i < numCourses; i++) {
            Set<Integer> visited = new HashSet<>();
            visited.add(i);
            if (!checkIfCourseDoable(i, visited)) {
                return false;
            }
            isCourseDoable[i] = true;
        }
        return true;

    }

    private boolean checkIfCourseDoable(int curseNumber, Set<Integer> visited) {
        if (isCourseDoable[curseNumber]) {
            return true;
        }
        List<Integer> integers = courseToPreRequirements.get(curseNumber);
        if (integers == null || integers.isEmpty()) {
            isCourseDoable[curseNumber] = true;
            return true;
        }
        for (Integer i : integers) {
            if (visited.contains(i)) {
                return false;
            }
            if (isCourseDoable[i]) {
                continue;
            }
            visited.add(i);
            if (!checkIfCourseDoable(i, visited)) {
                return false;
            }
            visited.remove(i);
        }
        return true;
    }

    //_________________
    //каноническое решение для поиска в глубину циклов в графе
    int[] state; // 0,1,2
    Map<Integer, List<Integer>> graph;

    public boolean canFinishCanon(int numCourses, int[][] prerequisites) {
        graph = new HashMap<>();
        state = new int[numCourses];

        for (int[] p : prerequisites) {
            graph.computeIfAbsent(p[0], _ -> new ArrayList<>()).add(p[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int course) {
        if (state[course] == 1) {
            return false; // цикл
        }
        if (state[course] == 2) {
            return true;  // уже проверен
        }

        state[course] = 1;

        for (int pre : graph.getOrDefault(course, List.of())) {
            if (!dfs(pre)) {
                return false;
            }
        }

        state[course] = 2;
        return true;
    }

    //второе каноническое решение Kahn’s Algorithm (BFS + indegree)
    //удаляем вершины начальные у которых вход равен 0.
    //удаляя их уменьшаем входы отесавшихся вершин если уделенные были с ними связаны.
    //и так пока все не удалим или не останется вершин с входом 0.
    public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]); // важно: направление!
            indegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processed = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            processed++;

            for (int next : graph.get(course)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return processed == numCourses;
    }
}
