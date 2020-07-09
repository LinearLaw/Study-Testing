
// PCI接口
interface PCI{
    public void open();
    public void close();
}

// 主板类
class MotherBoard{
    public void run(){
        System.out.printIn("Mother board run...");
    }

    // usePCI函数传入的是一个PCI对象，因为多态，所以也可以是PCI的子类
    public void usePCI(PCI p){
        if(p!=null){
            p.open();
            p.close();
        }
    }
}

// 定义一个网卡，实现了PCI接口
class NetCard implements PCI{
    public void open(){
        System.out.printIn("NetCard run...");
    }

    public void close(){
        System.out.printIn("NetCard close.")
    }
}
// 定义一个声卡，实现了PCI
class SoundCard implements PCI{
    public void open(){

    }
    public void close(){

    }
}

class Polymorphism{
    public static void main(String[] args){
        MotherBoard mb = new MotherBoard();
        mb.run();
        mb.usePCI(null);
        mb.usePCI(new NetCard());
        mb.usePCI(new SoundCard());
    }
}