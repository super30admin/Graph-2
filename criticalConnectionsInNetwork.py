# // Time Complexity : O(V+E)
# // Space Complexity :O(V+E)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : Line 43 logic issue


# // Your code here along with comments explaining your approach


from collections import defaultdict
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.result = []
        self.hmap = defaultdict(set)        #adjacency list
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.time = 0

        for edge in connections:
            self.hmap[edge[0]].add(edge[1])
            self.hmap[edge[1]].add(edge[0]) 

        self.dfs(0,-1)

        return self.result

    def dfs(self,v,u):                      # curr and parent
        #base
        if self.discovery[v] != -1: return  # already discovered
        
        self.discovery[v] = self.time       # not discovered yet
        self.lowest[v] = self.time
        self.time+=1

        for ne in self.hmap[v]:             # neighbpours in hmap[curr]
            if ne == u: continue            # neibour is parent? continue, dont go back
            
            self.dfs(ne,v)                  # curr node is now parent

            if self.lowest[ne] > self.discovery[v]:       # critical condition found?
                self.result.append([ne,v])

            self.lowest[v] = min(self.lowest[ne],self.lowest[v]) 

