import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        int[] count = new int[k];

        // Store available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Store busy servers: [finishTime, serverId]
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

            // Release servers that have completed their requests
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] serverInfo = busy.poll();
                available.add(serverInfo[1]);
            }

            // If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Find the next available server
            Integer server = available.ceiling(i % k);

            // Wrap around if no suitable server is found
            if (server == null) {
                server = available.first();
            }

            // Assign request to server
            available.remove(server);
            count[server]++;

            // Add server to busy queue
            busy.offer(new int[]{
                time + load[i], server
            });
        }

        // Find maximum number of requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect all busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}
