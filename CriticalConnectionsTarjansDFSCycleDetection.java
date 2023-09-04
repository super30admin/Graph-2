import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class CriticalConnectionsTarjansDFSCycleDetection {

        // Tarjan's algorithm - Time O(V+E) and Space O(V+E)
        // Connections in a cycle are not critical

        // global
        int[] discovery;
        int[] lowestNeighDisc;
        int time;

        List<List<Integer>> result;
        HashMap<Integer, List<Integer>> adjacencyMap;

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

            // keep -1 as discovery time of nodes by default, before the DFS stars
            this.discovery = new int[n];
            Arrays.fill(discovery, -1);

            this.lowestNeighDisc = new int[n];

            this.result = new ArrayList<>();
            this.adjacencyMap = new HashMap<>();

            // establish adjacency
            buildAdjacency(n, connections);

            // DFS
            dfsTarjan(0, 0);

            // output
            return result;
        }

        public void buildAdjacency(int n, List<List<Integer>> connections) {  // O(V+E)

            // add servers with empty lists to adjacency map
            for(int i = 0; i < n; i++) {        // O(V) = O(n)

                adjacencyMap.put(i, new ArrayList<>());
            }

            // iterate over connections
            for(List<Integer> connect: connections) {    // O(E) = O(c)

                int oneEnd = connect.get(0);
                int otherEnd = connect.get(1);

                // add neighbors of each node from connections
                adjacencyMap.get(oneEnd).add(otherEnd);
                adjacencyMap.get(otherEnd).add(oneEnd);
            }
        }

        public void dfsTarjan(int node, int parent) {     // O(V+E)

            // base
            // keep dfs only forward
            if(discovery[node] != -1) {

                return;
            }

            // logic
            // action
            // whenever a node is visited, update its discovery and lowest neighbor discovery time
            discovery[node] = time;
            lowestNeighDisc[node] = time;

            // increment time after each action
            time++;

            // recursion
            // iterate over neighbors of node for dfs
            for(int neighbor: adjacencyMap.get(node)) {

                // avoids backward recursion
                if(neighbor == parent) {

                    continue;
                }

                // dfs recursion
                dfsTarjan(neighbor, node);

                // Tarjan's trick
                // only if cycle is not there, the lowest discovery time of neighbor will exceed discovery time of node calling dfs
                if(lowestNeighDisc[neighbor] > discovery[node]) {

                    result.add(Arrays.asList(neighbor, node));
                }

                // update the lowest discovery time of node based on its neighbors
                // if there is cycle, the lowest discovery time of node will take the value of the lowest in that cycle
                lowestNeighDisc[node] = Math.min(lowestNeighDisc[neighbor], lowestNeighDisc[node]);


            }
        }

        public static void main(String[] args) {

            CriticalConnectionsTarjansDFSCycleDetection obj = new CriticalConnectionsTarjansDFSCycleDetection();

            Scanner scanner = new Scanner(System.in);

            System.out.println("number of servers: ");
            int n = scanner.nextInt();

            System.out.println("number of connections: ");
            int e = scanner.nextInt();

            List<List<Integer>> connections = new ArrayList<>();

            for(int i = 0; i < e; i++) {

                List<Integer> connection = new ArrayList<>();
                System.out.println("connection " + (i+1));

                int endOne = scanner.nextInt();
                int endTwo = scanner.nextInt();

                connection.add(endOne);
                connection.add(endTwo);

                connections.add(connection);
            }

            List<List<Integer>> answer = obj.criticalConnections(n, connections);

            System.out.println("critical connections in the network: ");
            for(List<Integer> connection: answer) {

                System.out.print(connection.get(0) + " ");
                System.out.println(connection.get(1));

            }
        }

}


/*
Time Complexity = O(V+E) = O(n+c) = O(E)
Graph is connected = E >= V
Space Complexity = O(V+E) = O(n+c) = O(E)
n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
*/