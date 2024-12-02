#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Oct 24 11:07:37 2019

@author: tanvirkaur
"""
# tarzan algorithm is used
# we maintain two array : discovery and lowest time
# discovery is used to keep track whether the node is visited or not
# lowesttime is used to maintain the timestamp of that node
# we use dfs to iterarte through the nodes and update the dictionary and lowest time
# there are three cases 1. the next node is the parent node: we continiue
#2. the node is visited. #3 the node is not visited.
# in Pyton graph is implemented as a dictionarty
# time complexity = O(n)
# Space complexity = O(n)
# Leet Code Acceptance = Yes
from collections import defaultdict
class Solution(object):
    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        result = []
        graph = defaultdict(list)
        for edge in connections:
            graph[edge[0]].append(edge[1])
            graph[edge[1]].append(edge[0])
        discovery = [ -1 for i in range(n)]
        lowestTime = [ 0 for i in range(n)]
        self.time = 0
        result = []
        
        
        def dfs( u , prev, discovery, lowestTime):
            if discovery[u]== -1:
                discovery[u] = self.time
                lowestTime[u] = self.time
                self.time += 1
                vertices = graph[u]
                for v in vertices:
                    if(v==prev):
                        continue
                    if discovery[v] == -1:
                        dfs(v,u, discovery, lowestTime)
                        lowestTime[u] = min(lowestTime[u],lowestTime[v])
                        if lowestTime[v] > discovery[u]:
                            result.append((u,v))
                    else:
                        lowestTime[u] = min(lowestTime[u],lowestTime[v])
        dfs(0,0, discovery,lowestTime)
        return result
