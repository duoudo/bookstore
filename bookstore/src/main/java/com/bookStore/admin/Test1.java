package com.bookStore.admin;

public class Test1 {
    public static void main(String[] args){
        //ava中基本类型的包装类的大部分(6种)都实现了常量池技术，这些类是Byte,Short,Integer,Long,Character,Boolean
        //5种整形的包装类Byte,Short,Integer,Long,Character的对象，在值小于127等于时可以使用常量池，
        // 也即对象不负责创建和管理大于127的这些类的对象。
        Integer i1=127;
        Integer i2=127;
        System.out.println(i1==i2); //输出true
        //值大于127时，不会从常量池中取对象
        Integer i3=128;
        Integer i4=128;
        System.out.println(i3==i4); //输出false
        //Boolean类也实现了常量池技术
        Boolean bool1=true;
        Boolean bool2=true;
        System.out.println(bool1==bool2); //输出true
        //浮点类型的包装类没有实现常量池技术
        Double d1=1.0;
        Double d2=1.0;
        System.out.println(d1==d2); //输出false
    }
}
