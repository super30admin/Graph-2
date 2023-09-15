# Time Complexity : O(v+e)
# Space Complexity :O(v+e)
# Passed on Leetcode: yes

from collections import defaultdict

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        # Initialize the adjacency list to represent the graph
        graph = defaultdict(list)
        for u, v in connections:
            graph[u].append(v)
            graph[v].append(u)
        
        # Initialize variables for Tarjan's algorithm
        disc = [-1] * n  # Discovery time
        low = [-1] * n   # Low-link value
        parent = [-1] * n
        result = []
        time = 0

        def tarjan(u):
            nonlocal time
            disc[u] = time
            low[u] = time
            time += 1

            for v in graph[u]:
                if disc[v] == -1:  # If v is not visited
                    parent[v] = u
                    tarjan(v)
                    low[u] = min(low[u], low[v])

                    if low[v] > disc[u]:
                        # This is a critical connection
                        result.append([u, v])
                elif parent[u] != v:
                    low[u] = min(low[u], disc[v])

        # Start Tarjan's algorithm from each unvisited node
        for i in range(n):
            if disc[i] == -1:
                tarjan(i)

        return result
