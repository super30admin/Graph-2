class Solution {
    private int[] discovery;
    private int[] lowest;
    private List<List<Integer>> cricticalConnectionsList;
    private List<List<Integer>> adjacencyList;
    private int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.cricticalConnectionsList = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();

        this.discovery = new int[n];
        this.lowest = new int[n];
        populateAdjacencyList(n, connections);

        Arrays.fill(discovery, -1);
        traverse(0, 0);
        return cricticalConnectionsList;
    }

    private void traverse(int currentNode, int parentNode){

        if(discovery[currentNode] != -1){
            return;
        }

        discovery[currentNode] = time;
        lowest[currentNode] = time;
        time++;

        //traverse over the neighbors that have not been visited yet
        for(int neighbor :  adjacencyList.get(currentNode)){
            if(neighbor == parentNode){
                continue;
            }
            traverse(neighbor, currentNode);
            if(lowest[neighbor] > discovery[currentNode]){
                cricticalConnectionsList.add(Arrays.asList(currentNode, neighbor));
            }
            lowest[currentNode] = Math.min(lowest[currentNode], lowest[neighbor]);
        }

    }

    private void populateAdjacencyList(int n, List<List<Integer>> connections){
        for(int i = 0; i < n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(List<Integer> edge : connections){
            int first = edge.get(0);
            int second = edge.get(1);

            adjacencyList.get(first).add(second);
            adjacencyList.get(second).add(first);
        }
    }
}