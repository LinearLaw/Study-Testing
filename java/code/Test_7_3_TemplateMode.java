/**
 * @desc 模板方法设计模式
 *      定义功能时，一部分功能是确定的，另一部分不确定
 *      确定的部分使用了不确定部分的内容，
 *      这时候需要将不确定的部分暴露出去，由该类的子类来完成
 * 
 */

 // 定义一个抽象类
abstract class GetTime{
    // 抽象类中有一个不可覆盖的方法，里面使用了抽象方法
    public final void getTime(){
        long start = System.currentTimeMillis();
        runcode();
        long end = System.currentTimeMillis();

        System.out.printIn(end - start);
    }
    // 抽象类中有一个抽象方法
    public abstract void runcode();
}

// 将类继承抽象类，并对runcode进行实现
class SubTime extends GetTime{
    public void runcode(){
        for(int x=0;x<4000;x++){
            System.out.printIn(x);
        }
    }
}


class TemplateDemo{
    public static void main(String[] args){
        SubTime gt = new SubTime();
        gt.getTime();
    }
}