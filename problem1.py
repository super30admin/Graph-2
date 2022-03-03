# TC:O(E+V)
# SC:O(n)/O(v)

class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        if graph is None or len(graph) == 0:
            return 0

        n = len(graph)

        self.colors = [-1] * n
        cl = 0
        for i in range(0, n):
            if self.colors[i] == -1:
                self.dfs(i, graph, cl)
                cl += 1

        group = [0] * cl

        for i in range(0, n):
            color = self.colors[i]
            group[color] += 1

        infected = [0] * cl

        for infect in initial:
            color = self.colors[infect]
            infected[color] += 1

        result = sys.maxsize
        for infect in initial:
            color = self.colors[infect]

            if infected[color] == 1:
                if result == sys.maxsize:
                    result = infect
                elif group[self.colors[result]] < group[color]:
                    result = infect
                elif group[self.colors[result]] == group[color] and infect < result:
                    result = infect

        _min = sys.maxsize

        if result == sys.maxsize:
            for infect in initial:
                _min = min(_min, infect)

            result = _min

        return result

    def dfs(self, i, graph, cl):
        # base case
        if self.colors[i] != -1:
            return
        # logic
        self.colors[i] = cl

        for j in range(0, len(graph[i])):
            if graph[i][j] == 1:
                self.dfs(j, graph, cl)
