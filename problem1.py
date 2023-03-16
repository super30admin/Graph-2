#time O(N*N)
#space O(N)
class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:

        initial.sort()
        ans = initial[0]; max = 0
        
        for init in initial:
            stack = [init]
            total = 0
            seen = set()
            alert = 0

            while len(stack) > 0:
                popped = stack.pop()
                for connection in range(len(graph[popped])):
                    if graph[popped][connection] == 1 and connection not in seen:
                        if connection in initial and popped != connection:
                            alert = 1
                        total = total + 1
                        stack.append(connection)
                        seen.add(connection)

            if total > max and alert == 0:
                max = total
                ans = init
        return ans