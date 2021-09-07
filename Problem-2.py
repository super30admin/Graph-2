"""
TC: O(n^2)
SC: O(n)
"""
class Solution:
    def __init__(self):
        self.colors = []
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        n = len(graph)
        self.colors = [-1] * n
        color_num = 0
        for curr_node in range(n):
            if self.colors[curr_node] == -1:
                self.dfs(graph, curr_node, color_num)
                color_num += 1
        
        group_count = [0] * color_num
        for node in self.colors:
            group_count[node] += 1
        
        affected_in_group = [0] * color_num
        for affected in initial:
            affected_in_group[self.colors[affected]] += 1
        
        result = float('inf')
        for affected_node in initial:
            if affected_in_group[self.colors[affected_node]] == 1:
                if result == float('inf'):
                    result = affected_node
                elif group_count[self.colors[affected_node]] > group_count[self.colors[result]]:
                    result = affected_node
                elif group_count[self.colors[affected_node]] == group_count[self.colors[result]] and affected_node < result:
                    result = affected_node
        
        if result == float('inf'):
            for node in initial:
                result = min(node, result)
        
        return result
    
    
    def dfs(self, graph, node, color_num):
        # base
        if self.colors[node] != -1: return
        
        # logic
        self.colors[node] = color_num
        for next_node in range(len(graph)):
            if graph[node][next_node] == 1:
                self.dfs(graph, next_node, color_num)
                
        
        