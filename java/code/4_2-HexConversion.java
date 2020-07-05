class HexConversion{

    /**
     * @desc 十六进制转换表
     */
    public static char[] hexChs = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    /**
     * @desc 十进制转十六进制
     * @param num
     */
    public static char[] toHex(int num){
        char[] arr = new char[8];

        int pos = arr.length;
        while(num!=0){
            // 与运算
            int temp = num & 15;

            // 将取与的结果从高位到低位存储
            arr[--pos] = hexChs[temp];

            // 右移四位，相当于舍弃低4位，求更高位的十六进制
            num = num>>>4;
        }
        return arr;
    }

    /**
     * @desc 二进制转换表
     */
    public static char[] binChs = {'0','1'};
    /**
     * @desc 十进制转换成二进制
     * @param num
     * @return
     */
    public static char[] toBin(int num){
        char [] arr = new char[32];
        int pos = arr.length;
        while(num!=0){
            int temp = num & 1;
            arr[--pos] = binChs[temp];
            num = num>>>1;
        }
        return arr;
    }
}