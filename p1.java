// Time Complexity :O(n) where n is the number of connectios
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    List<List<Integer>> result;
    HashMap<Integer, List<Integer>> map;
    int[] distance;
    int[] lowest;
    int time = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        map = new HashMap<>();
        distance = new int[n];
        //Make the distance array as -1 which will act as not visited
        for(int i=0; i< n; i++){
            distance[i] = -1;
        }
        lowest = new int[n];

        //Make the indegrees hashmap
        for(List<Integer> c: connections){
            List<Integer> temp = map.getOrDefault(c.get(0), new ArrayList<>());
            temp.add(c.get(1));
            map.put(c.get(0), temp);

            temp = map.getOrDefault(c.get(1), new ArrayList<>());
            temp.add(c.get(0));
            map.put(c.get(1), temp);
        }
        //Call recursive function
        dfs(0,0);
        return result;
    }

    private void dfs(int v, int u){
        //base
        if(distance[v] != -1){
            return;
        }
        distance[v] = time;
        lowest[v] = time;
        time++;
        List<Integer> temp = map.get(v);
        //Go through every connection for a node
        for(int i: temp){
            //If it's the parent, do nothing
            if(i == u){
                continue;
            } 
            dfs(i, v);
            //If we find a node which is not a part of any cycle, add it to the result
            if(lowest[i] > distance[v]){
                List<Integer> r = new ArrayList<>();
                r.add(v);
                r.add(i);
                result.add(r);
            }
            //If we detect cycle, mark the lowest
            if(lowest[i] < lowest[v]){
                lowest[v] = lowest[i];
            }
        }
        return;
    }
}