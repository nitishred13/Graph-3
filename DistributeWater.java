import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
//Time Complexity:O(ElogV)
//Space Complexiyty:O(E+V)
//We connect each house to a virtual node 0 using the well cost as an edge.
//Use a priority queue (min-heap) to always pick the cheapest edge connecting a new house.
//Keep adding edges until all nodes are visited, and sum their costs for the answer.
public class DistributeWater {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes)
    {
        List<int[]> edges = new ArrayList<>();

        for(int[] pipe: pipes)
        {
            edges.add(pipe);
        }

        for(int i=1;i<=n;i++)
        {
            edges.add(new int[]{0,i,wells[i-1]});
        }
        int result = 0;

        HashMap<Integer,List<int[]>> map = new HashMap<>();
        for(int[] edge : edges)
        {
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.putIfAbsent(edge[1], new ArrayList<>());
            map.get(edge[0]).add(new int[]{edge[1],edge[2]});
            map.get(edge[1]).add(new int[]{edge[0],edge[2]});   
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.add(new int[]{0,0});

        boolean[] visited = new boolean[n+1];

        while(!pq.isEmpty())
        {
            int[] curr = pq.poll();

            int node = curr[0];
            int cost = curr[1];

            if(visited[node]) continue;
            visited[node] = true;

            result += cost;
            for(int[] ne: map.get(node))
            {
                if(visited[ne[0]]) continue;
                pq.add(ne);
            }
        }
        return result;
    }
}
