"""
Implemented using the Tarjan's Algorithm
Time complexity: O(N+E)
Space complexity: O(N+E)
"""
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        adj = defaultdict(list)

        for u, v in connections:
            adj[u].append(v)
            adj[v].append(u)

        tin = [-1] * n
        low = [-1] * n
        visited = [False] * n
        timer = [0]
        bridges = []

        def dfs(node: int, parent: int):
            visited[node] = True
            tin[node] = low[node] = timer[0]
            timer[0] += 1

            for neighbor in adj[node]:
                if neighbor == parent:
                    continue
                if not visited[neighbor]:
                    dfs(neighbor, node)
                    low[node] = min(low[node], low[neighbor])
                    if low[neighbor] > tin[node]:
                        bridges.append([node, neighbor])
                else:
                    low[node] = min(low[node], tin[neighbor])

        for i in range(n):
            if not visited[i]:
                dfs(i, -1)

        return bridges