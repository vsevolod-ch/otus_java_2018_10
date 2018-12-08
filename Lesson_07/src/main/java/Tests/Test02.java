package Tests;


import TestFramework.Annotations.After;
import TestFramework.Annotations.Before;
import TestFramework.Annotations.Test;

public class Test02 {

    public Test02() {
    }

    @Before
    public void init() {
        System.out.println(this.getClass().getName() + ".init()");
    }

    @After
    public void finish() {
        System.out.println(this.getClass().getName() + ".finish()");
    }

    @Test(priority = 2)
    public void test01() {
        System.out.println(this.getClass().getName() + ".test01()");
    }

    @Test(priority = 2)
    public void test02() {
        throw new RuntimeException("Test02 failed.");
    }

    @Test(priority = 2)
    public void test03() {
        System.out.println(this.getClass().getName() + ".test03()");
    }

    @Test
    public void test04() {
        System.out.println(this.getClass().getName() + ".test04()");
    }

    @Test
    public void test05() {
        System.out.println(this.getClass().getName() + ".test05()");
    }

}
