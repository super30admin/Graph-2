'''
Time Complexity: 0(V+E)
Space Complexity: 0(V+E)
Run On LeetCode: Yes
'''
class Solution:
    
    def __init__(self):
        self.__adjacencyList = {}
        self.__discoveryList = None
        self.__lowestList = None
        self.__time = 0
        self.__criticalConnection = []
    
    def dfsTraversal(self,currentNode,parentNode):
        # base-case -- chk if its already discovered
        if self.__discoveryList[currentNode] != -1:
            return 
        
        # logic-case
        # set the discovery time and lowest possible dfs time
        self.__discoveryList[currentNode] = self.__time
        self.__lowestList[currentNode] = self.__time
        self.__time += 1
        
        # iterate the edges to the currentNode
        edges = self.__adjacencyList[currentNode]
        for e in edges:
            # base-chk --- if the edge e is equal to the parentNone (going back to the parentNode!!)
            if e == parentNode:
                continue
            
            # logic-chk
            
            # perfom dfs
            self.dfsTraversal(e,currentNode)
            
            # chk -- if its a critical connection
            if self.__lowestList[e] > self.__discoveryList[currentNode]:
                self.__criticalConnection.append([currentNode,e])
            
            # update the lowest time to earliest possible DFS
            self.__lowestList[currentNode] = min(self.__lowestList[currentNode], self.__lowestList[e]) 
            
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        
        # covert edge list to the adjacency list -- its an undirected graph
        for i in range(0,len(connections)):
            edge = connections[i]
            if edge[0] not in self.__adjacencyList:
                self.__adjacencyList[edge[0]] = [edge[1]]
            else:
                self.__adjacencyList[edge[0]].append(edge[1])
            
            if edge[1] not in self.__adjacencyList:
                self.__adjacencyList[edge[1]] = [edge[0]]
            else:
                self.__adjacencyList[edge[1]].append(edge[0])
        
        # initialize visitedList to False
        self.__visitedList = [False] * len(connections)
        
        print('\n1. AdjacencyList is:\t')
        for key in self.__adjacencyList:
            print(f"\t{key}: {self.__adjacencyList[key]}")
        
        # initialize the discoveryList
        self.__discoveryList = [-1]*len(self.__adjacencyList)
        
        # initialize the lowestList
        self.__lowestList = [0]*len(self.__adjacencyList)
        
        self.dfsTraversal(0,0) #-- initial case, currentNode is itself a parentNode
        print('2. DiscoveryList is:\t',self.__discoveryList)
        print('3. LowestList is:\t',self.__lowestList)
        print('4. Critical connections is:\t',self.__criticalConnection)
        
        return self.__criticalConnection
            