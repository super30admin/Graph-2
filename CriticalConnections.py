'''
Solution:
1.  The given Graph is connected and so all vertices will be visited with one DFS on any vertex.
2.  Compute low time and discovery time at each vertex whenever possible, and after DFS visit, if low time of current
    node is still greater than discovery time of parent node => critical edge.
3.  Return all critical edges in similar way.

Time Complexity:    O(V + E)
Space Complexity:   O(V + E) #  including recursive stack space

--- Passed all testcases on leetcode successfully.
'''

class Solution:

    def __init__(self):
        self.time = 0

    def __getGraph(self, n: int, connections: List[List[int]]) -> List[List[int]]:

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

    def __dfsVisit(self, adjacencyList: List[List[int]], u: int, visited: List[bool],
                   discoveryTime: List[int], lowTime: List[int], parent: List[int],
                   finalResult: List[List[int]]) -> None:

        #   update visited to True as it is just visited, increment time, update discovery and low time to be current time
        visited[u] = True
        self.time += 1
        discoveryTime[u] = self.time
        lowTime[u] = self.time

        #   for each of its vertices
        for v in adjacencyList[u]:

            #   if not visited
            if (visited[v] != True):
                parent[v] = u                                           #   allocate the parent and perform DFS

                self.__dfsVisit(adjacencyList, v, visited, discoveryTime,
                                lowTime, parent, finalResult)

                #   after DFS and getting back, update low time to be minimum of low time of parent and child nodes
                lowTime[u] = min(lowTime[u], lowTime[v])

                #   if low time of current node is still greater than discovery time of parent node => critical edge
                if (lowTime[v] > discoveryTime[u]):
                    finalResult.append([u, v])

            #   if already visited => update low time with minimum of low time of parent and discovery time of child
            elif (v != parent[u]):
                lowTime[u] = min(lowTime[u], discoveryTime[v])

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:

        #   edge case check
        if (connections == None or len(connections) == 0 or n == 0):
            return []

        #   initializations and creating graph
        finalResult = []

        adjacencyList = self.__getGraph(n, connections)

        visited = [False for i in range(n)]
        discoveryTime = [0 for i in range(n)]
        lowTime = [0 for i in range(n)]
        parent = [0 for i in range(n)]

        #   perform DFS on any one node as it is connected Graph initially
        self.__dfsVisit(adjacencyList, 0, visited, discoveryTime,
                        lowTime, parent, finalResult)

        #   return all critical edges
        return finalResult