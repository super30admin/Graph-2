// https://leetcode.com/problems/critical-connections-in-a-network/description/

// Time Complexity: O(v+e)
// Space Complexity: O(v+e)


class Solution {
    public:
        unordered_map<int, vector<int>> mp;
        vector<int> discovered;
        vector<int> lowest;
        int time=0;
        vector<vector<int>> res;
    
        vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
            discovered = vector<int> (n, -1);
            lowest = vector<int> (n, -1);
            buildgraph(connections);
            dfs(0,0);
            return res;
        }
        void buildgraph(vector<vector<int>>& connections){
            for(auto edge : connections){
                mp[edge[0]].push_back(edge[1]);
                mp[edge[1]].push_back(edge[0]);
            }
        }
        void dfs(int curr, int parent){
            discovered[curr] = time; lowest[curr] = time;
            time++;
            for(auto ne : mp[curr]){
                if(ne == parent) continue;
                if(discovered[ne] == -1){
                    dfs(ne, curr);
                    if(lowest[ne] > discovered[curr]) res.push_back({curr, ne});
                }
                lowest[curr] = min(lowest[ne], lowest[curr]);
                
            }
        }
    };