package Tests;


import Annotations.After;
import Annotations.Before;
import Annotations.Test;

public class Test03 {

    public Test03 () {}

    @Before
    public void init () {
        System.out.println(this.getClass().getName() + ".init()");
    }

    @After
    public void finish () {
        System.out.println(this.getClass().getName() + ".finish()");
    }

    @Test
    public void test01 () {
        System.out.println(this.getClass().getName() + ".test01()");
    }

    @Test
    public void test02 () {
        System.out.println(this.getClass().getName() + ".test02()");
    }

    @Test
    public void test03 () {
        System.out.println(this.getClass().getName() + ".test03()");
    }

    @Test
    public void test04 () {
        System.out.println(this.getClass().getName() + ".test04()");
    }

    @Test
    public void test05 () {
        System.out.println(this.getClass().getName() + ".test05()");
    }

}
