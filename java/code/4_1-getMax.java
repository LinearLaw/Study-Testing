class ArrayTest{

    /**
     * @desc 获取数组最大值
     * @param arr
     * @return
     */
    public static int getMax(int[] arr){
        int max = arr[0];
        for(int x=1;x<arr.length;x++){
            if(arr[x]>max){
                max = arr[x];
            }
        }
        return max;
    }
    public static int getMax2(int[] arr){
        int max = 0;
        for(int x=1;x<arr.length;x++){
            if(arr[x] > arr[max]){
                max = x;
            }

        }
        return arr[max];

    }

    public static void main(String[] args){
        int[] arr = {5,34645,34543,234,346,45,36,456,235};
        int max = getMax(arr);

        System.out.print("max="+max+"\n");
    }
}



