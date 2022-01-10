class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
       HashMap<String, List<String>> map = new HashMap<>();
       
       for(String currStr: strs){
            
           //Our key
           char[] charArray = currStr.toCharArray();
           Arrays.sort(charArray);
           String sortedStr = String.valueOf(charArray);
           
           //If hashmap contains the key 
           if(!map.containsKey(sortedStr)){
               map.put(sortedStr, new ArrayList<>());
           }
           map.get(sortedStr).add(currStr);
           
       }
       return new ArrayList<>(map.values()); 
    }
} 