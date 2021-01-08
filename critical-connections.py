# TIME COMPLEXITY: O(V+E)
# SPACE COMPLEXITY: O(V)
class Solution(object):
    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        # Build adjList
        adjList = [[] for _ in range(n)]

        for (src, dst) in connections:
            adjList[src].append(dst)
            adjList[dst].append(src)

        # Initialize tracking arrays
        # Tracks visited vertices
        visited = [-1] * n
        # Track arrival and departure times
        timestamp = [0]
        # Arrival and departure arrays
        arrival = [-1] * n
        departure = [-1] * n
        # Track parent of every vertex
        parent = [-1] * n
        # Keep track of the smallest arrival time from source
        oldestarrival = [-1] * n
        # Results array to append critical connections - bridge edges
        result = []

        def dfs(source):
            # Mark source as visited and add arrival time for source
            visited[source] = 1
            arrival[source] = timestamp[0]
            timestamp[0] += 1
            # oldest arrival time is initialized to arrival time of source
            oldestarrival[source] = arrival[source]
            for neighbor in adjList[source]:
                if visited[neighbor] == -1:
                    parent[neighbor] = source
                    # Every suordinate reports if there exists a backedge from it's subtree to a vertex before it (parent or above)
                    oldestarrival[source] = min(
                        oldestarrival[source], dfs(neighbor))
                else:
                    if neighbor != parent[source] and departure[neighbor] == -1:
                        # Exploring a back-edge
                        # Update oldest arrival time - min of source arrival time, neighbor arrival time
                        # If the neighbor's arrival time is less than the current arrival time -> the back-edge is going to a vertex preceeding the current node
                        # This back-edge is part of a cycle
                        oldestarrival[source] = min(
                            oldestarrival[source], arrival[neighbor])
            # At the end of sub-tree construction, if the oldest arrival is unchanged -> there is no back edge in the sub-trees that points to a vertex preceeding the current vertex
            # Also check that the vertex is not the root
            if oldestarrival[source] == arrival[source] and source != 0:
                result.append([source, parent[source]])
            departure[source] = timestamp[0]
            timestamp[0] += 1
            # Every sub-ordinate returns the oldestarrival time of it's sub-tree
            # Every sub-ordinate checks if there are any bridge edges in it's subtrees
            return oldestarrival[source]

        dfs(0)
        return result
