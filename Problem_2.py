"""
Time Complexity : O(V) - dfs over the vertices only if conditions satisfy
Space Complexity : O(V)- stack space plus arrays
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
For this question, we have certain preprocessing tp follow. First of all, we need to do DFS over the matrix to find out which
nodes are together. To do so, we can color the nodes in same color, if they are together. Secondly, we nee to make a list which
stores oinformation regarding number of nodes inside a particular colored group. Thereafter, we need to make another list which
has info of how many nodes are infeted in all the groups. Then we will traverse over the initial infected array, check in which
group they are, how many infected servers in that group. If its more than 1, we basically need to ignore it. If it becomes 1 for the
first time, we record the node's details. If the number of node is 1 and we already have 1,we will check which one has more nodes
to save, we would take that value. If number of nodes are also same, we would take the minimum.  In case, after we iterate through 
everything and we find that there is no group with 1 infected server,k we can return the node number with least value.
"""


class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        if not graph:
            return 0
        n = len(graph)
        self.colorNodes = [-1]*n
        self.color = 0
        result = float('inf')

        for i in range(n):
            if self.colorNodes[i] == -1:
                self.dfs(graph, i, n)
                self.color += 1

        group = [0]*self.color
        infected = [0]*self.color

        for i in self.colorNodes:
            group[i] += 1

        for i in initial:
            grp = self.colorNodes[i]
            infected[grp] += 1

        for i in initial:
            grp = self.colorNodes[i]
            noInfected = infected[grp]
            if noInfected == 1:
                if result == float('inf'):
                    result = i
                elif group[grp] > group[self.colorNodes[result]]:
                    result = i
                elif group[grp] == group[self.colorNodes[result]]:
                    result = min(result, i)

        if result == float('inf'):
            result = min(initial)
        return result

    def dfs(self, graph, node, n):
        self.colorNodes[node] = self.color
        for i in range(n):
            if graph[node][i] == 1 and self.colorNodes[i] == -1:
                self.dfs(graph, i, n)
