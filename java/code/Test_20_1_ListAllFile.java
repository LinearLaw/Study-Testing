
/**
 * @desc 列出某一个文件夹下面的所有文件和文件夹，包括子目录
 * 
 * 递归
 * 1、限定条件
 * 2、递归次数，避免内存溢出
 */

import java.io.*;
class ListAllFile {

    public static void main(String[] args){
        File dir = new File("./TestFolder");

        ListAllFile.showDir(dir,0);
    }

    /**
     * @desc 根据传入的level，打印出对应的层级符
     * @param level
     */
    public static String getLevel(int level){
        StringBuilder sb = new StringBuilder();
        sb.append("|--");

        for(int x=0;x<level;x++){
            sb.insert(0,"|");
        }

        return sb.toString();
    }

    /**
     * @desc 传入一个File对象，将所有的文件和文件夹打印出来
     * @param dir
     * @param level
     */
    public static void showDir(File dir,int level)
    {
        String name = dir.getName();

        System.out.print(ListAllFile.getLevel(level)+name+"\n");

        // 在打印之前，让level进行+1
        level++;
        try{
            // 注意：如果这个路径并不存在，那么dir.listFiles就是一个空指针异常
            
            // listFiles可以 返回当前目录下的所有文件和文件夹的数组，元素类型是File
            File[] files = dir.listFiles();

            for(int x=0;x<files.length;x++){

                // 如果files[x]是一个目录，则继续往下遍历这个目录，
                if(files[x].isDirectory()){
                    showDir(files[x], level);
                }else{
                    // 如果是文件，则直接打印出文件名
                    System.out.print(ListAllFile.getLevel(level) + files[x] + "\n");
                }
            }   
        }catch(Exception e){
            // System.out.print(e);

            /**
             * printStackTrace会打印所有报错信息
             * e.toString只会打印报错类型信息
             */
            e.printStackTrace();
        }
    }


}
