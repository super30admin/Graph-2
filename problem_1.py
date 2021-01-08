"""
Time Complexity : O(V+E) - as we do DFS only once
Space Complexity : O(V+E)- creating the list
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
The bruteforce here would be to traverse over the connections given and consider that it is removed. Now do DFS the whole list
and check if we are able to reach all the nodes. If yes, the edge removed is not a critical connection. But, if we are not able 
to reach all the nodes, that means the graph has been divided into 2 halves and that edge is a critical connection. This
approach would be of n^2 complexity. To make it optimal, we implement Tarjan's algorithm. According to it, we just traverse 
the whole graph via DFS once. While we are traversing, we give our nodes a discovery time and a low time. While we are traversing and 
when we start to come back, we see that the node from which we are coming back, if it has a low time less than the low time of the current
node, we take the minimum one. This basically is an indication of a cycle. In case we see that the low time of the neighbor we came from
is greater than the discovery time of the node we re on, we have found a critical edge. For better understanding, look up this youtube
video https://www.youtube.com/watch?v=RYaakWv5m6o&ab_channel=TechRevisions 
"""


class Solution:
    def createList(self, n, connections):
        self.graph = [[] for i in range(n)]
        for node in connections:
            self.graph[node[0]].append(node[1])
            self.graph[node[1]].append(node[0])

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        result = set()
        discovery = [-1] * n
        low = [0] * n

        self.time = 0
        self.createList(n, connections)

        def dfs(u, v):
            if discovery[u] != -1:
                return
            discovery[u] = self.time
            low[u] = self.time
            self.time += 1

            for node in self.graph[u]:
                if node == v:
                    continue
                dfs(node, u)

                if low[node] > discovery[u]:
                    result.add((node, u))

                low[u] = min(low[node], low[u])

        dfs(0, None)
        return result
