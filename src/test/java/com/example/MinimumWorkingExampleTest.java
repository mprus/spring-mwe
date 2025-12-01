package com.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.BridgeMethodResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class MinimumWorkingExampleTest {

    @Test
    void test() {
        final List<Method> getValueMethods = Arrays.stream(C.class.getMethods())
                .filter(it -> it.getName().equals("getValue")) //get both bridge and non-bridge methods
                .toList();

        //below assertions work with spring-core 6.2.12 but fail with spring-core 6.2.13
        Assertions.assertEquals("public default java.lang.Integer com.oberthur.tsp.apay.MinimumWorkingExampleTest$B.getValue()", BridgeMethodResolver.findBridgedMethod(getValueMethods.get(0)).toString());
        Assertions.assertEquals("public default java.lang.Integer com.oberthur.tsp.apay.MinimumWorkingExampleTest$B.getValue()", BridgeMethodResolver.findBridgedMethod(getValueMethods.get(1)).toString());
    }

    interface A<T> {
        T getValue();
    }
    interface B extends A<Integer> {
        default Integer getValue() {
            return 0;
        }
    }
    static class C implements B {

    }
}
