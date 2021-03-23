class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        #Approach: Tarjan's Algorithm  //  Modified DFS
        #Time Complexity: O(V + E)
        #Space Complexity: O(V + E)  //  or just O(E) since E >= V
        #where, V and E are the number of vertices (n) and edges (len(connections)) in the graph, respectively
        
        self.time = 0
        self.graph = [[] for _ in range(n)]     #Adjacency List; but can also use a hashMap
        self.result = []
        
        discovery = [-1 for _ in range(n)]      #natural discovery order
        lowest = [inf for _ in range(n)]        #earliest disovery order that can be directly reached
        
        self.buildGraph(n, connections)
        self.dfs(0, 0, discovery, lowest)
        
        return self.result
    
    def buildGraph(self, n, connections):
        for edge in connections:
            self.graph[edge[0]].append(edge[1])
            self.graph[edge[1]].append(edge[0])
            
    def dfs(self, node, parent, discovery, lowest):
        #base
        if discovery[node] != -1:
            return
        
        #logic
        discovery[node] = self.time
        lowest[node] = self.time
        self.time += 1
        
        for neighbor in self.graph[node]:
            if neighbor == parent:
                continue
                
            self.dfs(neighbor, node, discovery, lowest)
            
            if lowest[neighbor] > discovery[node]:
                self.result.append([node, neighbor])
                
            lowest[node] = min(lowest[node], lowest[neighbor])