/* 
    Time Complexity                              :  O(N*N) to convert adjacency matrix to adjacency list
                                                    O(N*N) to traverse the graph using the dfs
    Space Complexity                             :  size of adjanceny list O(N*N) + size of discovery array O(N) + size of lowest array O(N)
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  


class Solution {
private: 
    vector<vector<int>> adjLst;
    int time;
    vector<vector<int>> ans;
    vector<int> discovery;
    vector<int> lowest;
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        time = 0;
        discovery.resize(n,-1);
        lowest.resize(n,0);
        adjLst.resize(n,vector<int>());
        
        // convert adj matrix to adjlst
        for(auto edge : connections) {
            int from = edge[0];
            int to = edge[1];
            adjLst[from].push_back(to);
            adjLst[to].push_back(from);
        }
        
        tarjansAlgo(n);
        return ans;
        
    }
    
    void tarjansAlgo(int n) {
        dfs(0,0);
    }
    
    void dfs(int v, int u) {
        if(discovery[v] != -1) return;
        
        discovery[v] = time;
        lowest[v] = time;
        time++;
        
        for(auto child : adjLst[v]) {
            if(child == u) continue;
            dfs(child, v);
            if(lowest[child] > discovery[v])
                ans.push_back(vector<int>{child,v});
            lowest[v] = min(lowest[child],lowest[v]);
        }
    }
    
};