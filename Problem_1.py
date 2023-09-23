# Time Complexity: O(n)
# Space Complexity: O(n)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        disc = ([-1] * n)
        low = ([-1] * n)
        criticalConnections = []
        graph = defaultdict(list)
        for (u, v) in connections:
            graph[u].append(v)
            graph[v].append(u)
    
        def dfs(vertex, parent = -1, time = 0):
            low[vertex] = disc[vertex] = time
            for neighbor in graph[vertex]:
                if (disc[neighbor] == -1):
                    dfs(neighbor, vertex, (time + 1))
                    if (low[neighbor] > disc[vertex]):
                        criticalConnections.append([vertex,neighbor])
                    low[vertex] = min(low[vertex], low[neighbor])
                elif (parent != neighbor):
                    low[vertex] = min(low[vertex], disc[neighbor]) 
            return
        dfs(connections[0][0])
        return criticalConnections