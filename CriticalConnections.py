#All TC on leetcode passed

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:

        #Time complexity - O(V+E) - for DFS and creating graph 
        #Space complexity - O(V+E) for adjacency matrix
        self.res = []
        #adjacency list for given connections
        graph = [[] for i in range(n)]

        #lowest gives the earliest discovered node that is reachable from cur node 
        lowest = [0]*n

        #natural order of dfs i.e. the natural time each node is discovered by dfs
        discovery = [-1]*n

        time = 0
        
        self.buildGraph(graph, n, connections)
        self.dfs(graph, n, 0, 0, discovery, lowest, time)
        return self.res

    #building the graph adjacenecy list
    def buildGraph(self, graph, n, connections):
        for c in connections:
            graph[c[0]].append(c[1])
            graph[c[1]].append(c[0])
    
    def dfs(self, graph, n, parent, cur, discovery, lowest, time):

        if discovery[cur]!=-1:
            return
        
        discovery[cur] = time
        lowest[cur] = time
        time+=1

        for child in graph[cur]:
            if child == parent:
                continue
            self.dfs(graph, n, cur, child, discovery, lowest, time)

            if lowest[child]>discovery[cur]:
                self.res.append([child, cur])
            
            lowest[cur] = min(lowest[child], lowest[cur])
        
