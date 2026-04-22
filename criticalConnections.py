class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        adj = dict()
        time=0
        res = []
        discovery = [-1]*n
        lowest = [-1]*n

        for u,v in connections: # O(E)
            if u not in adj:
                adj[u]=[]
            adj[u].append(v)
            if v not in adj:
                adj[v]=[]
            adj[v].append(u)
        
        def helper(node, parent): # O(V+E)
            nonlocal time
            #base
            if discovery[node]!=-1:
                return
            
            #logic
            discovery[node] = lowest[node] = time
            time+=1

            for child in adj[node]:
                if child == parent:
                    continue
                
                helper(child, node)

                if lowest[child] > discovery[node]:
                    res.append([child, node])
                
                lowest[node] = min(lowest[child], lowest[node])
        
        helper(0, 0)
        return res
            
