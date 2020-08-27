------------------------Critical Connection (Tarjans algorithm)------------------------------------------
# Time Complexity : O(V+E) as V is number of vertices and E number of edges 
# Space Complexity : O()  No extra space
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# 
# We I will create a graph of nodes, and create a discovery and lower arrays. I will iterate through graph using dfs and 
# update the discover and lower variables in the moment of time. Once I reach the end of the graph, I will check if the lower
# of node is greater than the discovery of parent node, If yes that is the critical edge and update my lower of current node with 
# min of lower of parent and child. If there is a circle then the lower variable of the circle nodes is same.

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        graph = collections.defaultdict(list)
        for i in connections:
            graph[i[0]].append(i[1])
            graph[i[1]].append(i[0])
        
        def dfs(node, parent):
            # base case
            
            if discovery[node] != -1:
                return 
            
            discovery[node] = self.time
            lower[node] = self.time
            self.time+=1
            
            for i in graph[node]:
                if i == parent:
                    continue
                dfs(i, node)
                if lower[i] > discovery[node]:
                    res.append([i,node])
                
                lower[node] = min(lower[node], lower[i])
        
        discovery = [-1 for i in range(n)]
        lower = [0 for i in range(n)]
        self.time = 0
        res = []
        dfs(0, 0)
        return res