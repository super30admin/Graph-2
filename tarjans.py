# Time: O(v+e)
# Space: O(v+e) 
# Did it run on Leetcode: yes

class Solution(object):
    timee=0

    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        def buildgraph(connections, n):
            hmap = {i: [] for i in range(n)}
            for edge in connections:
                n1 = edge[0]
                n2 = edge[1]
                hmap[n1].append(n2)
                hmap[n2].append(n1)
            return hmap

        def dfs(v, u):
            
            if disc[v] != -1:
                return
            disc[v] = Solution.timee
            lowest[v] = Solution.timee
            Solution.timee += 1
            for n in graph[v]:
                if n == u:
                    continue
                dfs(n, v)
                if lowest[n] > disc[v]:
                    res.append([v, n])
                lowest[v] = min(lowest[n], lowest[v])

        graph = buildgraph(connections, n)
        lowest = [float('inf')] * n
        timee = 0
        res = []
        disc = [-1] * n
        dfs(0, 0)
        return res
