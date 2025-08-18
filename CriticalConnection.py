# Time Complexity: O(E * (V+E))
# Space Complexity: O(V + E)
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.map = {i: [] for i in range(n)}
        self.time = 0
        self.result = []

        for u, v in connections:
            self.map[u].append(v)
            self.map[v].append(u)

        self.helper(0, -1)
        return self.result

    def helper(self, u, v):
        if self.discovery[u] != -1:
            return

        self.discovery[u] = self.time
        self.lowest[u] = self.time
        self.time += 1

        for ne in self.map[u]:
            if ne == v:
                continue

            self.helper(ne, u)

            if self.lowest[ne] > self.discovery[u]:
                self.result.append([ne, u])

            self.lowest[u] = min(self.lowest[u], self.lowest[ne])