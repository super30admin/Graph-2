//time complexity O(2 ^ n) where n is the length of string
//space complexity O(2 ^ n * l) where l is the average length of the child-strings
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if(s == null) return result;
        Queue<String> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        q.add(s);
        set.add(s);
        boolean flag = false;
        while(!q.isEmpty() && !flag){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String str = q.poll();
                if(isValid(str)){
                    flag = true;
                    result.add(str);
                } 
                if(!flag) {
                    //put all the children of str in q
                    for(int j = 0; j < str.length(); j++){
                        if(Character.isLetter(str.charAt(j))) continue;
                        String child = str.substring(0, j) + str.substring(j+1);
                        if(!set.contains(child)){
                            q.add(child);
                            set.add(child);
                        }
                    }
                    
                }
            }
        }
        return result;
    }
    
    private boolean isValid(String s){
        // if(s.length() == 0) return true;
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') count++;
            else if(c == ')') {
                if(count == 0) return false;
                count--;
            };
        }
        return count == 0;
    }
}
