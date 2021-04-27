package main.java.linear;

public class Ticket implements Runnable {
    private int ticket = 100;
    Object lock = new Object();

    // 实现Runnable，并实现run方法，该函数即可作为一个线程来运行。
    public void run(){
        while(true){
            sellTicket();
        }
    }
    /*
        使用了synchronized修饰之后，该函数就变成了一个同步函数
        仅有当前函数执行完成，CPU才会释放资源，运行其他线程。

        synchronized修饰普通函数，默认锁是this
            修饰static函数，默认锁是当前class
     */
    public synchronized void sellTicket(){
        if(ticket > 0){
            try{
                Thread.sleep(100);

            }catch(InterruptedException e){
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();
            System.out.println(name + "正在卖： " + ticket--);
        }

    }
}


