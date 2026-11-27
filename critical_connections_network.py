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
lowest[v]: Min of discovery times of all adjacent nodes of v excluding the discovery time of parent of v.
E.g. Let u = parent, v = child, v1, v2 = child's children
 u --- v ----  v1
       |       |
       |       v3
       |       |
       |       |
       |-------v2

lowest[v] = min(dt[v], dt[v1], dt[v2])

Note: If the nodes v1, v2, v3 have the same lowest value, it would mean that these nodes are part of a cycle. And hence, the subgraph containing v, v1,...,v3 is not an SCC and will not have a critical edge.

Condition of critical edge (aka bridge):
if lowest[v] > dt[u]:
    u--v is a bridge
https://youtu.be/6h1SucBNxgc?t=216 (concept illustrated during 3:30 - 9:30)


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
    def buildGraph(edges, graph):
        for edge in edges:
            u, v = edge
            graph[u].append(v)
            graph[v].append(u)

    def dfs_tarjan(v, u):
        # v = child, u = parent
        nonlocal time
        if discovery[v] != -1: # discovery[node] == -1: unvisited, else visited
            return

        discovery[v]= time
        lowest[v] = time
        time += 1

        for nbr in graph[v]:
            if nbr == u:
                continue

            dfs_tarjan(nbr, v)

            # nbr--v is a critical connection
            if lowest[nbr] > discovery[v]:
                criticals.append([nbr, v])

            # Update the lowest time of v
            # if parent's low < child's low: nothing to be updated
            # (this is natural for nodes in linear paths)
            # parent's low > child's low: update parent's low to
            # child's low
            # (this happens when parent and child are in a cyclic network
            #  which child can be reached from parent w/o the direct
            # edge connecting parent and child)
            lowest[v] = min(lowest[v], lowest[nbr])


    if n == 0:
        return []
    discovery = [-1]*n # Space: O(V)
    lowest = [-1]*n # Space: O(V)
    criticals = []
    graph = defaultdict(list) # adjacency list, Space: O(E)
    time = 0
    buildGraph(connections, graph) # O(E)
    dfs_tarjan(0, -1) # start node = 0, parent node = -1
    return criticals

def run_tests():
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
        passed = (sorted(ans) == sorted([sorted(sublist) for sublist in criticals]))
        assert passed == True, f"Test case failed"
        print(f"Pass: {passed}")
    print(f"\nDONE")

run_tests()