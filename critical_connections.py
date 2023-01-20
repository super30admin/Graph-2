class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        ## T.C = O(v+e)
        ## S.C = O(v+e)

        graph = [[]*n for i in range(n)]
        discovery = [-1]*n
        lowest = [0]*n
        time = [0]
        res = []

        for i, j in connections:
            graph[i].append(j)
            graph[j].append(i)


        def dfs(v, u):
            ## base case
            if discovery[v] != -1:
                return

            discovery[v] = time[0]
            lowest[v] = time[0]
            time[0] += 1

            for i in range(len(graph[v])):
                node = graph[v][i]
                if node == u:
                    continue
                dfs(node, v)
                
                if lowest[node] > discovery[v]:
                    res.append([v, node])
                
                lowest[v] = min(lowest[v], lowest[node])
            
            #print(res)
        
        dfs(0, -1)
        return res


