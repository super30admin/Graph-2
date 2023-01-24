# Time Complexity : O(n+m), Where n is number of nodes, m is number of edges in graph 
# Space Complexity : O(n), Where n is number of nodes in graph
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Nothing specific


from typing import List
class Solution:
    #This solution is based on Tarzans Algorithms
    def __init__(self):
        self.time=0
        self.result=[]
            
                    
        
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        #First let us build Adjacency list based on the connections
        hashmap={}
        for i in connections:
            oneend=i[0]
            otherend=i[1]
            
            if(oneend not in hashmap):
                hashmap[oneend]=[otherend]
            else:
                hashmap[oneend].append(otherend)
            
            if(otherend not in hashmap):
                hashmap[otherend]=[oneend]
            else:
                hashmap[otherend].append(oneend)
        
        self.discovery=[-1 for _ in range(n)]
        self.lowest=[-1 for _ in range(n)]
        #print(hashmap)
        self.dfs(0,-1,hashmap)
        
        return self.result
    
    def dfs(self,u,v,graph):
        #Base Case
        if(self.discovery[u]!=-1):
            #It is already explored
            return
        
        #Actual Logic
        self.discovery[u]=self.time
        self.lowest[u]=self.time
        #print(self.lowest)
        #print(self.discovery)
        self.time+=1
        
        for children in graph.get(u):
            if(children == v):
                continue
            self.dfs(children,u,graph)
            if(self.lowest[children]>self.discovery[u]):
                #This is critical connection
                self.result.append([children,u])
            self.lowest[u]=min(self.lowest[children],self.lowest[u])