class Solution {
    public int numRabbits(int[] answers) {
        int[] count = new int[1001];
        int total =0;

        for(int answer : answers){
            if(count[answer] == 0){
                total += answer + 1;
                count[answer] = answer;
            }else{
                count[answer]--;
            }
        }
        return total;
    }
}