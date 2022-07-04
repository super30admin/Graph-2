
//Tarjan's algorithm
class Solution {

    // Time Complexity : 0(V+E) where v is the vertices in the connections list and e is the edges
// Space Complexity : 0(V+E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


    int [] discovery; //To store the time stamp of servers once it is discovered. Indexes act as the server
    int [] lowest;  //to store the lowest time stamp which is updated as the traversing progresses. Here also, indexes act as servers
    int time;   //gives out the time when a node is explored
    List<List<Integer>> result; //to return the final result
    List<List<Integer>> graph;  //to form the graph from the connections list
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if(n == 0){
            return new ArrayList<>();
        }

        result = new ArrayList<>();
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int [n];
        Arrays.fill(discovery, -1); //initially filling with -1 to know if I have traversed through the node or not
        for(int i =0; i < n; i++){
            graph.add(new ArrayList<>());   //initializing a new array list at ever server. This graph stores the edges from a particular vertice
        }

        buildGraph(connections);

        dfs(0,0);

        return result;
    }

    public void buildGraph(List<List<Integer>> connections){
        for(List<Integer> edge : connections){  //going over all the connections
            int from = edge.get(0); //1st value gives from where does a connection start
            int to = edge.get(1);   //2nd value gives to where a connection goes to
            graph.get(from).add(to);    //adding the connection to the graph. Getting the server and adding the connection to it
            graph.get(to).add(from);  //since the graph is undirected, so an edge from also goes to 'to'
        }
    }

    public void dfs(int v, int u){
        //base
        if(discovery[v] != -1){ //if a node is already discovered, I don't touch it again as it will go in an unending cycle then
            return;
        }

        //logic
        discovery[v] = time;    //initially setting the time when a node is traversed
        lowest[v] = time;   //also making the current time as lowest
        time++; //increasing the time once a node is discovered and processed

        for(Integer n: graph.get(v)){   //going through the connections of a particular server. Here I start my traversal from node 0
            if(n == u){ //if the node is it's parent as well, then I continue
                continue;
            }
            dfs(n,v);   //if not, I call the dfs on it's connection
            if(lowest[n] > discovery[v]){   //if time of lowest node is greater than the parent node, then I add it to my result
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]); //also update the lowest of the partent
        }
    }
}