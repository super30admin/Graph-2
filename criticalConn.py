class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        #Tc: O(V+E) Sc: O(V)
        graph = [[] for _ in range(n)]
        for u, v in connections:
            graph[u].append(v)
            graph[v].append(u)

        disc = [-1] * n 
        low = [-1] * n  
        parent = [-1] * n
        bridges = []

        def dfs(u, time):
            disc[u] = time
            low[u] = time
            for v in graph[u]:
                if disc[v] == -1:  
                    parent[v] = u
                    dfs(v, time + 1)
                    low[u] = min(low[u], low[v])
                    if low[v] > disc[u]:
                        bridges.append([u, v])
                elif v != parent[u]:
                    low[u] = min(low[u], disc[v])

        for i in range(n):
            if disc[i] == -1:
                dfs(i, 0)

        return bridges
        