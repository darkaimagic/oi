class Dijkstra {
    int n;
    HashMap<Integer, HashMap<Integer, Integer>> g = new HashMap<>();
    int[] dis;
    //given a graph, return the minimum distance array from node 1 to all nodes
    public int[] run(int n, int[][] edges) {
        //node is from 1 to n
        this.n = n;
        for (int[] e : edges) {
            g.putIfAbsent(e[0], new HashMap<>());
            g.get(e[0]).put(e[1], e[2]);
            g.putIfAbsent(e[1], new HashMap<>());
            g.get(e[1]).put(e[0], e[2]);
        }
        dis = new int[n + 1];
        boolean[] flag = new boolean[n + 1];
        for (int i = 2; i <= n; i++)
            dis[i] = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i <= n; i++)
            pq.add(new int[]{dis[i], i});
        while (pq.size() > 0) {
            int[] e = pq.poll();
            if (dis[e[1]] < e[0])
                continue;
            flag[e[1]] = true;
            for (int x : g.get(e[1]).keySet()) {
                if (flag[x])
                    continue;
                int d = g.get(e[1]).get(x);
                if (dis[e[1]] + d < dis[x]) {
                    dis[x] = dis[e[1]] + d;
                    pq.add(new int[]{dis[x], x});
                }
            }
        }
        return dis;
    }
}
