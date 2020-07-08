
/**
 * @desc 单例设计模式
 *      提供一个唯一的对象，所有人都共享这一个对象，而不是各自创建各自单独的对象
 * 
 *      注意：目前没有考虑多线程的情况。
 */
public class Single {
    
    private Single(){

    }
    // 创建一个静态成员实例对象
    private static Single s = new Single();

    // 对外提供这个对象
    public static Single getInstance(){
        return s;
    }

}

class SingleDemo{
    public static void main(String[] args){
        Single s1 = Single.getInstance();
        Single s2 = Single.getInstance();

        System.out.printIn(s1==s2);    // 返回了一个true

    }
}


/**
 * @desc 单例设计模式 - 饿汉式
 *      加载类时不进行创建实例，在调用时才创建
 */
class SingleObj{
    private static SingleObj s = null;
    private SingleObj(){

    }

    public static SingleObj getInstance(){
        if(s == null){
            synchronized(Single.class){
                if(s == null){
                    s = new SingleObj();
                }
            }
        }
        return s;
    } 

 }