class Solution:
    graph = {}
    result = []
    discovery = []
    lowest = []
    time = 0

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.graph = {}
        for i in range(n):
            self.graph[i] = []
        self.result = []
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.time = 0
        self.buildGraph(connections)
        self.dfs(0, 0)
        return self.result

    def buildGraph(self, connections):
        for edge in connections:
            n1 = edge[0]
            n2 = edge[1]
            self.graph.get(n1).append(n2)
            self.graph.get(n2).append(n1)


    def dfs(self, v, u):
        if self.discovery[v] != -1:
            return

        self.discovery[v] = self.time
        self.lowest[v] = self.time
        self.time += 1

        for n in self.graph[v]:
            if u == n:
                continue

            self.dfs(n, v)
            if self.lowest[n] > self.discovery[v]:
                self.result.append([n, v])
            self.lowest[v] = min(self.lowest[n], self.lowest[v])

# Graph, DFS
# Time Complexity : O(V+E)
# Space Complexity : O(V+E)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No