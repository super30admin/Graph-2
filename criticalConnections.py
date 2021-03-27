class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        # Time Complexity: O(VE)
        # Space Complexity: O(V+E)
        def dfs(ad_list, visited, ignore, i):
            for j in ad_list[i]:
                if(not j in visited and (not (i in ignore and j in ignore))):
                    visited.add(j)
                    dfs(ad_list, visited, ignore, j)
        ad_list = {i:[] for i in range(0,n)}
        for i in range(0,len(connections)):
            ad_list[connections[i][0]].append(connections[i][1])
            ad_list[connections[i][1]].append(connections[i][0])
        o = []
        for i in range(0, len(connections)):
            ignore = connections[i]
            visited = set()
            visited.add(0)
            dfs(ad_list, visited, ignore, 0)
            if(len(visited)<n):
                o.append(connections[i])
        return o
