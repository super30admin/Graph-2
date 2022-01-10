class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
       HashMap<Double, List<String>> map = new HashMap<>();
       
       for(String currStr: strs){
            
           //Our key
           // char[] charArray = currStr.toCharArray();
           // Arrays.sort(charArray);
           // String sortedStr = String.valueOf(charArray);
           double key = prime(currStr);
           //If hashmap contains the key 
           if(!map.containsKey(key)){
               map.put(key, new ArrayList<>());
           }
           map.get(key).add(currStr);
           
       }
       return new ArrayList<>(map.values()); 
    }
    public double prime(String str){
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        double key = 1;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            key = key * prime[c - 'a'];
        }
        return key;
    }
} 