from typing import List
from collections import defaultdict


class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        """
            https://leetcode.com/problems/critical-connections-in-a-network/
            Time Complexity - O(V+E)
            Space Complexity - O(V+E)
            'V' -> No of vertices
            'E' -> No of edges
        """
        self.visited_time = {}
        self.low_links = {}
        self.graph = defaultdict(list)
        self.result = []
        self.time = 0

        # build graph
        for connection in connections:
            parent, child = connection[0], connection[1]
            self.graph[parent].append(child)
            self.graph[child].append(parent)

        for node in self.graph:
            if node not in self.visited_time:
                self._dfs(node, node)

        return self.result

    def _dfs(self, cur_node, parent_node):

        self.time += 1
        self.visited_time[cur_node] = self.time
        self.low_links[cur_node] = self.time

        for child_node in self.graph[cur_node]:

            if child_node == parent_node:
                continue

            if child_node not in self.visited_time:
                self._dfs(child_node, cur_node)
                if self.low_links[child_node] > self.visited_time[cur_node]:
                    self.result.append([cur_node, child_node])

            self.low_links[cur_node] = min(self.low_links[cur_node], self.low_links[child_node])
