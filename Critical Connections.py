# Time Complexity : O(V+E)
# Space Complexity : O(V+E)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.critical_connections = []
        self.arrival_time = [0]*n
        self.minimum_time = [0]*n
        self.visited = [False]*n
        
        self.adj = [[] for i in range(n)]
        for i,j in connections:
            self.adj[i].append(j)
            self.adj[j].append(i)
            
        self.dfs(0,-1,-1)
        return self.critical_connections
        
        
    def dfs(self,node,parent,time):
        self.visited[node] = True
        time+=1
        self.arrival_time[node] = time
        self.minimum_time[node] = time
    
        for nchild in self.adj[node]:
            if nchild != parent: # this is to make sure you are not processing your parent as YOUR child
                if not self.visited[nchild]:
                    self.dfs(nchild,node,time)
                    if self.minimum_time[nchild]> self.arrival_time[node]:
                        self.critical_connections.append([nchild,node])
                        
                self.minimum_time[node] = min(self.minimum_time[node],self.minimum_time[nchild])