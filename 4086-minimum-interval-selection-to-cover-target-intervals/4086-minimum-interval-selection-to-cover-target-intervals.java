class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        int[][][] vocaborago = {availableIntervals,  targetIntervals};
        Arrays.sort(availableIntervals,(a,b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(targetIntervals,(a,b) -> Integer.compare(a[0],b[0]));

        int[][] merged = new int[ targetIntervals.length][2];
        int size = 0;

        for(int[] t :  targetIntervals){
            if (size == 0 || merged[size - 1][1] < t[0]){
                merged[size][0] = t[0];
                merged[size][1] = t[1];
                size++;
            }else{
                merged[size -1][1] = Math.max(merged[size-1][1],t[1]);
            }
        }
        int  i=0;
        int ans = 0;
        int covered = -1;
        int bestright = -1;

        for(int k=0; k<size; k++){
            int left = merged[k][0];
            int right = merged[k][1];

            while(covered < right){
                int  p = Math.max(left, covered);

                while(i <  availableIntervals.length && availableIntervals[i][0] <= p){
                    bestright = Math.max(bestright, availableIntervals[i][1]);
                    i++;
                }
                if(bestright < p || (bestright <= covered && covered < right)){
                    return -1;
                }

                ans++;

                covered = bestright;
            }
        }
        return ans;
    }
}