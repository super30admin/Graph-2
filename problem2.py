# TC:O(E+V)
# SC:O(n)/O(v)

class Solution:
    def __init__(self):
        self.graph = []
        self.result = []
        self.time = 0

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        if connections is None or len(connections) == 0:
            return []

        self.discovery = [-1] * n
        self.lowest = [0] * n

        for i in range(0, n):
            self.graph.append([])

        self.buildGraph(connections)
        self.dfs(0, 0)
        return self.result

    def buildGraph(self, connections):
        for edge in connections:
            self.graph[edge[0]].append(edge[1])
            self.graph[edge[1]].append(edge[0])

    def dfs(self, v, u):
        # base case
        if self.discovery[v] != -1:
            return
        # logic
        self.discovery[v] = self.time
        self.lowest[v] = self.time
        self.time += 1

        for n in self.graph[v]:
            if n == u:
                continue

            self.dfs(n, v)

            if self.lowest[n] > self.discovery[v]:
                self.result.append([n, v])

            self.lowest[v] = min(self.lowest[v], self.lowest[n])



