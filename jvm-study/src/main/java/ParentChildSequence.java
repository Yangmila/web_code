public class ParentChildSequence {

    private static class P{
        protected int x = 3;

        public P(){
            System.out.println(x);//1
            s();
            System.out.println(x);//2
        }
        protected void s(){
            x = 4;
        }
    }


    //字节码层面上，类加载中，类对象的构造方法为<clinit>字节码指令，收集静态变量，静态代码块
    // 字节码层面上，对象的构造方法为<init>字节码指令，收集成员变量，实例代码块，构造方法
    //执行顺序：父类的<clinit>, 子类的<clinit>, 父类的<init>, 子类的<init>
    private static class C extends P{
        protected int x = 1;//成员变量，没有继承，父类和子类的都是自己的

        public C(){
//            super();//字节码层面，隐的父类构造方法调用
            System.out.println(x);//3
        }
        protected void s(){
            x = 6;
        }
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.x);//4
    }
}


