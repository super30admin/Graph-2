// Time Complexity : O(V+E) v==n e-edges or connections
// Space Complexity : O(V+E) for map
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> result;
    HashMap<Integer, List<Integer>> map;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        discovery = new int[n];
        lowest = new int[n];
        time=0;
        result= new ArrayList<>();
        map = new HashMap<>();
        Arrays.fill(discovery,-1); // acts as visisted as well as to detect cycle
        fillMap(n,connections); // build the map
        dfs(0,0);
        return result;
    }
    //O(V+E)
    private void fillMap(int n , List<List<Integer>> connections){ 
        for(int i=0; i<n;i++){
            map.put(i,new ArrayList<>());
        }
        for(List<Integer> list : connections){   // for every edge or connection
            int first = list.get(0);     // two vertices or nodes
            int second = list.get(1);
            map.get(first).add(second);       // add them to both undirected graph
            map.get(second).add(first);
        }
    }

    private void dfs(int n, int v){ //n is curr node v is parent
        // base
        if(discovery[n]!=-1) return;
        // logic
        discovery[n]=time;   // maintain the order of the discovery
        lowest[n] = time;   // lowest or earliest reachable node from that node
        time++;
        for(int neighbor : map.get(n)){ // get list of neighbors for n 
            if(neighbor == v ) continue;//check the list element is same as the parent to avoid loop
            dfs(neighbor,n);  // for every neighbor of n call dfs 
            if(lowest[neighbor]>discovery[n]){ // critical condition
                result.add(Arrays.asList(neighbor, n));
            }
            lowest[n]= Math.min(lowest[n], lowest[neighbor]); // this change happen only when cycle is there

        }

    }
}