# Time Complexity: O(V+E)
# Space COmplexity:O(E)
from collections import defaultdict


class Solution:
    graph = defaultdict(list)
    result = []
    discovery = []
    lowest = []
    time = 0

    def buildgraph(self, connections):
        for e in connections:
            self.graph[e[0]].append(e[1])
            self.graph[e[1]].append(e[0])

    def dfs(self, v, u):

        if self.discovery[v] != -1:
            return
        self.discovery[v] = self.time
        self.lowest[v] = self.time
        self.time += 1

        for n in self.graph[v]:

            if n == u:
                continue
            self.dfs(n, v)

            if self.lowest[n] > self.discovery[v]:
                self.result.append([n, v])
            self.lowest[v] = min(self.lowest[n], self.lowest[v])

    def criticalConnections(self, n, connections):
        self.graph = defaultdict(list)
        self.result = []
        self.discovery = [-1] * n
        self.lowest = [-1] * n
        self.time = 0

        self.buildgraph(connections)
        self.dfs(0, 0)
        return self.result

