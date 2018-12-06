package Tests;

import Annotations.After;
import Annotations.Before;
import Annotations.Test;

public class Test01 {

    public Test01 () {}

    @Before
    public void init () {
        System.out.println(this.getClass().getName() + ".init()");
    }

    @After
    public void finish () {
        System.out.println(this.getClass().getName() + ".finish()");
    }

    @Test(priority = 5)
    public void test01 () {
        System.out.println(this.getClass().getName() + ".test01()");
    }

    @Test(priority = 4)
    public void test02 () {
        System.out.println(this.getClass().getName() + ".test02()");
    }

    @Test(priority = 3)
    public void test03 () {
        System.out.println(this.getClass().getName() + ".test03()");
    }

    @Test(priority = 2)
    public void test04 () {
        System.out.println(this.getClass().getName() + ".test04()");
    }

    @Test(priority = 1)
    public void test05 () {
        System.out.println(this.getClass().getName() + ".test05()");
    }

}
