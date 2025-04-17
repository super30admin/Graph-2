# Time O(V+E)
# Space O(2V + V+E)
class Solution:
    def __init__(self):
        self.result = []
        self.time = 0

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        # so called forward looking dfs
        adjMap = defaultdict(list)# adjacency map
        for u, v in connections:
            adjMap[u].append(v)
            adjMap[v].append(u)
        lowest = [-1] * n
        discovered = [-1] * n
        #call forward looking dfs
        for v in range(n):
            if discovered[v] == -1:
                # node, parent, adjMap
                self.dfs(v, -1, adjMap, lowest, discovered)
        return self.result

    def dfs(self, v: int, parent: int, adjMap: dict, lowest: List[int], discovered: List[int]) -> None:
        # base
        if discovered[v] != -1: return
        discovered[v] = self.time
        lowest[v] = self.time
        self.time += 1
        for neighbor in adjMap[v]:
            # neighbor same as parent then pass
            if neighbor == parent: continue
            self.dfs(neighbor, v, adjMap, lowest, discovered)
            if lowest[neighbor] > discovered[v]:
                self.result.append([v, neighbor])
            lowest[v] = min(lowest[v], lowest[neighbor])
