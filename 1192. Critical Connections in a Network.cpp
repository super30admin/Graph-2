/*
Time: O(v+e)
Space: O(v+e)

Find the bridges -> they are the critical connections


Articulation Point/Bridge Algorithm:

Varibales: Discovery time array, Low time array

1. Start dfs
2. Increment global time variable and initiate discovery time to that time
3. Initiate low to discovery time for any node for which dfs is called
4. Recursively call dfs for each unvisited child and go back to step 2
5. For each call returned for a child, update low to
   low[u] = min(low[u], low[child])
6. If there's any visited child (back edge), update low to
   low[u] = min(low[u], d[child])

7. If for any child v, d[u] <= low[v], u is an Articulation Point  
   If for any child v, d[u] < low[v], u-v is a Bridge

Special care for root node: 
   If the root can call dfs for more than one unvisited child, it is an Articulation Point
*/
class Solution {
public:
    vector<vector<int>> v, bridges;
    vector<int> d,low,vis;
    int t = 0;

    void dfs(int u, int p){
        t++;
        low[u] = d[u] = t;
        vis[u] = 1;
        


        for(auto adj: v[u]){
            if(adj == p)  continue;

            if(!vis[adj]){ // unvisited child -> take low[u]
                dfs(adj, u);

                low[u] = min(low[u], low[adj]);
                if(d[u] < low[adj])  bridges.push_back({u,adj});
            }
            else{ //visited child -> take d[u] because low[u] might be changed
                low[u] = min(low[u], d[adj]);
            }

            //for AP, we need to care for root node seprately, for bridge we don't
        }
    }

    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& conn) {
        v = vector<vector<int>>(n, vector<int>());
        vis = low = d = vector<int>(n, 0);

        for(auto& e: conn){
            v[e[0]].push_back(e[1]);
            v[e[1]].push_back(e[0]);
        }

        dfs(0, -1);
        return bridges;


    }
};
