/*
// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
*/
#include<iostream>
#include<vector>
#include<unordered_map>
#include<queue>

using namespace std;

//second attempt 

class Solution {
    vector<int> colors{};
    int num_nodes{};
    void bfs(int node,int idx,vector<vector<int>>& graph){
        if(colors.at(node)!=-1) return;
        queue<int> q{};
        q.push(node);
        while(!q.empty()){
            int temp = q.front();
            q.pop();
            colors.at(temp) = idx;
            for(int i{};i<num_nodes;++i){
                if(colors.at(i) == -1 && graph.at(temp).at(i) == 1){ // check if untouched and is connected component
                    q.push(i);
                }
            }
        }
    }

    void display(vector<int> vec){
        for(int v:vec) cout<<v<<" ";
        cout<<endl;
    }
public:
    int minMalwareSpread(vector<vector<int>>& graph, vector<int>& initial) {
        num_nodes = graph.size();
        colors.resize(num_nodes,-1);
        //assign groups to each node
        int idx{};
        for(int i{};i<num_nodes;++i){
            bfs(i,idx,graph);
            ++idx;
        }
        //number of elements in each group
        vector<int> groups(idx,0);
        for(int i{};i<num_nodes;++i){
            groups.at(colors.at(i))++;
        }
        //number of infected in each group
        vector<int> groups_infected(idx,0);
        for(int i:initial){
            groups_infected.at(colors.at(i))++;
        }
        //either output the group which has just one infected node in the set
        // or the group which has least number no of infected node with least node number
        //display(colors);
        //display(groups);
        //display(groups_infected);
        int res{INT_MAX};
        for(int v:initial){
            //cout<<"node "<<v<<" ";
            int group = colors.at(v);
            int num_ele = groups_infected.at(group);
            if(num_ele == 1){
                if(res == INT_MAX) res = v;
                else if(groups.at(group)>groups.at(colors.at(res))) res = v;
                else if(groups.at(group) == groups.at(colors.at(res)) && v<res) res = v;
            }
            //cout<<"res "<<res<<endl;
        }
        if(res == INT_MAX){
            for(int v:initial) res = min(res,v);
        }
        return res;
    }
};


//first attempt not working 

class Solution {
public:
    int minMalwareSpread(vector<vector<int>>& graph, vector<int>& initial) {
        int initial_size = initial.size();
        int graph_size = graph.size();
        unordered_map<int,vector<bool>> umap{};
        for(const auto v:initial){
            umap[v] = vector<bool>(graph_size,false);
            //umap[v].at(v) = true;
            queue<int> q{};
            q.push(v);
            while(!q.empty()){
                int node = q.front();
                q.pop();
                umap[v].at(node) = true;
                for(int i{};i<graph_size;++i){
                    if(graph.at(v).at(i) && !umap[v].at(node)){ // if the node is connected and not visited
                        q.push(i);
                    }
                }
            }

        }
        int min_node{INT_MAX};
        int min_val{INT_MAX};
        for(const int v:initial){
            vector<bool> temp(graph_size,false);
            int cnt{};
            for(int i:initial){
                if(i==v) continue;
                for(int j{};j<graph_size;++j){
                    if(umap[i].at(j) && !temp.at(j)){
                        temp.at(j) = true;
                        cnt++;
                    }
                }
            }
            cout<<v<<" "<<cnt<<endl;
            if(min_val>cnt){
                min_val = cnt;
                min_node = v;
            }
        }
        return min_node;
    }
};
