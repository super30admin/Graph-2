class Solution:
    # Tc: O(N^2)  Sc: O(N)
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        n = len(graph)
        initial.sort()
        min_removed_node = -1
        min_infected = float('inf')

        for remove_node in initial:
            infected = set(initial) - {remove_node}
            queue = list(infected)

            while queue:
                node = queue.pop(0)
                for nei in range(n):
                    if graph[node][nei] and nei not in infected:
                        infected.add(nei)
                        queue.append(nei)

            if len(infected) < min_infected or (len(infected) == min_infected and remove_node < min_removed_node):
                min_infected = len(infected)
                min_removed_node = remove_node

        return min_removed_node