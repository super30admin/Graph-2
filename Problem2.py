class Solution:
    # Time complexity: O(N^2) - DFS and iteration over nodes
    # Space complexity: O(N) - Storing colors, groups, and initGroups arrays

    colors = []  # To store the color assigned to each node

    def dfs(self, graph, node, color):
        """
        Depth-First Search to color nodes and form groups.
        """
        if self.colors[node] != -1:
            return
        self.colors[node] = color
        for neighbor in range(len(graph[node])):
            if graph[node][neighbor] == 1:
                self.dfs(graph, neighbor, color)

    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        """
        Minimize malware spread by selecting the optimal node to remove.
        """
        if not graph or len(graph) == 0:
            return 0

        n = len(graph)
        self.colors = [-1] * n  # Initialize colors array to track groups
        c = 0  # Color counter

        # Iterate through nodes and perform DFS to color nodes and form groups
        for i in range(n):
            if self.colors[i] == -1:
                self.dfs(graph, i, c)
                c += 1

        groups = [0] * c  # Store the size of each group
        for ele in self.colors:
            groups[ele] += 1

        # Store the size of each group among initial nodes
        initGroups = [0] * c
        for node in initial:
            cl = self.colors[node]
            initGroups[cl] += 1

        result = -1  # Initialize result with an integer index
        maxGroupSize = 0  # Store the maximum group size

        # Iterate through initial nodes to find the optimal node to remove
        for node in initial:
            cl = self.colors[node]
            count = initGroups[cl]
            if count == 1:
                if groups[cl] > maxGroupSize or (groups[cl] == maxGroupSize and node < result):
                    maxGroupSize = groups[cl]
                    result = node

        if result == -1:
            result = min(initial)

        return result
