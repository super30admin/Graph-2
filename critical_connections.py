# Time complexity O(V+E)
# Space O(v)

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        def build_graph(connections, hmap):
            for edge in connections:
                n1 = edge[0]
                n2 = edge[1]
                hmap[n1].append(n2)
                hmap[n2].append(n1)

        def dfs(v,u):
            nonlocal time
            if discovery[v] != -1:
                return
            discovery[v] = time
            lowest[v] = time
            time += 1
            for ne in hmap.get(v):
                if ne == u: continue
                dfs(ne, v)
                if lowest[ne] > discovery[v]:
                    result.append((ne,v))
                lowest[v] = min(lowest[v], lowest[ne])

        result = []
        discovery = [-1] * n
        lowest = [-1] * n
        time = 0
        hmap = defaultdict(list)
        build_graph(connections, hmap)
        dfs(0,0)
        return result


