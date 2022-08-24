# TC : O(v+e)
# SC : O(v+e)
from collections import defaultdict

class Solution:
     def criticalConnections(self, n, connections):
        # populate the adj list
        graph = defaultdict(set)
        for e1,e2 in connections:
            graph[e1].add(e2)
            graph[e2].add(e1)

        
        steps, crit_conn = [None] * n, []

        def dfs(e1, par):
            min_step = n

            for e2 in graph[e1]:
                if e2 == par: continue

                if steps[e2] is None:
                    steps[e2] = steps[e1] + 1
                    dfs(e2, e1)

                min_step = min(min_step, steps[e2])

            if min_step < steps[e1]:
                steps[e1] = min_step
            else:
                if par != -1: crit_conn.append([e1, par])

        steps[0] = 0
        dfs(0, -1)
        return crit_conn