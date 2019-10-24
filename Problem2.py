class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        # Time complexity : O(n^2) where n is the number of nodes in the graph
        # space complexity : O(n) + O(c) + O(c) where n is the number of nodes in the graph and c is the number of colors used
        # Accepted on leetcode
        color = [-1 for _ in range(len(graph))]
        c = 0
        for i in range(len(color)):
            if color[i] == -1:
                self.dfs(graph, i, c, color)
                c += 1
        groups = [0 for _ in range(c)]
        for i in range(len(color)):
            groups[color[i]] += 1
        numberNodesInitArr = [0 for _ in range(c)]
        for i in initial:
            numberNodesInitArr[color[i]] += 1
        res = float("inf")
        for node in initial:
            colorNode = color[node]
            if numberNodesInitArr[colorNode] == 1:
                if res == float("inf"):
                    res = node
                elif groups[colorNode] > groups[color[res]]:
                    res = node
                elif groups[colorNode] == groups[color[res]] and node < res:
                    res = node
        if res == float("inf"):
            res = min(initial)
        return res
        
    def dfs(self, graph, node, c, color):
        color[node] = c
        for nei in range(len(graph)):
            if graph[node][nei] == 1 and color[nei] == -1:
                self.dfs(graph, nei, c, color)