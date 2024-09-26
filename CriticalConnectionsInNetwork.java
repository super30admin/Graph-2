// Time Complexity: O(V+E)
// Space Complexity: O(V+E)

class Solution {

	int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new LinkedList<>();

        // base case
        if(connections == null || connections.size() == 0 || n == 0) {
        	return null;
        }

        // init graph
        List<Integer>[] adjancencyList = createGraph(n, connections, false);

        // book-keeping
        boolean[] visited = new boolean[n];
        int[] discoveryTime = new int[n];
        int[] lowTime = new int[n];
        int[] parent = new int[n];
        int time = 0;

        // call dfsVisit for unvisited nodes
        for(int u = 0; u < n; u++) {
        	if(!visited[u]) {
        		dfsVisit(adjancencyList, u, visited, discoveryTime, lowTime, parent, result);
        	}
        }
        return result;
    }

    public List<Integer>[] createGraph(int n, List<List<Integer>> edges, boolean printGraph) {
    	List<Integer>[] adjancencyList = new LinkedList[n];
    	// create graph with just the vertices
    	for(int i = 0; i < n; i++) {
    		adjancencyList[i] = new LinkedList<>();
    	}
    	// add edges
    	for(List<Integer> edge : edges) {
    		int u = edge.get(0);
    		int v = edge.get(1);
    		adjancencyList[u].add(v);
    		adjancencyList[v].add(u);
    	}

    	// print the graph
    	if(printGraph) {
    		for(int i = 0; i < n; i++) {
    			System.out.println(i + ": " + adjancencyList[i]);
    		}
    	}
    	return adjancencyList;
    }

    public void dfsVisit(List<Integer>[] adjancencyList, int u, boolean[] visited, 
    	int[] discoveryTime, int[] lowTime, int[] parent, List<List<Integer>> result) {
    	// mark visited
    	visited[u] = true;
    	// update time
    	time++;
    	discoveryTime[u] = time;
    	lowTime[u] = time;
    	// iterate on neighbors
    	for(int v : adjancencyList[u]) {
    		if(!visited[v]) {
    			parent[v] = u;
    			dfsVisit(adjancencyList, v, visited, discoveryTime, lowTime, parent, result);
    			lowTime[u] = Math.min(lowTime[u], lowTime[v]);
    			if(lowTime[v] > discoveryTime[u]) { // bridge detected
    				List<Integer> edge = new LinkedList<>();
    				edge.add(u);
    				edge.add(v);
    				result.add(edge);
    			}
    		} else if(v != parent[u]) {
    			lowTime[u] = Math.min(lowTime[u], discoveryTime[v]);
    		}
    	}
    }
}