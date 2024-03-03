/*
// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
Using Tarjan's algorithm .
*/

#include<iostream>
#include<vector>

using namespace std;

class Solution {
    vector<vector<int>> adj;
    int time{};
    vector<int> discovery;
    vector<int> lowest;
    vector<vector<int>> res{};
    int graph_size{};
    void dfs(int node, int parent){
        if(discovery.at(node)!=-1) return;
        discovery.at(node) = time;
        lowest.at(node) = time;
        ++time;
        for(int i{};i<graph_size;++i){
            if(i == node || i == parent) continue;
            if(adj.at(node).at(i)){
                if(discovery.at(i) == -1){
                    dfs(i,node);
                    if(lowest.at(i)>discovery.at(node)){
                        res.push_back({i,node});
                    }
                    lowest.at(node) = min(lowest.at(i),lowest.at(node));
                }
                else{
                    lowest.at(node) = min(lowest.at(i),lowest.at(node));
                }
            }    
        }
    }
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        graph_size = n;
        adj.resize(n,vector<int>(n,0));
        discovery.resize(n,-1);
        lowest.resize(n,0);
        for(int i{};i<n;++i) adj.at(i).at(i) = 1;
        for(const auto& v:connections){
            adj.at(v.at(0)).at(v.at(1)) = 1;
            adj.at(v.at(1)).at(v.at(0)) = 1;
        }
        dfs(0,-1);
        return res;

    }
};