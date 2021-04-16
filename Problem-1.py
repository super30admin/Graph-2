#Time Complexity :o(n*n) 
#Space Complexity :o(n)
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no

class Solution(object):
    def minMalwareSpread(self, graph, initial):
        """
        :type graph: List[List[int]]
        :type initial: List[int]
        :rtype: int
        """
        n=len(graph)
        colorCode=[-1]*n
        
        i=0
        color=0
        hset=set()
        while i<n:
            if(colorCode[i]==-1):
                #BFS
                queue=collections.deque()
                queue.append(i)
                hset.add(i)
                
                while queue:
                    curr=queue.popleft()
                    colorCode[curr]=color
                    
                    for j in range(n):
                        if(graph[curr][j]==1 and i!=j and j not in hset):
                            queue.append(j)
                            hset.add(j)                    
                color+=1
            i+=1
        group=[0]*color
        
        for i in colorCode:
            group[i]+=1
        
        affectedCount=[0]*color
        for i in initial:
            indx=colorCode[i]
            affectedCount[indx]+=1
        
        maxi=float('-inf')
        node=None
        
        for i in initial:
            indx=colorCode[i]
            cnt=affectedCount[indx]
            if(cnt==1):
                if(group[indx]-affectedCount[indx]>maxi):
                    maxi=group[indx]-affectedCount[indx]
                    node=i
                elif(group[indx]-affectedCount[indx]==maxi):
                    node=min(node,i)
    
        if(maxi==float('-inf')):
            return min(initial)
        
        return node
        
