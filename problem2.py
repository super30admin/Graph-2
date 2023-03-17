#time O(N*N)
#space O(N)
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        g=collections.defaultdict(set)
        for i in connections:
            g[i[0]].add(i[1])
            g[i[1]].add(i[0])
        # print(g)
        endpoints = [i for i in range(n) if len(g[i]) == 1]
        # print(endpoints)
        ans = []
        while endpoints:
            visit=set()
            for i in endpoints:
                if not g[i]:
                    continue
                k=g[i].pop()
                ans.append([i, k])
                g[k].discard(i)
                if len(g[k]) == 1:
                    visit.add(k)
            endpoints=visit
        # print(ans)
        return ans