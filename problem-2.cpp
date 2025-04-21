// https://leetcode.com/problems/minimize-malware-spread/description/

// Time Complexity: O(n^2 + m log m)
// Space Complexity: O(n)

class Solution {
    public:
        void dfs(vector<vector<int>>& graph, vector<int> &colors, int i, int color){
            colors[i] = color;
            for(int k=0; k<graph.size(); k++){
                if(graph[i][k] == 1 && colors[k] == -1){
                    dfs(graph,colors,k,color);
                }
            }
        }
        int minMalwareSpread(vector<vector<int>>& graph, vector<int>& initial) {
            int n = graph.size();
            vector<int> colors(n,-1);
            int color = 0;
    
            for(int i=0; i<n; i++){
               if(colors[i] == -1){
                    dfs(graph, colors, i, color);
                    color++;
                }
            }
            vector<int> numMal(color,0);
            vector<int> nodes(color,0);
            for(int i=0; i<initial.size(); i++){
                numMal[colors[initial[i]]]++;
            }
            for(int i=0; i<n; i++){
                nodes[colors[i]]++;
            }
            sort(initial.begin(), initial.end());
            int result=initial[0]; int maxSaved=-1;
            for(auto i:initial){
                int c = colors[i];
                if(numMal[c] == 1){
                    if(nodes[c] > maxSaved){
                        maxSaved = nodes[c];
                        result = i;
                    }
                }
            }
            return result;
    
        }
    };