'''
Accepted on leetcode
time - O(N*N)
space -O(N)
'''


class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.discovery = [-1 for i in range(n)]
        self.lowest = [0 for i in range(n)]
        self.time = 0
        self.result = []
        self.graph = []

        # building a  graph
        self.buildGraph(connections, n)

        # performing dfs
        self.dfs(0, 0)

        
        return self.result

    def buildGraph(self, connections, n):
        for i in range(n):
            self.graph.append([])
        # creating an array with all the connections as a list at every node position in array
        for node in connections:
            fromNode = node[0]
            toNode = node[1]

            self.graph[fromNode].append(toNode)
            self.graph[toNode].append(fromNode)

    def dfs(self, u, prev):
        # initialize discovery and lowest same as time
        self.discovery[u] = self.time
        self.lowest[u] = self.time
        self.time += 1
        # iterate through the children of a particular node in the graph
        for v in self.graph[u]:
            # case 1: if node is parent, then continue
            if v == prev:
                continue
            # case 2: if neighbouring node is not discovered 
            if self.discovery[v] == -1:
                self.dfs(v, u)
                # if the connection is a critical connection then add to result.
                if self.lowest[v] > self.discovery[u]:
                    self.result.append([u, v])
            # case 3: if the neighbouring node  is discovered and not the parent and there is a cycle then
            # update lowest accordingly.
            self.lowest[u] = min(self.lowest[u], self.lowest[v])
