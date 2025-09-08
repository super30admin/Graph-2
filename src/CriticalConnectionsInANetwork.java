//time complexity- O(v+e)
//space complexity- O(v+e)
import java.util.*;
public class CriticalConnectionsInANetwork {
    static int[] discovery;
    static int[] lowest;
    static HashMap<Integer, List<Integer>> map;
    static int time;
    static List<List<Integer>> result;

    static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        discovery = new int[n];
        lowest = new int[n];
        map = new HashMap<>();
        time = 0;
        result = new ArrayList<>();
        Arrays.fill(discovery, -1);
        for(int i=0; i<n; i++){
            map.put(i, new ArrayList<>());
        }
        for(List<Integer> connection : connections){
            int u = connection.get(0);
            int v = connection.get(1);

            map.get(u).add(v);
            map.get(v).add(u);
        }
        connect(0, -1);
        return result;
    }

    static void connect(int u, int v){
        if(discovery[u] != -1) return;
        discovery[u] = time;
        lowest[u] = time;
        time++;
        for(int i : map.get(u)){
            if(i == v) continue;
            connect(i, u);
            if(lowest[i] > discovery[u]){
                result.add(Arrays.asList(i, u));
            }
            lowest[u] = Math.min(lowest[i], lowest[u]);
        }
    }
    public static void main(String[] args) {
        int  n = 4;
        List<List<Integer>>connections = new ArrayList<>();
        connections.add( Arrays.asList(0, 1));
        connections.add( Arrays.asList(1, 2));
        connections.add( Arrays.asList(2, 0));
        connections.add( Arrays.asList(1, 3));

        List<List<Integer>>  re = criticalConnections(n, connections);
        for (List<Integer> li : re) {
            System.out.println(li);
        }

    }
}
