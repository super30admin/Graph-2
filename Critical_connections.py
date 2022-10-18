# Time complexity : O(V+E)
# Space complexity : O(V+E)
# Leetcode : Solved and submitted

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        # default list for graph, res, discovery, lowest and time
        self.graph = []
        self.res = []
        self.discovery = [-1]*n
        self.lowest = [0]*n
        self.time = 0
        
        # call out the buildgraph function to build based on the connections given in the array
        self.buildGraph(connections, n)
        
        # call the dfs function to find the critical path
        self.dfs(0, 0)
        
        return self.res
    
    def buildGraph(self, conn, n):
        # for each node, add an empty list in the graph
        for i in range(n):
            self.graph.append([])
        
        # for every connection(edge), we'll have to add two entries as this is an undirected graph
        for edge in conn:
            n1 = edge[0]
            n2 = edge[1]
            
            # append the nodes in the each node
            self.graph[n1].append(n2)
            self.graph[n2].append(n1)
    
    # dfs is the Karjan's algorithm which is used to detect a cycle in a graph using a single traversal
    def dfs(self, v, u):
        # we stop when we find the discovery of the new_node is not -1, which means it already has been discovered or visited
        if self.discovery[v] != -1:
            return
        
        # update the discovery, lowest of the new node which is v
        self.discovery[v] = self.time
        self.lowest[v] = self.time
        
        # increment the time
        self.time += 1
        
        # go over the edges of each node
        for n in self.graph[v]:
            # if we have an edge to the parent node which is u, then continue
            if n == u:
                continue
            
            # recursively call the dfs on the child node of v and v which is now the parent
            self.dfs(n, v)
            
            # if we have the value of lowest of new node greater than the parent, which means there is a path
            # append the edge from n to v as the critical path
            if self.lowest[n] > self.discovery[v]:
                self.res.append([n,v])
            
            # update the lowest with the minimum of new and old node
            self.lowest[v] = min(self.lowest[v], self.lowest[n])
