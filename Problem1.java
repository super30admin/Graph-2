class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> hs = new HashSet<>();

        int groupNumber = 0;
        int groups[] = new int[graph.length];

        for(int i=0;i<graph.length;i++){
            groups[i] = -1;
        }

        for(int i=0;i<graph.length;i++){
            if(groups[i] == -1){
                hs.add(i);
                runDfs(graph, i, groupNumber++, hs, groups);
            }
        }
        int count[] = new int[groupNumber];
        for(int i:groups)
            count[i]++;

        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i:initial)
            hm.put(groups[i],hm.getOrDefault(groups[i],0)+1);

        int minIndex = initial[0];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<initial.length;i++){
            if(hm.get(groups[initial[i]]) == 1 && count[groups[initial[i]]] > max){
                minIndex = initial[i];
                max = count[groups[initial[i]]];
            }
            else if(hm.get(groups[initial[i]]) == 1 && count[groups[initial[i]]] == max)
                minIndex = Math.min(minIndex, initial[i]);
        }

        if(max == Integer.MIN_VALUE)
            for(int i:initial)
                minIndex = Math.min(i, minIndex);

        return minIndex;


    }

    public void runDfs(int graph[][], int index, int groupNumber, HashSet<Integer> hs, int groups[]){
        groups[index] = groupNumber;
        for(int i=0;i<graph.length;i++){
            if(graph[index][i] == 1 && !hs.contains(i)){
                hs.add(i);
                runDfs(graph, i, groupNumber, hs, groups);
            }
        }
    }
}