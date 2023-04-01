924. Minimize Malware Spread

Time complexity:O(n*n)

Space complexity:O(n)

class disjointset{
    public:
    vector<int>par, sz;
    disjointset(int n){
        par.resize(n+1);
        sz.resize(n+1, 1);
        for(int i=0; i<=n; i++){
            par[i]=i;
        }
    }
    int findp(int u){
        if(u==par[u]){return u;}
        return par[u]=findp(par[u]);
    }
    void un(int u, int v){
        int pu=findp(u), pv=findp(v);
        if(pu==pv){return;}
        if(sz[pu]<sz[pv]){
            par[pu]=pv;
            sz[pv]+=sz[pu];
        }
        else{
            par[pv]=pu;
            sz[pu]+=sz[pv];
        }
    }
};
class Solution {
public:
    int minMalwareSpread(vector<vector<int>>& graph, vector<int>& initial) {
        int n=graph.size(), ans=-1, maxi=-1;
        disjointset d(n);
        vector<int>v(n, 0);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(graph[i][j]==1){
                    if(d.findp(i)!=d.findp(j)){
                        d.un(i, j);
                    }
                }
            }
        }
        for(int i=0; i<initial.size(); i++){
            v[d.findp(initial[i])]++;
        }
        for(int i=0; i<initial.size(); i++){
            if(v[d.findp(initial[i])]==1 && d.sz[d.findp(initial[i])]>=maxi){
                if(d.sz[d.findp(initial[i])]==maxi){
                    ans=min(ans, initial[i]);
                }
                else{
                    ans=initial[i];
                }
                maxi=d.sz[d.findp(initial[i])];
            }
        }
        if(ans==-1){
            int val=INT_MAX;
            for(int i=0; i<initial.size(); i++){
                val=min(val, initial[i]);
            }
            return val;
        }
        return ans;
    }
};
