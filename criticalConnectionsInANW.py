from collections import defaultdict
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        graph = defaultdict(list)
        for a, b in connections:
            graph[a].append(b)
            graph[b].append(a)

        res = []
        discovery = [-1 for i in range(n)]
        earliest_discovery = [n + 1 for i in range(n)]
        self.time = 0
        def dfs(node, par):
            if discovery[node] != -1:
                return 
            discovery[node] = self.time
            earliest_discovery[node] = self.time
            self.time += 1
            for nei in graph[node]:
                if nei == par:
                    continue
                dfs(nei, node)
                if discovery[node] < earliest_discovery[nei]: #denotes there was a cycle and node is part of cycle
                    res.append([node, nei])
                earliest_discovery[node] = min(earliest_discovery[nei], earliest_discovery[node])
                    
        dfs(0, -1)
        return res
