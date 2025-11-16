# Time complexity is O(V+E) and space is also O(V+E)

# The algorithm that we use here is Tarjan's algorithm for a forward looking DFS only. It basically tells us if there are cycles in the graph or not in just one pass.

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        adjList = defaultdict(list)

        discovery = [-1] * n
        lowest = [0] * n
        time = 0
        result = []
        for val1, val2 in connections:
            adjList[val1].append(val2)
            adjList[val2].append(val1)

        def dfs(curr, papa):
            nonlocal discovery, lowest, time, result
            if discovery[curr] != -1:
                return

            discovery[curr] = time
            lowest[curr] = time
            time += 1

            for nei in adjList[curr]:
                if nei == papa: continue
                dfs(nei, curr)
                if lowest[nei] > discovery[curr]:
                    result.append([nei, curr])
                lowest[curr] = min(lowest[curr], lowest[nei])

        dfs(0, -1)
        return result