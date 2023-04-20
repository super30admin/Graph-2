"""
Rasika Sasturkar
Using DFS -
Time Complexity: O(V+E), V is no. of vertices & E is no. of edges in the graph
Space Complexity: O(V+E)
"""


def criticalConnections(n, connections):
    result = []
    # null case
    if connections is None:
        return result

    discovery = [-1 for _ in range(n)]
    lowest = [0 for _ in range(n)]
    graph = []
    time = 0

    def build_graph(connections, n):
        for i in range(n):
            graph.append([])
        for edge in connections:
            graph[edge[0]].append(edge[1])
            graph[edge[1]].append(edge[0])

    def dfs(v, u):
        nonlocal time
        # base case
        if discovery[v] != -1:
            return

        # logic
        discovery[v] = time
        lowest[v] = time
        time += 1

        for n in graph[v]:
            if n == u:
                continue
            dfs(n, v)
            if lowest[n] > discovery[v]:
                result.append([n, v])
            lowest[v] = min(lowest[v], lowest[n])

    build_graph(connections, n)
    dfs(0, 0)
    return result


def main():
    """
    Main function - examples from LeetCode problem to show the working.
    This code ran successfully on LeetCode and passed all the test cases.
    """
    print(criticalConnections(n=4, connections=[[0, 1], [1, 2], [2, 0], [1, 3]]))  # return [[3, 1]]
    print(criticalConnections(n=2, connections=[[0, 1]]))  # return [[1, 0]]


if __name__ == "__main__":
    main()
