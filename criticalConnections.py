# Time Complexity : O(v+e)
# Space Complexity : O(v+e)
# Did this code successfully run on Leetcode : Yes
from collections import defaultdict

class Solution:
    def criticalConnections(self, n, connections):

        graph = defaultdict(set)
        for fr, to in connections:
            graph[fr].add(to)
            graph[to].add(fr)

        steps, crit_conn = [None] * n, []

        def dfs(fr, parrent):
            min_step = n

            for to in graph[fr]:
                if to == parrent: continue

                if steps[to] is None:
                    steps[to] = steps[fr] + 1
                    dfs(to, fr)

                min_step = min(min_step, steps[to])

            if min_step < steps[fr]:

                steps[fr] = min_step
            else:

                if parrent != -1: crit_conn.append([fr, parrent])

        steps[0] = 0
        dfs(0, -1)

        return crit_conn
