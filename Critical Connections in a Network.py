""""// Time Complexity : O(V+E)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""


class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.d = {}
        for i in range(n):
            self.d[i] = []
        for edge in connections:
            self.d[edge[0]].append(edge[1])
            self.d[edge[1]].append(edge[0])
        self.result = []
        self.time = 0

        self.helper(0, 0)
        return self.result

    def helper(self, current, parent):
        # base
        if self.discovery[current] != -1:
            return
        # logic
        self.discovery[current] = self.time
        self.lowest[current] = self.time
        self.time += 1
        for n in self.d[current]:
            if n == parent:
                continue
            self.helper(n, current)
            if self.lowest[n] > self.discovery[current]:
                self.result.append([n, current])
            self.lowest[current] = min(self.lowest[current], self.lowest[n])