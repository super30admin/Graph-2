using System;
using System.Collections.Generic;
using System.Text;

namespace DFS_And_BFS
{
    public class CriticalConnections
    {
        List<IList<int>> result;
        List<IList<int>> graph;
        int[] discovery;
        int[] lowest;
        int time;

        public IList<IList<int>> CriticalConnections1(int n, IList<IList<int>> connections)
        {
            if (n == 0) return new List<IList<int>>();

            result = new List<IList<int>>();
            graph = new List<IList<int>>();

            discovery = new int[n];
            lowest = new int[n];
            time = 0;

            Array.Fill(discovery, -1);

            for (int i = 0; i < n; i++)
            {
                graph.Add(new List<int>());
            }


            buildGraph(connections);

            dfs(0, 0);

            return result;
        }

        private void buildGraph(IList<IList<int>> connections)
        {

            foreach (List<int> conn in connections)
            {
                graph[conn[0]].Add(conn[1]);
                graph[conn[1]].Add(conn[0]);
            }
        }

        private void dfs(int v, int u)
        {
            //base
            if (discovery[v] != -1) return;

            //logic
            discovery[v] = time;
            lowest[v] = time;
            time++;

            foreach (int n in graph[v])
            {
                if (n == u) continue;

                dfs(n, v);

                if (lowest[n] > discovery[v])
                {
                    result.Add(new List<int>() { n, v });
                }

                lowest[v] = Math.Min(lowest[v], lowest[n]);
            }
        }
    }
}
