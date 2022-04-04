"""
time:O(connections * V (dfs))
space: V

low[node]:
  this gets the min(level) of neighbors, which actually means "how close to the supposed root can we can get if we keep traversing forward from that node".

As for "edge(u,v) where DFN(u)<LOW(v)":
  This means that:
    traversing down the tree from a node at level 2 will ONLY reach level 3+. 
    -> this implies that this node is the ONLY link from level 2 to level 3.
    -> implies u to v is a critical connection
  A more intuitive explanation of the above:
    That statement means: "can we traverse along any of a nodes neighbors to get back to the same level as node?
     bc if we can, there is a cycle and we can safely remove this node.
     If not, then the inverse is true, and u -> v is a critical connection"
"""

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        g = collections.defaultdict(list)
        for u, v in connections:
            g[u].append(v)
            g[v].append(u)
            
        N = len(connections)
        lev = [None] * N
        low = [None] * N
        
        def dfs(node, par, level):
            # already visited
            if lev[node] is not None:
                return 
            
            lev[node] = low[node] = level
            for nei in g[node]:
                if not lev[nei]:
                    dfs(nei, node, level + 1)
            
            # minimal level in the neignbors, exclude the parent
            cur = min([level] + [low[nei] for nei in g[node] if nei != par])    
            low[node] = cur
            # print(low, lev)
        
        dfs(0, None, 0)
        
        ans = []
        for u, v in connections:
            if low[u] > lev[v] or low[v] > lev[u]:
                ans.append([u, v])
        return ans