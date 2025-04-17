#time complexity o(n^2)
#space complexity o(n)
class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        n = len(graph)
        colors = [-1] * n
        color = 0

        for i in range(n):
            if colors[i] == -1:
                #not visited doesnt have a color yet
                self.dfs(graph, colors,i, color)
                color += 1
        

        groups = [0] * color
        for i in range(n):
            cl = colors[i]
            groups[cl] += 1

        initgroups = [0] * color
        for node in initial:
            c = colors[node]
            initgroups[c] += 1

        result = float("inf")
        for node in initial:
            col = colors[node]
            #check how many are initially infected for that color
            cnt = initgroups[col]
            if cnt == 1:
                if result == float("inf"):
                    result = node
                elif groups[col] > groups[colors[result]]:
                #compare if curr node is saving more nodes that earlier result
                    result = node
                elif groups[col] == groups[colors[result]] and node < result:
                    #curr node and earlier result node are saving same number of      nodes, but the curr node is smaller
                    result = node
        
        if result == float("inf"):
            for node in initial:
                result = min(result, node)

        return result


    def dfs(self,graph: List[List[int]], colors: List[int],i : int, col: int):
        n = len(graph)
        #base case
        if colors[i] != -1:
            return
        #logic
        colors[i] = col
        for j in range(n):
            if graph[i][j] == 1:
                self.dfs(graph,colors,j,col)



            
        

        