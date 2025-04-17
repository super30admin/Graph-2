#time complexity o(v+e)
#space complexity o(v+e)
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        
        self.hmap = {}
        self.discover = [-1] * n
        self.lowest = [-1] * n
        self.result = []
        self.time = 0

        self.buildgraph(connections)
        self.dfs(0,0)
        return self.result

    def buildgraph(self,connections: List[List[int]]):
        for inn, out in connections:
            self.hmap.setdefault(inn,[]).append(out)
            self.hmap.setdefault(out, []).append(inn)

    def dfs(self,v:int, u:int):
        #base case
        if self.discover[v] != -1:
            return
        #logic
        self.discover[v] = self.time
        self.lowest[v] = self.time
        self.time += 1
        for ne in self.hmap[v]:
            if ne == u:
                continue
            self.dfs(ne,v)
            if self.lowest[ne] > self.discover[v]:
                self.result.append([ne,v])
            self.lowest[v] = min(self.lowest[ne],self.lowest[v])


        