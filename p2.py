#Time: O(V)
#Space: O(V)

class Solution:

    def dfsVisit(self, graph: List[List[int]], colors: List[int], u: int,
                   currentColor: int) -> None:

        #   a normal DFS visit and update color representing the component
        colors[u] = currentColor
        for v in range(len(graph)):
            if (colors[v] == -1 and graph[u][v] == 1):
                self.dfsVisit(graph, colors, v, currentColor)

    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:

        #   initializations
        n = len(graph)

        colors = [-1 for i in range(n)]

        currentColor = 0

        #   perform DFS on all components and update their colors
        for u in range(n):
            if (colors[u] == -1):
                self.dfsVisit(graph, colors, u, currentColor)
                currentColor += 1

        #   initialize number of components based on final color count
        numComponents = currentColor

        #   fill each component's size
        componentSizes = [0 for i in range(numComponents)]
        for u in range(n):
            componentSizes[colors[u]] += 1

        #   fill each component's infected vertices
        countInfectedNodes = [0 for i in range(numComponents)]
        for u in initial:
            countInfectedNodes[colors[u]] += 1

        #   index to be cured
        cureIndex = float('inf')

        #   for each node in initial array
        for node in initial:

            #   if only one node infected
            if (countInfectedNodes[colors[node]] == 1):

                #   if this occurs first time
                if (cureIndex == float('inf')):
                    cureIndex = node

                #   if current node's component size is greater than existing node's component size
                elif (componentSizes[colors[node]] > componentSizes[colors[cureIndex]]):
                    cureIndex = node

                #   if equal component sizes => node with less index value
                elif (componentSizes[colors[node]] == componentSizes[colors[cureIndex]]):
                    cureIndex = min(cureIndex, node)

        #   if no one infected node found => minimum node should be returned
        if (cureIndex == float('inf')):
            for node in initial:
                cureIndex = min(cureIndex, node)

        #   return the index to be cured
        return cureIndex
