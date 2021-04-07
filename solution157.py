#Time Complexity:O(n^2)
#Space Complexity:O(n)
class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        self.n=len(graph)
        self.colors=[-1]*self.n                                                 #create an array of -1's for the total number of server nodes
        color=0                                                                 #an integer variable that is used to colorcode
        for i in range(self.n):                                                 #for each server node , if the value in colors array is -1
            if self.colors[i]==-1:
                self.dfs(i,graph,color)                                         #call the dfs function that color codes all nodes that are connected
                color+=1                                                        #change the color by incrementing by one
        groups=[0]*color                                                        #an array that groups all the nodes based on the color
        for c in self.colors:                                                   #increment the number of nodes at each color in group
            groups[c]+=1
        initGroups=[0]*color                                                    #create an initial group with size same as number of color
        for c in initial:
            initGroups[self.colors[c]]+=1                                       #Count the number of affected nodes assosiated with each color
        result=inf                                                              #let result be the final output.
        for node in initial:                                                    #for every affected node in initial
            count=initGroups[self.colors[node]]                                 #get its color from colors and the number of connected nodes to that node from initgroups
            if count==1:                                                        #if the count of connected node is one
                if result==inf:                                                 #and if the result is infinite or poosible affected node is less that current result 
                    result=node                                                 #or if number of nodes to be affected is same and the index is smaller than result
                elif groups[self.colors[result]]<groups[self.colors[node]]:     #update the result to be node.
                    result=node
                elif groups[self.colors[result]]==groups[self.colors[node]] and node<result:
                    result=node
        if result==inf:                                                         #if the result is still infinite  get the minimum possible affected and update result
            for node in initial:
                result=min(result,node)
        return result
                
    def dfs(self,node:int,graph:List[List[int]],color:int)->None:
        if self.colors[node]!=-1:                                               #if the color code id not updated at the node
            return
        self.colors[node]=color                                                 #assign the color to the current node
        for j in range(self.n):                                                 #also call dfs to color code the rest of the nodes.
            if graph[node][j]==1:
                self.dfs(j,graph,color)