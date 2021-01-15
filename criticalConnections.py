class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        output = []
        self.time = 0
        discovery = [-1]*n
        low = [0]*n
        allConnections = defaultdict(list)
        
        for a,b in connections:
            allConnections[a].append(b)
            allConnections[b].append(a)
        discoveryNumber = 0
        
        self.time = 0
        def dfs(node, parent):
            nonlocal discoveryNumber
            if discovery[node]!=-1:
                return 
            discovery[node] = self.time
            low[node] = self.time
            self.time +=1
            
            for neigh in allConnections[node]:
                if neigh == parent:
                    continue
                dfs(neigh, node)
                
                if discovery[node]<low[neigh]:
                    output.append([node,neigh])
                low[node] = min(low[node], low[neigh])


        dfs(0, None)

        return output
Time: O(N)
Space: O(N)
