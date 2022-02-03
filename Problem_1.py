class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.result = []
        if connections == None:
            return self.result
        self.graph = {}
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.time = 0
        for edge in connections:
            if edge[0] not in self.graph: 
                self.graph[edge[0]] = list()
            if edge[1] not in self.graph:
                self.graph[edge[1]] = list()
            self.graph[edge[0]].append(edge[1])
            self.graph[edge[1]].append(edge[0])
        self.dfs(0, 0)
        return self.result
        
    def dfs(self, v, u):
        if self.discovery[v] != -1:
            return
        self.discovery[v] = self.time
        self.lowest[v] = self.time
        self.time += 1
        for neigh in self.graph[v]:
            if neigh == u:
                continue
            self.dfs(neigh, v)
            if self.lowest[neigh] > self.discovery[v]:
                self.result.append([neigh, v])
            self.lowest[v] = min(self.lowest[v], self.lowest[neigh])

# Time Complexity: O(V+E)
# Space Complexity: O(V+E)