#Time compelxity: O(m+n) m=no.of vertices, n=no.of wedges
#space compelexity: O(m+n)
#ran on leetcode; yes
# maintain two arrays: "discovery" and "lowestTime", which keep track of the time at which each node is discovered and the lowest time node that can be reached from that node respectively. If the lowest time that can be reached from a node is greater than the time at which the node is discovered, then the edge connecting the two nodes is a critical connection and is added to the result list.
class Solution:
    def dfs(self,u,  prev, discovery,lowestTime,G):
        if(discovery[u] != -1):
            return
        discovery[u] = self.time

        lowestTime[u] = self.time

        self.time+=1
        neighbors=G[u]
        for v in neighbors:
            if(v == prev):
                continue
            self.dfs(v, u, discovery, lowestTime,G)
            if(lowestTime[v] > discovery[u]):
                self.result.append([u,v])

            lowestTime[u] = min(lowestTime[v], lowestTime[u])



    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.time=0
        self.result=[]
        G={}
        discovery=[-1]*n
        lowest_time=[0]*n
        for node in connections:
            if(node[0] in G):
                G[node[0]].append(node[1])
            else:
                G[node[0]]=[node[1]]
            if(node[1] in G):
                G[node[1]].append(node[0])
            else:
                G[node[1]]=[node[0]]
        self.dfs(0,0,discovery, lowest_time,G)
        return self.result
        

