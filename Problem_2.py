# Time Complexity: O(n^2)
# Space Complexity: O(n)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
    def minMalwareSpread(self, graph, initial):
        """
        :type graph: List[List[int]]
        :type initial: List[int]
        :rtype: int
        """
        def bfs(delnode):
            (stack, visited) = [i for i in initial if i != delnode], {i for i in initial if i != delnode}
            while stack:
                node = stack.pop(0)
                for (i, val) in enumerate(graph[node]):
                    if ((val == 1) and (i not in visited)):
                        visited.add(i)
                        stack.append(i)
            return len(visited)
        return (min(initial, key = lambda x: (bfs(x), x)))