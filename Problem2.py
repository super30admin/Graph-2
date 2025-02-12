# Problem 2: Minimize Malware Spread
from typing import List
class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        n = len(graph)
        colors = [-1] * n
        cl = 0

        def dfs(graph,colors,i,cl):
            #base
            if colors[i] != -1: return

            #logic
            colors[i] = cl
            for j in range(0,n):
                if i == j: continue
                if graph[i][j] == 1:
                    dfs(graph,colors,j,cl)

        for i in range(n):
            if colors[i] == -1:
                dfs(graph,colors,i,cl)
                cl += 1

        groups = [0] * cl
        for color in colors:
            groups[color] += 1

        infctd = [0] * cl
        for node in initial:
            gr = colors[node]
            infctd[gr] += 1

        result = float('inf')
        for node in initial:
            gr = colors[node]
            if infctd[gr] == 1:
                if result == float('inf'):
                    result = node
                elif groups[colors[node]] > groups[colors[result]]:
                    result = node
                elif groups[colors[node]] == groups[colors[result]]: 
                    result = min(result,node)

        if result == float('inf'):
            for node in initial:
                result = min(result,node)

        return result

        

        