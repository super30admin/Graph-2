import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Time Complexity: O(V+E), V is the vertices and E is the number of edges
Space Complexity: O(V^2), V is the number of vertices
Run on Leetcode: yes
Any difficulties: No

Approach:
1. Using tarzan algorithm- discussed in the class
2. Assigning an increasing id to each node
3. Maintaining lowestId reachable form any node except the parent node itself
4. An edge is critical if the lowestId returned is greater than the current id
 */
public class CriticalConnectionInTheNetwork {
        public static int id;
        public static int[] lowestId;
        public static List<List<Integer>> adjList;
        public static List<List<Integer>> result;

        public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections){
            result = new ArrayList<>();
            adjList = getAdjList(n, connections);

            // Let's take initial id as 0, and in a loop we will increment the id
            id = 0;
            // Assign every node a lowestId so the size of the array should be n
            lowestId = new int[n];

            // Let's initialize the array with all -1s
            Arrays.fill(lowestId, -1);

            tarzanAlgo(0, -1);
            return result;
        }

        public static List<List<Integer>> getAdjList(int n, List<List<Integer>> connections){
                List<List<Integer>> adjList = new ArrayList<>();
                for(int i = 0; i<n; i++){
                    adjList.add(new ArrayList<>());
                }

                for(List<Integer> connection: connections){
                    adjList.get(connection.get(0)).add(connection.get(1));
                    adjList.get(connection.get(1)).add(connection.get(0));
                }

                return adjList;
        }

        public static int tarzanAlgo(int node, int parentNode){
            if(lowestId[node] == -1){
                int nodeId = id;
                lowestId[node] = nodeId;
                id++;

                for(int child: adjList.get(node)){
                    if(child != parentNode){
                        int lowestChildId = tarzanAlgo(child, node);

                        if(nodeId<lowestChildId){
                            result.add(Arrays.asList(node, child));
                        }else{
                            lowestId[node] = Math.min(lowestChildId, lowestId[node]);
                        }
                    }
                }
            }
            return lowestId[node];
        }

        public static void main(String[] args){

            List<List<Integer>> connections = new ArrayList<>();

            List<Integer> con0 = new ArrayList<>();
            con0.add(0); con0.add(1);
            connections.add(con0);
            List<Integer> con1 = new ArrayList<>();
            con1.add(1); con1.add(2);
            connections.add(con1);
            List<Integer> con2 = new ArrayList<>();
            con2.add(2); con2.add(0);
            connections.add(con2);
            List<Integer> con3 = new ArrayList<>();
            con3.add(1); con3.add(3);
            connections.add(con3);
            System.out.println("Critical Connections: "+ criticalConnections(4, connections));
        }
}
