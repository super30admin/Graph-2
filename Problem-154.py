'''
Leetcode - 1192. Critical Connections in a Network - https://leetcode.com/problems/critical-connections-in-a-network/
time complexity - o(n)
space complexity - o(n)

approach - 1) first we maintain discover, lower and time variable at every node, discover -  check visited, lower - check cycles 
           2) if it is a parent then we continue
           3) if a node is not visited and then traverse through that node
           4) if a node is visited and it is not a parent then there exists a cycle,  for that lower is minimum of current node and neigh node
           5) id lower of that node is greater than discovery of neighbour node, there exists a critical path.
           
'''
class Solution:
    global discover
    global lower
    global res
    global graph
    global time
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.res=[]
        self.discover= [-1 for i in range(n)]
        self.lower=[i for i in range(n)]
        self.time=0
        self.graph=[[] for _ in range(n)]
        
        # build graph
        self.build_graph(n,connections)
        # perform DFS
        self.dfs(0,0)
        # return result
        return self.res
    
    def build_graph(self, n,connections):
        for node in connections:
            from1 =node[0]
            to=node[1]
            self.graph[from1].append(to)
            self.graph[to].append(from1)
            
    
    def dfs(self,u,parent):
        self.discover[u]=self.time
        self.lower[u]=self.time
        self.time=self.time+1
        for neigh in self.graph[u]:
            if neigh==parent:
                continue
            if self.discover[neigh]==-1:
                self.dfs(neigh,u)
                if self.lower[neigh]>self.discover[u]:
                    self.res.append([neigh,u])
            self.lower[u]=min(self.lower[u],self.lower[neigh])
        
        
        
   