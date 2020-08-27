import java.io.File;

/**
 * @desc 删除某一个文件夹里面的所有文件。
 * 
 * 逻辑是，先删除所有的文件，删除完所有的当前目录的文件之后，
 * 再将当前文件夹删除。
 */
class RemoveDir {
    
    public static void main(String[] args){
        File dir = new File("./TestFolder");

        if(dir.exists()){

            removeDir(dir);
        }else{
            System.out.println("TestFolder不存在，请先创建一个再测试");
        }
    }

    public static void removeDir(File dir){
        // 获取当前目录下的所有文件和文件夹
        File[] files = dir.listFiles();

        // 遍历files，删除当前目录下的所有文件
        // 如果是文件，就删除文件，
        // 如果是目录，递归调用removeDir，删除目录里面的文件
        for(int x = 0;x<files.length;x++){
            if(files[x].isDirectory()){
                removeDir(files[x]);
            }else{
                String name = files[x].toString();
                System.out.println(name+"::file delete result → "+files[x].delete());
            }
        }

        // 经过上面的for循环，就删除完了当前目录下的所有文件，
        // 所以在这里删除当前目录。
        System.out.println(dir + "::directory delete result → " + dir.delete());
    }

}