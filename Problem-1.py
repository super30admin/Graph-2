# Time Complexity : O(V + E)  # Each node and edge is visited once in DFS (Tarjan's Algorithm)
# Space Complexity : O(V + E) # Adjacency list + recursion stack
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# We use Tarjan’s algorithm to compute discovery time and lowest reachable time for each node.
# If low[neighbor] > disc[node], the edge between them is a bridge (critical connection).
# DFS traversal helps detect all such bridges in the graph.

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        graph = [[] for _ in range(n)]
        for u,v in connections:
            graph[u].append(v)
            graph[v].append(u)

        disc = [-1]*n
        low = [-1]*n
        time = 0
        res = []

        def dfs(u, parent):
            nonlocal time
            disc[u] = low[u] = time
            time += 1

            for v in graph[u]:
                if v == parent:
                    continue
                if disc[v] == -1:
                    dfs(v,u)
                    low[u] = min(low[u], low[v])
                    if low[v] > disc[u]:
                        res.append([u,v])
                else:
                    low[u] = min(low[u], disc[v])

        dfs(0,-1)
        return res