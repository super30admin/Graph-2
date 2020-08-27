# the main idea of this solution is to identify a cycle. if the cycle is identified, it is no longer a critical edge.
# critical edges are identified by comparing the discover id of a node with the lower value of the neighboring node.
# Time complexity - O(V+E)
# Space complexity - O(V+E) (graph, recursive stack, result array, lower, discovery)
# Did this solution run on leetcode? - yes
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        result = set()
        
        # set the initial values of discovery to -1 (unvisited).
        discovery = [-1] * n
        lower = [0] * n
        
        self.depth = 0 # variable used to assign initial value to discovery and lower.
        # construct an adjacency list graph.
        self.constructAdjGraph(n, connections)
        
        # depth first search
        def dfs(v, u):
            # base case
            # check if the node has been visited.
            if discovery[v] != -1: return
            
            # logic
            # set the initial value of logic and discovery
            discovery[v] = self.depth
            lower[v] = self.depth
            self.depth += 1
            
            for nei in self.graph[v]:
                if nei == u: continue   # if neighbor is the parent, continue
                dfs(nei, v)
                
                if lower[nei] > discovery[v]: # critical edge
                    result.add((nei, v))
                
                # set the lower value of neighbor to minimum of neighbor and parent.
                lower[v] = min(lower[nei], lower[v])
                
        dfs(0, None)
        return result
                
                
    def constructAdjGraph(self, n, connections):
        self.graph = [[] for _ in range(n)]
        for node in connections:
            self.graph[node[0]].append(node[1])
            self.graph[node[1]].append(node[0])
        
        