"""

## Problem 1192: Critical Connections in a Network

## Author: Neha Doiphode
## Date:   11-12-2022

## Description:
    There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections
    forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi.
    Any server can reach other servers directly or indirectly through the network.

    A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
    Return all critical connections in the network in any order.

## Examples:
    Example 1:
        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.

    Example 2:
        Input: n = 2, connections = [[0,1]]
        Output: [[0,1]]

## Constraints:
    2 <= n <= 105
    n - 1 <= connections.length <= 105
    0 <= ai, bi <= n - 1
    ai != bi
    There are no repeated connections.

## Time complexity: O(V + E), where V is number of vertices. E is number of Edges.
                    We process each node exactly once.

## Space complexity: O(3V + E), where V is number of vertices and E is number of edges.
                    O(V) for the discovery array.
                    O(V) for the lowest array.
                    O(V + E) to build the graph.


"""

from typing import Optional, List

def get_input():
    print("Enter the number of vertices in the graph: ", end = "")
    vertices = int(input())

    print("Enter the number of connections in the graph: ", end = "")
    connections = int(input())

    inp_list = []
    for connection in range(connections):
        print(f"Edges at vertex {connection}(int with spaces): ", end = "")
        inp = input()
        inp = [int(element) for element in inp.split()]
        inp_list.append(inp)

    print()
    return vertices, inp_list

class Solution:
    result = []
    graph  = []
    discovery = []
    lowest = []
    time = 0

    def dfs(self, child: int, parent: int) -> None:
        # base case
        if self.discovery[child] != -1:
            return

        # logic
        # Set discovery and lowest for every node.
        self.discovery[child] = self.time
        self.lowest[child]    = self.time

        # Increment time
        self.time += 1

        # Now we need to iterate thru all the children of current child
        for kid in self.graph[child]:
            if kid == parent:
                continue
            self.dfs(kid, child)

            # after the dfs is complete check if
            # lowest of kid becomes greater than discovery of child
            if self.lowest[kid] > self.discovery[child]:
                self.result.append([child, kid])

            # Update the lowest order of child
            self.lowest[child] = min(self.lowest[child], self.lowest[kid])


    def build_graph(self, connections: List[List[int]]) -> None:
        for edge in connections:
            from_node = edge[0]
            to_node   = edge[1]
            # Since graph is undirected we need to add
            # edges from to -> from and from -> to
            self.graph[from_node].append(to_node)
            self.graph[to_node].append(from_node)


    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        """Tarjan's Algorithm:"""
        # Declare discovery and lowest array
        self.discovery = [-1 for _ in range(n)]
        self.lowest    = [0 for _ in range(n)]
        self.result    = []
        # connections list does not need to be converted to adjacency list
        # as we already have it indexed based.
        # connections =
        # index       =  0     1     2     3
        # list        [[0,1],[1,2],[2,0],[1,3]]

        # Declare time variable
        self.time = 0

        # Create an empty list of list to build the graph
        self.graph = [[] for _ in range(n)]

        # Create a local function to build the graph
        self.build_graph(connections)

        # Now call dfs function
        # dfs(child node, parent node)
        child = 0
        parent = 0
        self.dfs(child, parent)

        # Once the dfs is complete return result
        return self.result

# Driver code
solution = Solution()
vertices, inp_list = get_input()
print(f"Input: Number of vertices in the graph: {vertices}")
print(f"Input: List of connections in the graph: {inp_list}")
print(f"Output: Using Tarjan's algorithm: List of critical connections in the graph: {solution.criticalConnections(vertices, inp_list)}")
