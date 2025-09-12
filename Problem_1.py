'''
1192 Critical Connections in a Network
https://leetcode.com/problems/critical-connections-in-a-network/description/


There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]

Constraints:
2 <= n <= 10^5
n - 1 <= connections.length <= 10^5
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.

Solution:
1. Tarjan's Algorithm for strongly connected components (SCC)
SCC: A subgraph of a directed graph is considered to be an Strongly Connected Component (SCC) if and only if for every pair of vertices A and B in the subgraph, there exists a path from A to B and a path (alternate path) from B to A.

The task is to find non-SCCs in an undirected graph.
Define:
discovery[v]: Time during first visit of node v
lowest[v]: Min of discovery times of all adjacent nodes of v excluding its parent
Note: If the nodes v1, v2, v3 ..., vN have the same lowest value, it would mean that these nodes are part of a cycle. And hence, the subgraph containing v1,...,vN is not an SCC and will not have a critical edge.

For the rest of the algorithm, follow the dry runs in the links below.
https://youtu.be/ghAK4ONksDo?t=1180 to https://youtu.be/ghAK4ONksDo?t=2060 (eg dry run in lecture)
https://www.youtube.com/watch?v=qrAub5z8FeA
Time: O(V+E), Space: O(V+E)

DFS traversal visiting each node exactly once, takes O(V) time.
For each node, we explore all its adjacent edges. Since the graph is undirected, each edge is stored twice in the adjacency list, so we process 2E edge traversals in total, which is O(2E). Thus, total is O(V+2E) = O(V+E)
Note that time is not O(V*2E) since we traverse each node and edge only once. Traversing V nodes and E edges only once takes O(V+E) time.

'''
from typing import List
from collections import defaultdict

def criticalConnections(n: int, connections: List[List[int]]) -> List[List[int]]:
    '''
    Time: O(V+E), Space: O(V+E)
    '''
    def buildGraph(edges):
        for i in range(len(edges)):
            src = edges[i][0]
            dstn = edges[i][1]
            graph[src].append(dstn)
            graph[dstn].append(src)

    def dfs(v, u):
        ''' v = child, u = parent '''
        nonlocal time
        if visited[v]:
            return
        # Mark v as visited (to avoid loops)
        visited[v] = True

        # Update discovery and lowest for v
        discovery[v] = time
        lowest[v] = time
        time += 1

        # Get the children of v and explore (perform dfs)
        children = graph[v]
        for n in children: # O(E)
            if n == u: continue
            dfs(n, v)
            # critical path exists when the discovery time of parent
            # < low time of child. This means that the child has no way
            # to reach the parent if the edge between parent and child is
            # severed. This is because the fastest time the child can be reached
            # by any path is still greater than discovery time of parent. Hence,
            # there is no way to reach the child w/o going through the parent.
            if discovery[v] < lowest[n]:
                criticals.append([v,n])

            # if parent's low < child's low: nothing to be updated
            # (this is natural for nodes in linear paths)
            # parent's low > child's low: update parent's low to
            # child's low
            # (this happens when parent and child are in a cyclic network
            #  which child can be reached from parent w/o the direct
            # edge connecting parent and child)
            lowest[v] = min(lowest[n], lowest[v])

    if n == 0:
        return []
    discovery = [-1]*n # Space: O(V)
    lowest = [-1]*n # Space: O(V)
    visited = [False]*n # Space: O(V)
    criticals = []
    graph = defaultdict(list) # adjacency list, Space: O(E)
    time = 0
    buildGraph(connections) # O(E)
    dfs(0, -1) # start node = 0, parent node = -1
    return criticals

def run_criticalConnections():
    tests = [ ([[0,1],[1,2],[2,0],[0,3],[2,4]],5,[[0,3],[2,4]]),
              ([[0,1],[1,2],[2,0],[1,3]],4,[[1,3]]),
              ([[0,1]],2,[[0,1]]),
    ]
    #tests = [([[0,1],[1,2],[2,0],[0,3],[2,4]],5,[[0,3],[2,4]])]
    for test in tests:
        connections, n, ans = test[0], test[1], test[2]
        criticals = criticalConnections(n, connections)
        print(f"\nedges = {connections}")
        print(f"No. of nodes = {n}")
        print(f"Critical edges = {criticals}")
        success = (sorted(ans) == sorted(criticals))
        print(f"Pass: {success}")
        if not success:
            print(f"Failed")
            return

run_criticalConnections()