package Tests;

import Annotations.After;
import Annotations.Before;
import Annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TestController<T> {
    /**
     * Класс теста
     */
    private Class clazz;
    /**
     * Метод начальной обработки тестов
     */
    private Method init;
    /**
     * Метод финальной обработки тестов
     */
    private Method finish;
    /**
     * Массив методов тестов. Использован TreeMap,
     * в котором ключи - это целые числа, равные значению priority.
     * TreeMap автоматически сортирует значения по возрастанию ключа.
     * В качестве значений массива TreeMap выбран ArrayList, который содержит
     * методы с равным приоритетом в произвольном порядке.
     */
    private TreeMap<Integer, ArrayList<Method>> invokablesTests;

    public TestController() {
        this.invokablesTests = new TreeMap<>();
        this.init = null;
        this.finish = null;
    }

    public static void test(Class clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestController tc = new TestController();
        tc.clazz = clazz;
        tc.test();
    }

    public static void test(String clazz) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestController tc = new TestController();
        tc.clazz = Class.forName(clazz);
        tc.test();
    }

    @SuppressWarnings("unchecked")
    private void test() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        this.parseClass();
        System.out.println(String.format("------ run tests %s -----", this.clazz.getName()));
        Constructor<T> c = this.clazz.getConstructor();
        T test;
        for (Map.Entry<Integer, ArrayList<Method>> entry : this.invokablesTests.entrySet()) {
            for (Method m : entry.getValue()) {
                test = c.newInstance();
                this.init.invoke(test);
                m.invoke(test);
                this.finish.invoke(test);
            }
        }
        System.out.println(String.format("------ tests %s passed -----", this.clazz.getName()));
        System.out.println();

    }

    private void parseClass() {
        Method[] methods = this.clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
                if (this.init != null)
                    throw new RuntimeException("Multiple @Before Exception.");
                this.init = m;
            }
            if (m.isAnnotationPresent(After.class)) {
                if (this.finish != null)
                    throw new RuntimeException("Multiple @After Exception.");
                this.finish = m;
            }
            if (m.isAnnotationPresent(Test.class)) {
                Test t = m.getAnnotation(Test.class);
                int priority = t.priority();
                ArrayList<Method> arl;
                if (!this.invokablesTests.containsKey(priority)) {
                    arl = new ArrayList<>();
                    arl.add(m);
                    this.invokablesTests.put(priority, arl);
                } else {
                    arl = this.invokablesTests.get(priority);
                    arl.add(m);
                }
            }
        }
    }

}
