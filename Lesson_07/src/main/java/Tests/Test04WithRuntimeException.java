package Tests;


import TestFramework.Annotations.After;
import TestFramework.Annotations.Before;
import TestFramework.Annotations.Test;

public class Test04WithRuntimeException {

    public Test04WithRuntimeException () {}

    @Before
    public void init () {
        System.out.println(this.getClass().getName() + ".init()");
    }

    @After
    public void finish () {
        System.out.println(this.getClass().getName() + ".finish()");
    }

    @Before
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
