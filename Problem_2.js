// Problem2: Minimize Malware Spread (https://leetcode.com/problems/minimize-malware-spread/)

// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

let n;
/**
 * @param {number[][]} graph
 * @param {number[]} initial
 * @return {number}
 */
var minMalwareSpread = function (graph, initial) {

    let color = 0;
    n = graph.length;
    let colors = new Array(n);
    colors.fill(-1);
    // Identify each connected graph with a group color(0,1,2...)
    for (let i = 0; i < n; i++) {
        if (colors[i] === -1) {
            dfs(graph, colors, color, i)
            color++;
        }
    }

    // Find total nodes in each group color
    let groups = new Array(color);
    groups.fill(0);
    for (let i = 0; i < n; i++) {
        groups[colors[i]]++;
    }

    // Find infected nodes in each group color
    let infectedNodes = new Array(color);
    infectedNodes.fill(0);
    for (let i = 0; i < initial.length; i++) {
        infectedNodes[colors[initial[i]]]++;
    }
    let removedNode = Infinity;
    // Loop through each initally infected node and if number of infected nodes in that group is 1, it will contribute to answer
    for (let i = 0; i < initial.length; i++) {
        let badNode = initial[i];
        if (infectedNodes[colors[badNode]] === 1) {
            // If this is the 1st node with 1 infected node in the group, assign it as answer
            if (removedNode === Infinity) {
                removedNode = badNode;
            } else {
                // Check the total nodes in that group. If it is higher than the existing answer, update
                if (groups[colors[badNode]] > groups[colors[removedNode]]) {
                    removedNode = badNode;
                } else if (groups[colors[badNode]] === groups[colors[removedNode]] && badNode < removedNode) {
                    removedNode = badNode;
                }
            }
        }
    }
    // If none found, return smalled infectred node
    if (removedNode === Infinity) {
        for (let i = 0; i < initial.length; i++) {
            removedNode = Math.min(removedNode, initial[i]);
        }
    }
    // else return removedNode
    return removedNode
};
var dfs = (graph, colors, color, i) => {
    if (colors[i] != -1)
        return;

    colors[i] = color;
    // Logic
    for (let j = 0; j < colors.length; j++) {
        if (graph[i][j] === 1) {
            dfs(graph, colors, color, j);
        }
    }
}