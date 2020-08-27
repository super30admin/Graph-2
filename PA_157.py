#Time Complexity - O(n^2)
#Space Complexity - O(n) 
from collections import defaultdict
class Solution(object):
    def minMalwareSpread(self, graph, initial):
        """
        :type graph: List[List[int]]
        :type initial: List[int]
        :rtype: int
        """
        if not graph or not initial:
            return 0
        d = defaultdict(list)
        
        def buildGraph(graph):
            for x in range(len(graph)):
                temp = graph[x]
                for i in range(len(temp)):
                    if temp[i] == 1:
                        d[x].append(i)
        buildGraph(graph)
        color = [-1] * len(graph)
        def dfs(node,c):
            if color[node] != -1:
                return
            color[node] = c
            for i in d[node]:
                dfs(i,c)
        c = 0
        for i in d.keys():
            if color[i] == -1:
                dfs(i,c)
                c = c + 1

        adjColor = [0] * c
        for i in color:
            adjColor[i] = adjColor[i] + 1
        if len(adjColor) == len(graph) or len(adjColor) == 1:
            return min(initial)

        malColor = [0] * c
        for i in initial:
            malColor[color[i]] = malColor[color[i]] + 1
        ans = -999999
        node_ans = 0
        
        xtemp = []
        for node in initial:
            if  malColor[color[node]] == 1:
                total_nodes = adjColor[color[node]]
                if total_nodes - 1 == 0:
                    xtemp.append(node)
                elif total_nodes - 1 > ans:
                    ans = total_nodes - 1
                    node_ans = node
                
        if ans == -999999:
            if xtemp:
                return min(xtemp)
            return min(initial)
        return node_ans