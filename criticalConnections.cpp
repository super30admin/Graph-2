//Time - O(V+E) where V & E is the vertices and the edge in the graph
//Space - O(E)
class Solution {
    vector<vector<int>> graph;
    int time = 0;
    vector<int> lowest;
    vector<int> visited;
    vector<vector<int>> result;
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        graph.resize(n);
        lowest.resize(n,-1);
        visited.resize(n,-1);
        for(auto &con : connections){
            graph[con[0]].push_back(con[1]);
            graph[con[1]].push_back(con[0]);
        }
        dfs(0,0);
        return result;
    }
    void dfs(int child,int parent){
        //base
        if(visited[child] != -1) return;
        //logic
        lowest[child] = time;
        visited[child] = time;
        time++;
        for(auto &node : graph[child]){
            if(node == parent) continue;
            dfs(node,child);
            //Lowest indicates the time which the earlier node can reach
            if(lowest[node] > visited[child]) result.push_back({child,node});
            if(lowest[child] > lowest[node]) lowest[child] = lowest[node];  
        }
    }
};