# Time Complexity : O(n*n), Where n is number of nodes in the graph
# Space Complexity : O(n), Where n is number of nodes in the graph
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Nothing specific

import sys
from typing import List

class Solution:
    def DFS(self,graph: List[List[int]],start_node):
        #Basecase
        if(self.colors[start_node]!=None):
            return
        #Actual logic
        self.colors[start_node]=self.color
        for j in range(len(graph)):
            if(graph[start_node][j]==1):
                #Means j is adjacent vertex of start_node
                    if(self.colors[j]==None):
                        #It was not visited
                        self.DFS(graph,j)
                    else:
                        continue
    
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        #Edge Case
        if(len(graph)==0):
            return -1
        
        n=len(graph)
        #First let us create all the connected components in the given Graph
        self.colors=[None for _ in range(n)]#This array will hold connected components in the given graph
        self.color=0#This will also will give number of connected components
        
        for i in range(n):
            if(self.colors[i]==None):
                #Do DFS
                self.DFS(graph,i)
                self.color+=1

        #print(self.colors)
        #print(self.color)
        
        n_elements=[0 for _ in range(self.color)]#This will have number of nodes in each connected component
        n_infected_elements=[0 for _ in range(self.color)]#This will have number of nodes infected intially in each connected component 
        for i in self.colors:
            n_elements[i]+=1
        for i in initial:
            n_infected_elements[self.colors[i]]+=1
        
        #print(n_elements)
        #print(n_infected_elements)
        
        sol=sys.maxsize
        for i in initial:
            col=self.colors[i]
            if(n_infected_elements[col]==1):
                #This is the case when we have only one infected node
                if(sol==sys.maxsize):
                    #Here solution doesnot exist
                    sol=i
                else:
                    #Here solution exist
                    if(n_elements[col]>n_elements[self.colors[sol]]):
                        sol=i
                    elif(n_elements[col]==n_elements[self.colors[sol]]):
                        sol=min(sol,col)
        
        if(sol==sys.maxsize):
            sol=min(initial)
        
        return sol
            