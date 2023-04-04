#time complexity: O(n^2)
#space complexity: O(n)
#ran on leetcode: yes
#Find out  the largest disconnected component wich has only one of the infected malware. 
class Solution:
    def dfs(self,node,color,graph):
        if(self.colors[node] != -1):
            return
        self.colors[node] = color
        for i in range(self.n):
            if(graph[node][i]==1):
                self.dfs(i, color, graph)

    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        self.n=len(graph)
        self.colors=[-1]*self.n
        c=0
        for i in range(self.n):
            self.dfs(i,c,graph)
            c+=1

        group=[0]*c
        for cl in self.colors:
            group[cl]+=1
        initColor=[0]*c

        for node in initial:
            initColor[self.colors[node]]+=1
        result=100000
        for node in initial:
            color=self.colors[node]
            count = initColor[color]
            if(count==1):
                if(result == 100000):
                    result = node
                elif (group[color] > group[self.colors[result]]):
                    result=node
                elif (group[color] == group[self.colors[result]] and node < result):
                    result = node
        if(result==100000):
            for node in initial:
                result = min(result, node)
        return result


