'''
924 Minimize Malware Spread
https://leetcode.com/problems/minimize-malware-spread/description/

You are given a network of n nodes represented as an n x n adjacency matrix graph, where the ith node is directly connected to the jth node if graph[i][j] == 1.

Some nodes initial are initially infected by malware. Whenever two nodes are directly connected, and at least one of those two nodes is infected by malware, both nodes will be infected by malware. This spread of malware will continue until no more nodes can be infected in this manner.

Suppose M(initial) is the final number of nodes infected with malware in the entire network after the spread of malware stops. We will remove exactly one node from initial.

Return the node that, if removed, would minimize M(initial). If multiple nodes could be removed to minimize M(initial), return such a node with the smallest index.

Note that if a node was removed from the initial list of infected nodes, it might still be infected later due to the malware spread.

Example 1:
Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
Output: 0

Example 2:
Input: graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
Output: 0

Example 3:
Input: graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]
Output: 1

Solution
1. DFS
Step 1: Categorize each node into sub-graphs using DFS
Define a colors array where the index is a node id and the value at the
index is the sub-graph id. By default, we set sub-graph id = -1 for all
nodes (indicating no sub-graph id has been allocated). Using DFS, we traverse theough the connected components (nodes) and set the sub-graph IDs (using a variable 'color').

Step 2: Using the colors array, count the no. of nodes in each sub-graph

Step 3: Using the initial array (node ids of infected nodes) and the colors array, count the no. of infected nodes in each sub-graph

Step 4: Find the no. of nodes that can be saved in each sub-graph (SG)
saved nodes in SG = total nodes in SG (step 2)
                    - no. of infected nodes in SG (step 4)

https://youtu.be/c7PW52OPg2M?t=391 (till 28:46)
Time: O(N^2), Space: O(N), N = total no. of nodes

'''
from typing import List
def minMalwareSpread(graph: List[List[int]], initial: List[int]) -> int:
    def dfs(graph, i):
        if colors[i] != -1:
            return

        colors[i] = color
        for j in range(N):
            if i == j: continue
            if graph[i][j] == 1:
                # we can assign color to index j using the assignment statement
                # colors[j] = color only if the graph has a simple linear chain
                # type of struct 1->2->3
                #
                # For non-linear struc, assignment statement will not work
                # Use DFS
                # for non-linear structs, 1->2->3->4
                #                               |
                #                                -->5
                dfs(graph, j)
        return

    if not graph:
        return -1
    N = len(graph)

    # Step 1: Categorize each node into sub-graphs using DFS
    colors = [-1]*N
    color = 0
    for i in range(N): # O(N)
        if colors[i] == -1:
            dfs(graph, i) # O(N)
            color += 1

    # Step 2: Count the no. of nodes in each sub-graph id
    groups = [0]*color
    for i in range(N):
        groupID = colors[i] # groupID is the sub-graph id
        groups[groupID] += 1

    # Step 3: Count the no. of infected nodes in each sub-graph
    countInfected = [0]*color
    for i in range(len(initial)):
        nodeID = initial[i]
        groupID = colors[nodeID]
        countInfected[groupID] += 1

    # Step 4: Find the no. of nodes that can be saved in each sub-graph
    # saved nodes = total nodes - no. of infected nodes
    answer = -1
    for i in range(len(initial)):
        nodeID = initial[i] # nodeID is the infected node id
        groupID = colors[nodeID] # groupID of the infected node
        if countInfected[groupID] == 1:
            currGroupSize = groups[groupID]
            if answer == -1:
                 # first time answer is assigned
                answer = nodeID
                prevGroupSize = currGroupSize
            elif currGroupSize > prevGroupSize:
                # answer was already assigned but we came across another
                # infected node existing in a new sub-graph that has more
                # no. of nodes than the previous sub-graph
                answer = nodeID
                prevGroupSize = currGroupSize
            elif currGroupSize == prevGroupSize and answer > nodeID:
                answer = nodeID
                prevGroupSize = currGroupSize
        else: # no. of infected nodes > 1 -> cannot save any node in sub-graph
            pass

    # If every group has more than 1 infected node
    if answer == -1:
        m = float('inf')
        for i in range(len(initial)):
            m = min(m, initial[i])
        return m
    else:
        return answer


def run_minMalwareSpread():
    tests = [([[1,1,0],[1,1,0],[0,0,1]], [0,1], 0),
             ([[1,0,0],[0,1,0],[0,0,1]],[0,2],0),
             ([[1,1,1],[1,1,1],[1,1,1]],[1,2],1),
             ([[1,0,1,0],[0,1,0,0],[1,0,1,0],[0,0,0,1]],[1],1),
             ([[1,1,0,0,0],[1,1,1,1,0],[0,1,1,0,0],[0,1,0,1,1],[0,0,0,1,1]], [0,2,4],0),
             ([[1,0,0,0,1,0,0,0,0,0],[0,1,1,0,0,0,0,0,0,0],[0,1,1,0,0,1,0,0,0,0],[0,0,0,1,0,0,0,0,0,0],[1,0,0,0,1,0,0,0,0,0],[0,0,1,0,0,1,0,0,0,0],[0,0,0,0,0,0,1,0,0,1],[0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,0,1,0],[0,0,0,0,0,0,1,0,0,1]], [1,3,0], 1), # 10 nodes, 6 sub-graphs
    ]
    for test in tests:
        graph, initial, ans = test[0], test[1], test[2]
        nodeID = minMalwareSpread(graph, initial)
        print(f"\ngraph = {graph}")
        print(f"initial nodes infected = {initial}")
        print(f"nodeID to be removed = {nodeID}")
        success = (ans == nodeID)
        print(f"Pass: {success}")
        if not success:
            print(f"Failed")
            return


run_minMalwareSpread()
