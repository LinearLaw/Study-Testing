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
    
    /**
     * @desc 选择排序
     * @param arr
     */
    public static void selectSort(int arr[]){
        for(int x=0;x<arr.length-1;x++){
            for(int y = x+1;y<arr.length;y++){
                if(arr[x] > arr[y]){
                    swap(arr,x,y);
                }
            }
        }
    }
    /**
     * @desc 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        for(int i = 0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j] < arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
    /**
     * @desc 交换两个数组元素的位置
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr,int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args){
        int[] arr = {5,34645,34543,234,346,45,36,456,235};
        int max = getMax(arr);

        System.out.print("max="+max+"\n");
    }
}



