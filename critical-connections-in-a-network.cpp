//Time - O(v+e)
//Space - O(v+e)
class Solution {
public:
    vector<vector<int>> graph;
    vector<vector<int>> result;
    int time = 0;
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        graph = vector<vector<int>>(n,vector<int>());
        buildGraph(graph, connections);
        vector<int> discovery (n,-1);
        vector<int> lowest (n,0);
        dfs(0,0,discovery, lowest);
        return result;
    }
    
    
    void buildGraph(vector<vector<int>>& graph, vector<vector<int>>& connections){
        
        for(auto c: connections){
            graph[c[0]].push_back(c[1]);
            graph[c[1]].push_back(c[0]);
        }
        return;
    }
    
    void dfs(int parent, int child, vector<int>& discovery, vector<int>& lowest){
        if(discovery[child] != -1) return;
        
        discovery[child] = time;
        lowest[child] = time;
        time++;
        
        vector<int> neighbors = graph[child];
        for(auto n:neighbors){
            if(n == parent) continue;
            dfs(child,n,discovery,lowest);
            if(lowest[n]>discovery[child]){
                result.push_back({n,child});
            }
            
            lowest[child] = min(lowest[n],lowest[child]);
        }
        
    }
};