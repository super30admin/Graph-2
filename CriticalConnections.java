
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//TC - O(V+E)
//SC - O(n)
//https://leetcode.com/problems/critical-connections-in-a-network/
class Solution {
    int[] discovery;
    int[] low;
    int time;
    HashMap<Integer,List<Integer>> map;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery = new int[n];
        Arrays.fill(discovery, -1);
        this.low = new int[n];
        this.time =0;
        this.map = new HashMap();
        this.result = new ArrayList<>();
        buildgraph(connections);
        dfs(0,0);   //starting with node -0 and parent -0
        return result;
    }
    //we're building undirected graph using connections list given to us. 
    //Eg:(0,1) we are adding 2 entries to map 0:{1}  1:{0}
    public void buildgraph(List<List<Integer>> connections) {
        for(List<Integer> node: connections){
            int node1 = node.get(0);
            int node2 = node.get(1);
            //option 1
            if(!map.containsKey(node1)){
                map.put(node1, new ArrayList<>());
            }
            map.get(node1).add(node2);
            //follow above or below method
            //List<Integer> list = map.getOrDefault(node2, new ArrayList<>());
            //list.add(node1);
            if(!map.containsKey(node2)){
                map.put(node2, new ArrayList<>());
            }
            map.get(node2).add(node1);
        }
    }

    public void dfs(int child, int parent) {
        //base
        if(discovery[child]!=-1)
            return;
        //logic
        discovery[child] = time;
        low[child] = time;
        time++;

        for(int curr: map.get(child)){  //child 2 --curr 0
            if(curr == parent)  //check notes, don't go to its parent again
                continue;
            dfs(curr,child);

            if(low[curr]>discovery[child]){
                result.add(Arrays.asList(curr,child));
            }
                low[child] = Math.min(low[child],low[curr]);
        }
    }
}
public class CriticalConnections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
