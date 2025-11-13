#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
We assign discovery and lowest time to each node using DFS.
If a neighbor’s lowest time is greater than the current node's discovery time, it's a critical edge.
We collect all such critical edges as part of the result.
*/
class Solution{
public:
    vector<int> discovery;
    vector<int> lowest;
    unordered_map<int, vector<int>> map;
    int time = 0;
    vector<vector<int>> result;
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections){
        discovery.assign(n, -1);
        lowest.resize(n);

        for(int i = 0; i < n; i++){
            map[i] = vector<int>();
        }

        for(auto& conn : connections){
            int u = conn[0];
            int v = conn[1];
            map[u].push_back(v);
            map[v].push_back(u);
        }
        helper(0, 1);
        return result;
    }

    void helper(int u, int v){
        if(discovery[u] != -1) return;

        discovery[u] = lowest[u] = time++;

        for(int ne : map[u]){
            if(ne == v) continue;

            helper(ne, u);
            if(lowest[ne] > discovery[u]){
                result.push_back({ne, u});
            }
            lowest[u] = min(lowest[u], lowest[ne]);
        }
    }
};
