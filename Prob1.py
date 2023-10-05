
#TC O(n^2) and SC O(n)
class Solution(object):
    def __init__(self):
        self.colors=None
    def minMalwareSpread(self, graph, initial):
        """
        :type graph: List[List[int]]
        :type initial: List[int]
        :rtype: int
        """

        if not graph: return 0
        if not graph:
            return 0
        
        n=len(graph)
        c=0
        self.colors=[-1 for _ in range(n)] #diffrentiate which network each node belongs to by using DFS to visit each node of current network. So, call dfs on each node so eventually all networks will be covered.
        for i in range(n):
            if self.colors[i]==-1:
                self.dfs(graph,i,c) #O(N^2) operation cause of the line 18 for loop
                c+=1
        
        groups=[0 for _ in range(c)] #let's count how many nodes each networks has
        for el in self.colors:
            groups[el]+=1
        initGroups=[0 for _ in range(c)] #how many nodes in each network is intially infected?
        for node in initial:
            cl=self.colors[node]
            initGroups[cl]+=1

        result=float('inf')
        for node in initial: #let's use initial array to figure out which node is the result
            cl=self.colors[node]
            cnt=initGroups[cl]
            if cnt==1: #if number of inital infected in a network is more than 1 don't calculate stuff.
                if result==float('inf'): #first node we have checked so assign to res
                    result=node
                elif groups[cl]>groups[self.colors[result]]: #if current node has more nodes in it's network than result's network assign curr to res
                    result=node
                elif groups[cl]==groups[self.colors[result]] and node<result: #if equal, assign the lesser one (given in question)
                    result=node
        if result==float('inf'): #if every network has more than 1 infected nodes, then blindly get the minimum of hte intial. array
            return min(initial)
        return result

    
    def dfs(self,graph,i,color):
        if self.colors[i]!=-1: #if alreayd visited return
            return

        self.colors[i]=color
        for j in range(len(graph)): #in current row go over each col
            if graph[i][j]==1 and i!=j: #1,1 2,2 3,3 will obviously be 1, skip them
                self.dfs(graph,j,color) #here, we are passing current col as row to dfs, so eventually the entire matrix will be surveyed
