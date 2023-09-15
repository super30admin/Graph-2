# Time Complexity : O(v+e)
# Space Complexity :O(v+e)
# Passed on Leetcode: yes


class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        def find(node):
            if parent[node] == node:
                return node
            parent[node] = find(parent[node])
            return parent[node]

        def union(node1, node2):
            root1 = find(node1)
            root2 = find(node2)
            if root1 != root2:
                parent[root1] = root2
                size[root2] += size[root1]

        n = len(graph)
        initial.sort()
        parent = [i for i in range(n)]
        size = [1] * n

        # Count initially infected nodes
        initial_infected = set(initial)

        # Union-Find: connect nodes if there is no malware between them
        for i in range(n):
            for j in range(i + 1, n):
                if graph[i][j] == 1:
                    union(i, j)

        # Count the number of nodes in each component
        component_sizes = [0] * n
        for i in range(n):
            root = find(i)
            component_sizes[root] += 1

        max_infected_count = 0
        result = -1

        # Find the node with the highest initially infected count
        for node in initial:
            root = find(node)
            if initial_infected:
                component_infected = 0
                for i in range(n):
                    if find(i) == root and i in initial_infected:
                        component_infected += 1
                if component_infected == 1:
                    if component_sizes[root] > max_infected_count:
                        max_infected_count = component_sizes[root]
                        result = node
                    elif component_sizes[root] == max_infected_count and node < result:
                        result = node

        return result
