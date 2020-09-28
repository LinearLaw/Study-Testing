

class Parent{
    Parent(){}

    public void show(){
        System.out.println("parent show");
    }
    
}


class Children extends Parent{
    Children(){
        super();
    }

    public void invokeParentShow(){
        super.show();
    }

    public void show(){
        System.out.println("children show");
    }
    
}

class Test_43_AB{
    public static void main(String[] args){
        Children child = new Children();
        child.show();
        child.invokeParentShow();
    }
}