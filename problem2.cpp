// Time Complexity: O(n^2)
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

// Approach: 

// we require colors array. so we perfrom dfs on each node to fill the colors array.
// if all nodes in a component are colored, we increase color++ for the next component and perfrom the same operation
// untill total colors array is filled.

// we need a groups array to have count of nodes for each colors

// we need an infected array to have count if infected nodes of each color 

// we check to see if infectednode count==1 and update the answer.
// during updating our answer, as per the question, we need to return smallest index if there are same answer values. 
// return an answer with greater values in group count.



class Solution {
public:
    int color = 0;
    vector<int>colors;
    int minMalwareSpread(vector<vector<int>>& graph, vector<int>& initial) {
        int n = graph.size();
        colors.resize(n,-1);
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                if(graph[i][j] == 1){
                    dfs(graph,i);
                    color++;
                }
            }
        }
        
        vector<int>groups(color);
        for(int i = 0;i<colors.size();i++)
        {
            int index = colors[i];
            groups[index]++;
        }
        
        vector<int>infected(color);
        for(int i = 0;i<initial.size();i++)
        {
            int index = colors[initial[i]];
            infected[index]++;
        }
        
        int ans = -1;
        for(int i = 0;i<initial.size();i++)
        {
            if(infected[colors[initial[i]]]==1){
                if(ans==-1)
                {
                    ans = initial[i];
                }
                else if(groups[colors[initial[i]]] > groups[colors[ans]]){
                    ans = initial[i];
                }
                else if(groups[colors[initial[i]]] == groups[colors[ans]] && initial[i]<ans){
                    ans = initial[i];
                }
            }
        }
        
        if(ans == -1){
            int mn = initial[0];
            for(int i = 0;i<initial.size();i++){
                mn = min(mn,initial[i]);
            }
            ans = mn;
        }
        return ans;
        
    }
    void dfs(vector<vector<int>>& graph,int u)
    {
        if(colors[u]!=-1) return;
        
        colors[u] = color;
        for(int i = 0;i<graph[u].size();i++)
        {
            if(graph[u][i]==1){
                dfs(graph,i);
            }
        }
    }
};