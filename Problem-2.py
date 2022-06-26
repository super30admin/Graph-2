class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        if not graph or len(graph)==0 or len(graph[0])==0:
            return 0
        
        def dfs(node,graph,colors,color):
            colors[node]=color
            for v in range(len(graph[0])):
                edge=graph[node][v]
                if edge==1 and colors[v]==-1:
                    dfs(v,graph,colors,color)
                    
        #colors list
        color=0
        colors=[-1 for i in range(len(graph[0]))]
        for i in range(len(graph[0])):
            if colors[i]==-1:
                dfs(i,graph,colors,color)
            color+=1       
        
        #group - members count
        group=[0 for i in range(color)]
        for c in colors:
            group[c]+=1
        
        #affected nodes
        affected=[0 for i in range(color)]
        for a in initial:
            affected[colors[a]]+=1
        
        #answer
        result=float('inf')
        for node in initial:
            c=colors[node]
            g=group[c]
            a=affected[c]            
            if a==1:
                if result==float('inf'):
                    result=node
                elif g>group[colors[result]]:
                    result = node
                elif g==group[colors[result]] and node<result:
                    result=node

        if result==float('inf'):
            return min(initial)
        else:
            return result
        