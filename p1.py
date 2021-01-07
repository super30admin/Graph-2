#Time: O(V+E)
#Space: O(V+E)
class Solution:

    def __init__(self):
        self.time = 0

    def getGraph(self, n: int, connections: List[List[int]]) -> List[List[int]]:

        #   a helper function to create the graph
        adjacencyList = []
        for i in range(n):
            adjacencyList.append([])

        for edge in connections:
            u = edge[0]
            v = edge[1]
            adjacencyList[u].append(v)
            adjacencyList[v].append(u)

        return adjacencyList

    def dfsVisit(self, adjacencyList: List[List[int]], u: int, visited: List[bool],
                   discoveryTime: List[int], lowTime: List[int], parent: List[int],
                   finalResult: List[List[int]]) -> None:

        
        visited[u] = True
        self.time += 1
        discoveryTime[u] = self.time
        lowTime[u] = self.time

        #   for each of its vertices
        for v in adjacencyList[u]:

            #   if not visited
            if (visited[v] != True):
                parent[v] = u                                          

                self.dfsVisit(adjacencyList, v, visited, discoveryTime,
                                lowTime, parent, finalResult)

               
                lowTime[u] = min(lowTime[u], lowTime[v])

                
                if (lowTime[v] > discoveryTime[u]):
                    finalResult.append([u, v])

            
            elif (v != parent[u]):
                lowTime[u] = min(lowTime[u], discoveryTime[v])

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:

       
        if (connections == None or len(connections) == 0 or n == 0):
            return []

        
        finalResult = []

        adjacencyList = self.getGraph(n, connections)

        visited = [False for i in range(n)]
        discoveryTime = [0 for i in range(n)]
        lowTime = [0 for i in range(n)]
        parent = [0 for i in range(n)]

        
        self.dfsVisit(adjacencyList, 0, visited, discoveryTime,
                        lowTime, parent, finalResult)

        
        return finalResult
