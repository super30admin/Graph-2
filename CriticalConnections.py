from typing import List


class Solution:
    def helper(self, current, parent):
        # base
        if self.discovery[current] != -1:
            return
        # logic
        self.discovery[current] = self.time
        self.lowest[current] = self.time
        self.time += 1
        for i in self.dictu[current]:
            if i == parent:
                continue
            self.helper(i, current)
            # if I can reach the node that was discovered at the time which is <= to its parent discovery time,
            # then we have a cycle in it.
            # we don't need the connections that stay in the cycle.
            if self.lowest[i] > self.discovery[current]:
                self.result.append([i, current])
            self.lowest[current] = min(self.lowest[current], self.lowest[i])

    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.dictu = {}
        for i in connections:
            if i[0] not in self.dictu:
                self.dictu[i[0]] = [i[1]]
            elif i[0] in self.dictu:
                self.dictu[i[0]].append(i[1])
            if i[1] not in self.dictu:
                self.dictu[i[1]] = [i[0]]
            elif i[1] in self.dictu:
                self.dictu[i[1]].append(i[0])
        self.result = []
        self.time = 0
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.helper(0, 0)
        return self.result


print(Solution().criticalConnections(
    10, [[1, 0], [2, 0], [3, 0], [4, 1], [5, 3], [6, 1], [7, 2], [8, 1], [9, 6], [9, 3], [3, 2], [4, 2], [7, 4], [6, 2],
         [8, 3], [4, 0], [8, 6], [6, 5], [6, 3], [7, 5], [8, 0], [8, 5], [5, 4], [2, 1], [9, 5], [9, 7], [9, 4],
         [4, 3]]))
