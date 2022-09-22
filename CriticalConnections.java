class Solution {int id=0;
    List<List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    HashMap<Integer, List<Integer>> graph= new HashMap<>();
        
        int ids[] = new int [n];
        int lin[] = new int [n];
        
        Arrays.fill(ids,-1);
     
        for(List <Integer> l : connections){
            
            int key=l.get(0);
            int val=l.get(1);
            
            if(!graph.containsKey(key)){
                
                graph.put(key,new ArrayList<Integer>());
            }
            
            if(!graph.containsKey(val)){
                
                graph.put(val,new ArrayList<Integer>());
            }
            graph.get(key).add(val);
            graph.get(val).add(key);    
        
       
    }
         dfs(graph,ids,lin,0,0);
            return ans;
}
    
 private void dfs(HashMap<Integer, List<Integer>> graph,int ids[] ,int lin[], int parent, int node){
     
     if(ids[node]!=-1){return;}
     
     ids[node]=id;
     lin[node]=id;
     id++;
     
     for( int child :graph.get(node)){
         
         if(child==parent){continue;}
         
         dfs(graph,ids,lin,node,child);
         
         if(lin[child]>ids[node]){
             ans.add(Arrays.asList(child,node));    
         }
         
         lin[node]=Math.min(lin[child],lin[node]);
         
     }
     
     
     
 }   
    
    
}
