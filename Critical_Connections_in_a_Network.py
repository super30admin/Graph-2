# Time complexity : O(V + E), where V is the number of nodes and E is the number of edges in the graph.
# Space Complexity : O(V), where V is the number of nodes
from typing import List

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        graph = [[] for _ in range(n)]
        for u, v in connections:
            graph[u].append(v)
            graph[v].append(u)

        disc = [-1] * n
        low = [-1] * n
        parent = [-1] * n
        result = []

        def dfs(node, time):
            disc[node] = time
            low[node] = time
            for neighbor in graph[node]:
                if disc[neighbor] == -1:
                    parent[neighbor] = node
                    dfs(neighbor, time + 1)
                    low[node] = min(low[node], low[neighbor])
                    if low[neighbor] > disc[node]:
                        result.append([node, neighbor])
                elif neighbor != parent[node]:
                    low[node] = min(low[node], disc[neighbor])

        dfs(0, 0)
        return result