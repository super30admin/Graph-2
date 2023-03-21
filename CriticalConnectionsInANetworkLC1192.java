class Solution {

    //Time Complexity: O(V+E)
    //Space Complexity: O(V+E)

    List<List<Integer>> result;
    List<List<Integer>> graph;
    int[] discovery;
    int[] lowest;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        result = new ArrayList<>();                         //to store result

        if(connections == null || connections.size() == 0) return result;       //check for null case

        graph = new ArrayList<>();                          //to store graph, here n servers numbered from 0 to n-1, that's why we can use list as a edjecency list otherwise we have to use map

        discovery = new int[n];                             //create an array to store at which time node is discovered
        Arrays.fill(discovery, -1);                         //initialize the discovery array with -1

        lowest = new int[n];                                //create an array which stores the node which connected to the lowest time timestamp
        time = 0;                                           //to keep track of time which helps track when we discover a node

        buildGraph(connections, n);                         //create a graph
        dfs(0, 0);                                          //call dfs at 0,0
        return result;                                      //return result
    }

    private void dfs(int currentNode, int parentNode){

        if(discovery[currentNode] != -1) return;            //check if the currentNode is discovered or not, if so, then return

        discovery[currentNode] = time;                      //update the time in the discovery array
        lowest[currentNode] = time;                         //update the time in the lowest array
        time++;


        //here we are iterating to the currentNode edges, and see if that edgeNode is connected to any previously discovered node or not, if it is connected then we just update the lowest value of the currentNode

        for(int newNode: graph.get(currentNode)){           //iterate to all the node that are connected to the currentNode

            if(newNode == parentNode) continue;             //check if the newNode is parentNode or not, if so then continue

            dfs(newNode, currentNode);                      //call the dfs on newNode as currentNode and currentNode as parentNode

            if(lowest[newNode] > discovery[currentNode]){                   // check if newNode's lowest array value is greater than the currentNode's discovery array value,
                result.add(Arrays.asList(newNode, currentNode));            //if so, then that is a critical connection, so add it to the result
            }
            lowest[currentNode] = Math.min(lowest[newNode], lowest[currentNode]);       //take the minimum between newNode's and currentNode's lowest array values and assign it to the currentNode's lowest array value, which helps us to identify that currentNode is connected to which lowest value node and helps us to detect the cycle
        }
    }


    private void buildGraph(List<List<Integer>> connections, int n){

        for(int i=0; i<n; i++){                             //iterate through n, and add all the nodes in graph list
            graph.add(new ArrayList<>());
        }

        for(List<Integer> edge: connections){               //iterate through connections

            graph.get(edge.get(0)).add(edge.get(1));        //get each edge and add it to the graph
            graph.get(edge.get(1)).add(edge.get(0));
        }
    }

}



public class CriticalConnectionsInANetworkLC1192 {
}
