// Problem1: Critical Connections in a Network (https://leetcode.com/problems/critical-connections-in-a-network/)

// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

let graph, discovery, time, result, lowest;
/**
 * @param {number} n
 * @param {number[][]} connections
 * @return {number[][]}
 */
var criticalConnections = function (n, connections) {
    if (n === null || connections === null || connections.length === 0)
        return connections;

    graph = new Array(n);
    discovery = new Array(n)
    discovery.fill(-1);
    lowest = new Array(n);
    time = 0;
    result = new Array();

    for (let i = 0; i < graph.length; i++) {
        graph[i] = new Array();
    }

    // Build graph
    buildGraph(connections);

    // dfs(child, parent)
    dfs(0, 0);

    return result;
};
var buildGraph = (connections) => {
    for (let i = 0; i < connections.length; i++) {
        let edge = connections[i];
        let from = edge[0];
        let to = edge[1];
        graph[from].push(to);
        graph[to].push(from);
    }
}
var dfs = (v, u) => {
    // Base
    if (discovery[v] != -1)
        return;
    // Logic
    discovery[v] = time;
    lowest[v] = time;
    time++;
    for (let i = 0; i < graph[v].length; i++) {
        let n = graph[v][i];
        if (n === u) continue;
        dfs(n, v);
        if (lowest[n] > discovery[v]) {
            result.push([n, v]);
        }
        lowest[v] = Math.min(lowest[v], lowest[n]);
    }

}
