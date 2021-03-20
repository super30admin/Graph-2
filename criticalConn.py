class Solution:
    
    """
    Description: 
    
    Time Complexity: O(V + E): for graph traversal
    Space Complexity: O(V + E): to build the graph (connections from each index)
    
    Approach: using Tarjan's Algorithm
    1. start from any node, and use node_id and low (assign same values at first as the nodes are being found)
       - initialize node_ids with -1 (not visited), and low with 0
    2. assign a paramter, time (to use in Tarjan's dfs); increment it recursively using dfs
    3. build the graph such that each index shows connections it has to the nodes
    4. start dfs from 0th node
       - find strongly connected nodes until we visit every node
       - use the graph created and call each neighbor for every index
       - update the low once traverse back if there are no more neighboring nodes
       - if the low value on next node is greater than the parent -> add that to resulting array
    5. return the resulting array
    """
    
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
         
        self.result = []
        self.node_id = [-1] * n
        self.low = [0] * n
        self.time = 0
        
        # find nodes connected to each index
        self.buildGraph(n, connections)
        
        # dfs (based on Tarjan's algorithm)
        self.dfs(0, 0)
        
        return self.result
                
    def buildGraph(self, n, connections):
        self.graph = [[] for i in range(n)]
        for node in connections:
            self.graph[node[0]].append(node[1])
            self.graph[node[1]].append(node[0])
            
    def dfs(self, u, v):
        
        # base
        if self.node_id[u] != -1: return
        
        # logic
        self.node_id[u] = self.time
        self.low[u] = self.time
        self.time += 1

        for node in self.graph[u]:
            
            if node == v: continue
                
            self.dfs(node, u)

            # condition to accept the bridge in the given graph
            if self.low[node] > self.node_id[u]:
                self.result.append([node, u])

            # update the low index for the node (traverse back)
            self.low[u] = min(self.low[node], self.low[u])
