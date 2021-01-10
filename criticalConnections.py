"""
Time Complexity : O(V+E)
Space Complexity : O(V)

Critical connections are those edge when removed , we can reach the node with other edge. Precisely they are not part of cycle.

The bruteforce here would be to traverse over the connections given and consider that it is removed. Now do DFS the whole list
and check if we are able to reach all the nodes. This is n^2 TC


Optimal solution ---> Tarjan's algorithm. 
we give our nodes a discover time and  lowest time.
once you finish a cycle , you compare the lowest  node with its parents when coming back in recursion and update the lowest value for each node

A connection is critical connection when you see a gap in the lowest value whcih u can find comparing low and discover.

reference:
https://www.youtube.com/watch?v=RYaakWv5m6o&list=PL1j2VgDmgBXqk4d8TXEmQoWOiwlV64LoT&index=4
https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
"""

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        
        self.res=[]
        self.adjlist = [[] for _ in range(n)]

        for src,dst in connections:
            self.adjlist[src].append(dst)
            self.adjlist[dst].append(src)
        
        discover=[-1]*n #visited 
        lowest=[-1]*n
        self.time=0 #track time of dicover and comapre with lowest
        self.dfs(0,0,discover,lowest)
        
        return self.res
    
    def dfs(self,u,v,discover,lowest):
        #u node
        #v is parent of u
        
        #base
        if discover[u]!=-1:
            return 
        
        #logic
        discover[u]=self.time #initially discover and lowest same 
        lowest[u]=self.time
        self.time+=1
        nei=self.adjlist[u]
        for n in nei:
            if n==v:
                continue
            self.dfs(n,u,discover,lowest)
            #find a gap in low values and thats the critical connection
            if(lowest[n]>discover[u]):
                self.res.append([n,u])
            lowest[u] = min(lowest[n], lowest[u])
        
    

      