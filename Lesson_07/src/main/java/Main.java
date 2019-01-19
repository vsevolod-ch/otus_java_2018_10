import TestFramework.TestController;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
//            TestController.test(Test01.class);
//            TestFramework.TestFramework.TestController.test(Test02WithRuntimeException.class);
//            TestFramework.TestFramework.TestController.test(Test03.class);
//            TestController.test("Tests.Test01");
            TestController.testPackageThroughReflections("Tests");
//            TestController.testPackageThroughClassloader("Tests");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
