// class Solution {
//     int[] visited ;
//     boolean b;
//     List<Integer> ltemp;
//     HashSet<Integer> hs;
//     int[] temp ;
    
//     public int minMalwareSpread(int[][] graph, int[] initial) {
//         hs = new HashSet<>();
//         temp = new int[initial.length];
//         int n = graph.length;
//         int max = 0;
//         int index = initial[0];
//         visited = new int[n];
//         b=false;
//         ltemp = new ArrayList<>();
//         for (int i : initial) {
//             hs.add(i);
//         }
        
//         Arrays.fill(visited,0);

//         for(int i = 0 ; i<initial.length ; i++){
//             if(visited[initial[i]]==1) continue;

//             else{
//                 dfs(initial[i],graph);
//                 System.out.println(initial[i] + " = " + ltemp.size());
//                 if(b == true){
                    
//                     //do nothing;
//                     b= false; 

//                 }
//                 else{
//                     if(max<ltemp.size()){
//                         max = ltemp.size();
//                         index = initial[i];
//                     }

//                 }
//             }
//             ltemp.clear();
//         }

//         return index;

        
//     }


//     public void dfs(int node, int[][] graph){
//         System.out.println(node);
//         visited[node]=1;
//         ltemp.add(node);
//         List<Integer> l = new ArrayList<>();
//         for(int i=0;i<graph.length;i++){
//             if(graph[node][i] == 1 && node!=i && visited[i]==0){
//                 if(hs.contains(i)){
//                     b=true;
//                 }
//                 ltemp.add(i);
//                 System.out.println(i);
//                 l.add(i);

//             } 
//         }


//         for(int j=0;j<l.size();j++){
//             dfs(l.get(j),graph);

//         }

        

//     }
    
// }

class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] component = new int[n];
        Arrays.fill(component, -1);  
        int compId = 0;

        for (int i = 0; i< n; i++) {
            if (component[i]== -1) {
                dfs(i, graph, component, compId++);
            }
        }
        
        int[] componentSize = new int[compId];  
        int[] malwareCount = new int[compId];   

        for (int i = 0; i <n; i++) {
            componentSize[component[i]]++;
        }
        
        for (int node : initial) {
            malwareCount[component[node]]++;
        }
        

        int result = -1;
        int maxSaved = 0;
        Arrays.sort(initial);  
        
        for (int node : initial) {
            int comp = component[node];
            if (malwareCount[comp] == 1) { 
                if (componentSize[comp] > maxSaved) {
                    maxSaved = componentSize[comp];
                    result = node;
                }
            }
        }

        return result == -1? initial[0] : result;
    }
    
    private void dfs(int node, int[][] graph, int[] component, int compId) {
        component[node] = compId;
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] == 1 && component[i] == -1) {
                dfs(i, graph, component, compId);
            }
        }
    }
}
