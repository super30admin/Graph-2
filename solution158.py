#Time Complexity:O(V+E)
#Space Complexity:O(E)
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.graph=[]                                                           #List that hols the list of connected Vertices for each vertice
        self.result=[]                                                          #result that holds the list of critical edges
        self.time=0                                                             #variable to hold the visit time.
        self.buildgraph(connections,n)                                          #fills the graph List with associated vertices.
        discovery=[-1]*n                                                        #list of undiscovered/discovereed verices
        lowest=[0]*n                                                            #list of lowest time the vertice was visited.
        self.dfs(0,0,discovery,lowest)                                          #start recursive call for each edge.
        return self.result
    
    def buildgraph(self,connections:List[List[int]],n:int)->None:
        for i in range(n):                                                      #for every vertice ,create a list in graph list
            self.graph.append([])
        for edge in connections:                                                #for every edge in the connection,fetch start and end vertice of the edge
            end=edge[0]
            start=edge[1]
            self.graph[end].append(start)                                       #append to the end vertice the start and viceversa
            self.graph[start].append(end)
            
    def dfs(self,v:int,u:int,discovery:List[int],lowest:List[int])->None:
        if discovery[v]!=-1:                                                    #if vertice is previously discovered break.
            return
        discovery[v]=self.time                                                  #at current vertice of discovery list update time
        lowest[v]=self.time                                                     #at current vertice of lowest list update time
        self.time+=1                                                            # increment time by one
        neighbors=self.graph[v]                                                 # find neighbors of current vertice using graphs list
        for n in neighbors:                                                     #for each neighbor in vertice
            if n==u:                                                            #if neighbor is same as end of edge, continue
                continue
            self.dfs(n,v,discovery,lowest)                                      #else call dfs on the current edge
            if lowest[n]>discovery[v]:                                          #if the lowest value at the vertice is smaller than at discovery
                self.result.append([v,n])                                       #append the edge to the result
            lowest[v]=min(lowest[v],lowest[n])                                  #update the lowest at vertice v to min between lowest of n or v
            