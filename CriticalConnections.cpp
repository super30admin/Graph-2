// Time Complexity - O(V+E)
// Space Complexity - O(V+E) - Is this correct?
// It runs on Leetcode!
// Problems Faced - No!

class Solution {
    vector<vector<int>> answer;
    vector<vector<int>> graph;
    vector<int> discovery;
    vector<int> lowest;
    int time = 0;
    private:
    void buildGraph(vector<vector<int>>& connections, int n){
        for(vector<int> edge : connections){
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }
    }
    
    void dfs(int curr, int p){
        if(discovery[curr] != -1)
            return;
        discovery[curr] = time;
        lowest[curr] = time;
        time++;
        for(int n : graph[curr]){
            if(n == p)
                continue;
            dfs(n, curr);
            if(lowest[n] > discovery[curr])
                answer.push_back({n, curr});
            lowest[curr] = min(lowest[n], lowest[curr]);
        }
    }
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        discovery.resize(n, -1);
        lowest.resize(n, 0);
        graph.resize(n, vector<int>());
        buildGraph(connections, n);
        dfs(0, 0);
        return answer;
    }
};