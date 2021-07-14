/* Intuition: 

IF IT IS A CYCLE, there is not CRITICAL CONNECTION IN THAT CYCLE.


The idea is that we will keep a track of when we visit all the nodes. The nodes that get discovered at the same earliest moment.
are in a cycle, that means there is no critical edge among them.
Time Complexity: O(V+E)
Space Complexity: O(N)

*/
class Solution {
public:
    map<int,vector<int>>map;
    vector<vector<int>> result;
    int time;
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        time = 0;
        buildGraph(connections);
        vector<int>discovery(n, -1);
        vector<int>earliest(n, -1);
        dfs(0,0, discovery, earliest);
        return result;
    }
    void buildGraph(vector<vector<int>>& connections){
        for( auto edge: connections){
            int incoming = edge[0];
            int outgoing = edge[1];

            if ( map.find(incoming) == map.end()){
                vector<int> nodes; 
                map[incoming] = nodes;
            }
            map[incoming].push_back(outgoing);

            if ( map.find(outgoing) == map.end()){
                vector<int> nodes; 
                map[outgoing] = nodes;
            }
            map[outgoing].push_back(incoming);

        }
    }
    void dfs(int child, int parent, vector<int>&discovery, vector<int>&earliest){
        
        if (discovery[child] != -1 ) return;
        
        discovery[child] = time;
        earliest[child] = time;
        time++;
        for ( auto nei: map[child]){
            if ( nei == parent){
                continue;
            }
            dfs(nei, child, discovery, earliest);
            if ( earliest[nei] > discovery[child]){
                result.push_back({nei, child});
            }
            earliest[child] = min(earliest[nei], earliest[child]);
                        
        }
        
        
        
    }
};