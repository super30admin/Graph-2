// Time complexity: O(V+E)
// Space Complexity: O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


// we make adjList

// we use two arrays named: discovery and lowest
// discovery -> it basically stores the timestamp of its processing 
// lowest-> initially we store the timestamp of its processing. but when we return back from recurrsive stack,
//          we keep on updating the lowest value to min of its value and its neighbour value( the idea is if there is a cycle, then at 
//          some point of time , we end up at last node in the cycle and the next neighbor will be the first node of the cycle, so, if we update
//          with the minimum value,at last every node in the cycle will have lowest equal to the value of the first node processed in that cycle ).

// usually, it always should follow as : the lowest of neigh should always be greater than the discovery but, only incase 
// of a cycle, it violates and has a lesser value.
// so, we just compare the lowest[neighbor]> discovery[curr] and if true, that connection is critical ( weak edge).





class Solution {
public:
    vector<vector<int>>adj;
    vector<int>discovery;
    vector<int>lowest;
    vector<vector<int>>res;
    int time = 0;;
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        adj.resize(n);
        lowest.resize(n);
        discovery.resize(n,-1);
        
        buildGraph(connections);
        
        dfs(0,-1);
        return res;
    }
    void buildGraph(vector<vector<int>>& connections)
    {
        for(auto &v:connections)
        {
            int from = v[0];
            int to = v[1];
            adj[from].push_back(to);
            adj[to].push_back(from);
        }
    }
    void dfs(int u,int v) //u = current, v = its parent
    {
        //base
        if(discovery[u]!=-1) return;
        
        //logic
        
        discovery[u] = time;
        lowest[u] = time;
        time++;
        if(adj[u].empty()) return;
        for(int neigh: adj[u])
        {
            if(neigh == v) continue;
            
            dfs(neigh,u);
            
            lowest[u] = min(lowest[u],lowest[neigh]);
            
            if(lowest[neigh]>discovery[u]){
                res.push_back({neigh,u});
            }
            
        }
    }
};