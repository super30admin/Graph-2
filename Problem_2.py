class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        self.n = len(graph)
        self.colors = [-1] * self.n
        cl = 0
        for i in range(self.n):
            if self.colors[i] == -1:
                self.dfs(graph, i, cl)
                cl += 1
                
        groups = [0] * cl
        for color in self.colors:
            groups[color] += 1
        
        # how many nodes in particular group are infected
        initialGroups = [0] * cl
        for node in initial:
            initialGroups[self.colors[node]] += 1
         
        result = float('inf')
        for node in initial:
            if initialGroups[self.colors[node]] == 1:
                if result == float('inf'):
                    result = node
                elif groups[self.colors[result]] < groups[self.colors[node]]:
                    result = node
                elif groups[self.colors[result]] == groups[self.colors[node]] and node < result:
                    result = node
                    
        if result == float('inf'):
            result = min(initial)
        return result
            
            
                
    def dfs(self, graph, idx, cl):
        if self.colors[idx] != -1:
            return
        self.colors[idx] = cl
        for j in range(self.n):
            if graph[idx][j] == 1:
                self.dfs(graph, j, cl)

# Time Complexity: O(V^2)
# Space Complexity: O(V) 