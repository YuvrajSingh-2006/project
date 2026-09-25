class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> stack = new Stack<>();
        Stack<Character> ops = new Stack<>();

        Set<String> current = new HashSet<>();
        current.add("");

        for(int i=0; i<expression.length(); i++){
            char ch = expression.charAt(i);

            if(ch == '{'){
                stack.push(current);
                ops.push('{');

                current = new HashSet<>();
                current.add("");
            }else if(ch == ','){
                stack.push(current);
                ops.push(',');

                current = new HashSet<>();
                current.add("");
            }else if(ch == '}'){
                Set<String> union = new HashSet<>(current);
                while(!ops.isEmpty() && ops.peek() == ','){
                    ops.pop();
                    Set<String> previous = stack.pop();
                    union.addAll(previous);
                }

                if(!ops.isEmpty() && ops.peek() == '{'){
                    ops.pop();
                    Set<String> beforeBrace = stack.pop();

                    current = multiply(beforeBrace, union);
                }else{
                    current = union;
                }
            }else{
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                current = multiply(current, letter);
            }
        }
        List<String> answer = new ArrayList<>(current);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> multiply(Set<String> a, Set<String> b){
        Set<String> result = new HashSet<>();

        for(String x : a){
            for(String y : b){
                result.add(x + y);
            }
        }
        return result;
    }
}