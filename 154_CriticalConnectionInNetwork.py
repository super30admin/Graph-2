'''
Accepted on leetcode(1192)
time - O(N*N)
space -O(N)
'''


class Solution:
    discovery = None
    lower = None
    time = None
    result = None
    graph = None

    def criticalConnections(self, n: int, connections):
        Solution.discovery = [-1 for i in range(n)]
        Solution.lower = [0 for i in range(n)]
        Solution.time = 0
        Solution.result = []
        Solution.graph = []

        # build graph
        self.buildGraph(connections, n)

        # dfs
        self.dfs(0, 0)

        # return the result
        return Solution.result

    def buildGraph(self, connections, n):
        for i in range(n):
            Solution.graph.append([])
        # create a array list with all the connections as a list at that node position in arraylist.
        for node in connections:
            fromNode = node[0]
            toNode = node[1]

            Solution.graph[fromNode].append(toNode)
            Solution.graph[toNode].append(fromNode)

    def dfs(self, u, parent):
        # initialize discovery and lower same as time for each dfs call on that node.
        Solution.discovery[u] = Solution.time
        Solution.lower[u] = Solution.time
        Solution.time += 1
        # iterate through the constructed adjacency list for the given graph for that particular node for traversal.
        for neigh in Solution.graph[u]:
            # case 1: if neighbor is parent, then skip.
            if neigh == parent:
                continue;
            # case 2: if neighbor is not discovered then traverse that path.
            if Solution.discovery[neigh] == -1:
                self.dfs(neigh, u)
                # following condition satisfies that is critical connection add to result.
                if Solution.lower[neigh] > Solution.discovery[u]:
                    Solution.result.append([neigh, u])
            # case 3: if the neighbor is discovered and not the parent then there is a cycle and
            # update lower accordingly.
            Solution.lower[u] = min(Solution.lower[u], Solution.lower[neigh])