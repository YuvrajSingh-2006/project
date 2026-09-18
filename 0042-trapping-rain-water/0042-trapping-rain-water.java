class Solution {
    public int trap(int[] height) {
        int max = 0;
        int idx = 0;
        int sum = 0;
        for(int i=0; i<height.length; i++){
            if (height[i]>=max){
                max =height[i];
                idx = i;
            }
            sum += max - height[i];
        }
        for(int i=idx; i<height.length; i++){
            sum -= max-height[i];
        }
        max = 0;
        for(int i=height.length-1; i>=idx; i--){
            if(height[i]>= max){
                max = height[i];
            }
            sum += max - height[i];
        }
        return sum;
    }
}