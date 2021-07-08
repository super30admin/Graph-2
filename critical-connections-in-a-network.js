// Time Complexity : O(V + E)
// Space Complexity : O(V + E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes, I'm still wrapping my head around this algorithm

/**
 * @param {number} n
 * @param {number[][]} connections
 * @return {number[][]}
 */
const criticalConnections = (n, connections) => {
    if (!n || connections == null || connections.length == 0) return [];

    const result = [];
    const createGraph = (n, edges, ) => {
        const adjacencyList = [];
        for (let i = 0; i < n; i++) {
            adjacencyList[i] = [];
        }
        for (const edge of edges) {
            const u = edge[0],
                  v = edge[1];
            adjacencyList[u].push(v);
            adjacencyList[v].push(u);
        }
        return adjacencyList;
    }
    const adjacencyList = createGraph(n, connections);
    const visited = new Array(n).fill(false),
          discoveryTime = new Array(n).fill(0),
          lowTime = new Array(n).fill(0),
          parent = new Array(n).fill(0);
    let time = 0;
    const dfs = (u) => {
        // Mark visited
        visited[u] = true;
        // Update time
        time++;
        discoveryTime[u] = time;
        lowTime[u] = time;
        // Iterate on neighbors
        for (const v of adjacencyList[u]) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v, visited, discoveryTime);
                lowTime[u] = Math.min(lowTime[u], lowTime[v]);
                if (lowTime[v] > discoveryTime[u]) result.push([u, v]);
            } else if (v != parent[u]) {
                lowTime[u] = Math.min(lowTime[u], discoveryTime[v]);
            }
        }
    }
    
    for (let u = 0; u < n; u++) {
        if (!visited[u]) dfs(u);
    }
    return result;
};
