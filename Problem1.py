class Solution:
    # Time Complexity : O(n^2) because in the dfs function for every node in the graph we are going to all its neighbours
    # Space Complexity : O(n) because we storing the entire graph as the adjacency lists
    # Accepted on leetcode
    timestamp = 0
    def buildgraph(self, n, connections):
        graph = []
        for i in range(n):
            graph.append([])
        for i in connections:
            from_ = i[0]
            to = i[1]
            graph[from_].append(to)
            graph[to].append(from_)
        return graph
    
    def dfs(self, src, parent, discovery, low, res, graph):
        discovery[src] = self.timestamp
        low[src] = self.timestamp
        self.timestamp += 1
        for nei in graph[src]:
            if nei == parent:
                continue
            elif discovery[nei] == -1:
                self.dfs(nei, src, discovery, low, res, graph)
                low[src] = min(low[src], low[nei])
                if low[nei] > discovery[src]:
                    res.append([src, nei])
            else:
                low[src] = min(low[src], low[nei])

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        res = []
        discovery = [-1 for i in range(n)]
        low = [0 for i in range(n)]
        graph = self.buildgraph(n, connections)
        self.dfs(0, 0, discovery, low, res, graph)
        return res