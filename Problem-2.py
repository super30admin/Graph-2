# Time Complexity : O(N^2)  # We traverse adjacency matrix and perform DFS/Union-Find
# Space Complexity : O(N)   # Visited array and component tracking
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# We find connected components of the graph using DFS.
# For each component, count how many initially infected nodes exist.
# Removing the infected node that uniquely infects the largest component minimizes spread.

class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        n = len(graph)
        visited = [False]*n
        comp = [-1]*n
        size = []
        c = 0

        def dfs(u):
            stack = [u]
            nodes = []
            visited[u] = True

            while stack:
                x = stack.pop()
                nodes.append(x)
                for v in range(n):
                    if graph[x][v] == 1 and not visited[v]:
                        visited[v] = True
                        stack.append(v)

            return nodes

        for i in range(n):
            if not visited[i]:
                nodes = dfs(i)
                for x in nodes:
                    comp[x] = c
                size.append(len(nodes))
                c += 1

        infected = [0]*c
        for x in initial:
            infected[comp[x]] += 1

        initial.sort()
        ans = initial[0]
        max_saved = -1

        for x in initial:
            cid = comp[x]
            if infected[cid] == 1 and size[cid] > max_saved:
                max_saved = size[cid]
                ans = x

        return ans