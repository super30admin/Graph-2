"""
Problem : 2

Time Complexity : O(n^2)
Space Complexity : O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

"""

# Minimize Malware Spread

class Solution(object):
    def __init__(self):
        self.colors=None
    def minMalwareSpread(self, graph, initial):
        """
        :type graph: List[List[int]]
        :type initial: List[int]
        :rtype: int
        """
        if not graph:
            return 0
        
        n=len(graph)
        c=0
        self.colors=[-1 for _ in range(n)]
        for i in range(n):
            if self.colors[i]==-1:
                self.dfs(graph,i,c)
                c+=1
        
        groups=[0 for _ in range(c)]
        for el in self.colors:
            groups[el]+=1
        initGroups=[0 for _ in range(c)]
        for node in initial:
            cl=self.colors[node]
            initGroups[cl]+=1
        result=float('inf')
        for node in initial:
            cl=self.colors[node]
            cnt=initGroups[cl]
            if cnt==1:
                if result==float('inf'):
                    result=node
                elif groups[cl]>groups[self.colors[result]]:
                    result=node
                elif groups[cl]==groups[self.colors[result]] and node<result:
                    result=node
        if result==float('inf'):
            for node in initial:
                result=min(result,node)
        return result

    
    def dfs(self,graph,i,color):
        if self.colors[i]!=-1:
            return

        self.colors[i]=color
        for j in range(len(graph)):
            if graph[i][j]==1 and i!=j:
                self.dfs(graph,j,color)

