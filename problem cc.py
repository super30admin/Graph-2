# https://leetcode.com/problems/critical-connections-in-a-network/description/

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
       # n = len(connections)
        discovery = [-1] *(n)
        lowest = [-1] * (n)
        result = []
        time = 0
        hm = {}
        for i in range(n):
            hm[i] = []
        
        def buildgraph(connections):
            for edge in connections:
                in_edge = edge[0]
                out_edge = edge[1]
                hm[in_edge].append(out_edge)
                hm[out_edge].append(in_edge)

        buildgraph(connections)

        def dfs(v,u):
            nonlocal time
            if discovery[v] != -1:
                return
            
            discovery[v] = time
            lowest[v] = time
            time += 1

            for ne in hm[v]:
                if ne == u:
                    continue
                dfs(ne,v)
                if lowest[ne] > discovery[v]:
                    result.append([ne,v])
                lowest[v] = min(lowest[v],lowest[ne])

        dfs(0,0)
        return result

# TC: O(V+E)
# SC: O(V+E)