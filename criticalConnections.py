#Time Complexity : O(v+e)
#Space Complexity : O(v) 
#Did this code successfully run on Leetcode : Yes

class Solution:
    def dfs(self, v, u, visited, lowest):
        if visited[v] != -1:
            return
        visited[v] = self.time
        lowest[v] = self.time
        self.time += 1
        neighbors = self.graph[v]
        for neighbor in neighbors:
            if neighbor == u:
                continue
            self.dfs(neighbor, v, visited, lowest)
            if lowest[neighbor] > visited[v]:
                self.result.append([neighbor, v])
            lowest[v] = min(lowest[v], lowest[neighbor])



    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.graph = [[] for _ in range(n)]
        self.time = 0
        for connection in connections:
            self.graph[connection[0]].append(connection[1])
            self.graph[connection[1]].append(connection[0])

        self.result = []
        visited = [-1 for _ in range(n)]
        lowest = [0 for _ in range(n)]

        self.dfs(0, 0, visited, lowest)
        return self.result
