# Time Complexity: O(V + E), V - nodes, E - connections
# Space Complexity: O(V)

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.graph = []
        self.result = []
        self.time = 0
        self.build_graph(n, connections)

        # Lowest time a node could have been discovered
        lowest = [0 for x in range(n)]
        # Order in which nodes are discovered
        # Fill with -1 to maintain visited nodes
        discovery = [-1 for x in range(n)]
        self.dfs(0, 0, lowest, discovery)
        return self.result

    def build_graph(self, n: int, connections: List[List[int]]) -> None:
        for i in range(n):
            self.graph.append([])

        # Build adjacency list by adding neighbors to every node
        for connection in connections:
            self.graph[connection[0]].append(connection[1])
            self.graph[connection[1]].append(connection[0])

    def dfs(self, currnode: int, parent: int, lowest: List[int], discovery: List[int]) -> None:
        # base
        if discovery[currnode] != -1:
            return

        # logic
        lowest[currnode] = self.time
        discovery[currnode] = self.time
        self.time += 1

        # Explore neighbors
        neighbors = self.graph[currnode]
        for neighbor in neighbors:
            if neighbor == parent:
                continue
            self.dfs(neighbor, currnode, lowest, discovery)

            # Find critical connections
            if lowest[neighbor] > discovery[currnode]:
                self.result.append([neighbor, currnode])
            lowest[currnode] = min(lowest[currnode], lowest[neighbor])
