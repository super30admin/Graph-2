class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        
        from collections import defaultdict
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        if not connections or n == 0:
            return []
        graph = defaultdict(list)
        for x, y in connections:
            graph[x].append(y)
            graph[y].append(x)
        
        print(graph)
            
        visited = [False]*n
        parent = [-1]*n
        disc = [-1]*n
        low = [0]*n
        time = 0
        res = []
        
        def dfs(node, time):
            visited[node] = True
            disc[node] = low[node] = time
            time += 1
            for neigh in graph[node]:
                if not visited[neigh]:
                    parent[neigh] = node
                    dfs(neigh, time)
                    low[node] = min(low[node], low[neigh])
                    if low[neigh] > disc[node]:
                        res.append([node, neigh])
                elif neigh != parent[node]:
                    low[node] = min(low[node], disc[neigh])
        
        for node in range(n):
            if not visited[node]:
                dfs(node, time)
        return res    
        
        #tc & sc o(v+e)
