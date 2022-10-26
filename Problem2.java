import java.util.*;
public class Problem2 {
    
    HashMap<Integer, Integer>nodes= new HashMap<>();
    HashMap<Integer, Integer>infected= new HashMap<>();
        
    public int minMalwareSpread(int[][] graph, int[] initial) {
        
        int grouping[]=new int[graph.length];
        int group=0;
        
        Arrays.fill(grouping,-1);
        
        for( int i=0;i<graph.length;i++){
       
            if(grouping[i]==-1){
               // System.out.println(i+" "+group);
            dfs(graph,grouping,i,group);
            group++;
            
            }
            
            
        }
        
        for( int i=0;i<initial.length;i++){
           // System.out.println(i);
           
            if(!infected.containsKey(grouping[initial[i]])){
         
         infected.put(grouping[initial[i]],0);
     }
         infected.put(grouping[initial[i]],infected.get(grouping[initial[i]])+1);
        }
   
      System.out.println(infected);
       System.out.println(nodes);
        
        int ans=-1;
        int count=Integer.MIN_VALUE;
        
        for(int i=0;i<initial.length;i++){
        
            int grp=grouping[initial[i]];
            
            if(infected.containsKey(grp) && nodes.containsKey(grp)&&infected.get(grp)==1){
           int c = nodes.get(grp)-infected.get(grp);
                
               if(c>count){ count=c; ans=initial[i];}
                else if(c==count){ans=Math.min(ans,initial[i]);}
                
        }
        else if(infected.containsKey(grp) && nodes.containsKey(grp)&&infected.get(grp)>1){
                System.out.println(i);
                if(ans==-1){ ans=initial[i]; }else{ ans=Math.min(ans,initial[i]);}
                
            }
        
        }
        return ans;
    }
    
    public void dfs(int [][]graph, int[] grouping,int i, int group ){
        
        
        
        for(int j=0;j<graph[i].length ;j++){
         //  System.out.println(i+" "+j);
           
     if(graph[i][j]==1 && grouping[j]==-1){
         
         if(!nodes.containsKey(group)){
         
         nodes.put(group,0);
     }
         nodes.put(group,nodes.get(group)+1);
         
         grouping[j]=group;  dfs(graph,grouping, j, group);
     
     
     }
            
            
        }   
        
        
    }
    
    
}
