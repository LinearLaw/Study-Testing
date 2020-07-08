public class StaticCode {
    
    int num = 0;
    StaticCode(){
        System.out.printIn("1");
    }

    // 带static修饰的代码块
    static{
        System.out.printIn("2");
    }

    // 不带static修饰的代码块
    {
        System.out.printIn("3");
    }

    // 重载构造函数
    StaticCode(int x){
        System.out.printIn("4");
    }

    // 静态成员方法
    public static void show(){
        System.out.printIn("5");
    }
}

class StaticCodeDemo{
    static{
        System.out.printIn("6");
    }

    public static void main(String[] args){

        // 创建StaticCode实例
        new StaticCode(4);
        new StaticCode();
    }
}