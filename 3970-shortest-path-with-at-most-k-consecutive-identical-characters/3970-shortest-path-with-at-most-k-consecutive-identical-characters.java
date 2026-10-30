import java.util.*;

class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Step 1: Create adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(
                new int[]{edge[1], edge[2]}
            );
        }

        // Step 2: Distance array
        // dist[node][count] = minimum distance
        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Step 3: Priority Queue
        // {distance, node, consecutive count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Starting node has count 1
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        // Step 4: Dijkstra's Algorithm
        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int cost = curr[0];
            int node = curr[1];
            int count = curr[2];

            // Ignore outdated states
            if (cost != dist[node][count]) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return cost;
            }

            // Explore neighbours
            for (int[] next : graph[node]) {

                int nextNode = next[0];
                int weight = next[1];

                int nextCount;

                if (labels.charAt(node) ==
                    labels.charAt(nextNode)) {

                    nextCount = count + 1;

                } else {

                    nextCount = 1;
                }

                // Check consecutive character limit
                if (nextCount > k) {
                    continue;
                }

                int newCost = cost + weight;

                // Relaxation
                if (newCost < dist[nextNode][nextCount]) {

                    dist[nextNode][nextCount] = newCost;

                    pq.offer(new int[]{
                        newCost,
                        nextNode,
                        nextCount
                    });
                }
            }
        }

        return -1;
    }
}
