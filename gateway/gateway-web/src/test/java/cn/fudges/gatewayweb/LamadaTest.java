package cn.fudges.gatewayweb;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 王平远
 * @since 2024/10/25
 */

public class LamadaTest {
    public static void main(String[] args) {
        Function<String,Integer> f = s -> s.length();
        Integer wow = f.apply("wow");
        System.out.println(wow);

        IntFunction<String> f2 = s -> "hello" + s;
        String apply = f2.apply(10);
        System.out.println(apply);

        ToIntFunction<String> f3 = s -> s.length();
        int size = f3.applyAsInt("hahahaha");
        System.out.println(size);

        Function<Integer,Integer> a = TestInter::test;
        Integer apply1 = a.apply(10);
        System.out.println(apply1);

        List<Integer> list = Arrays.asList(1,4,3,2,5);
        list.sort(Comparator.reverseOrder());

        Consumer<Integer> c =  b -> System.out.println(b + 1);
        c.accept(10);

        Predicate<Integer> p1 = aaa -> aaa > 1;
        Predicate<Integer> p2 = bbb -> bbb < 3;
        List<Integer> collect = list.stream().map(ccc -> ccc + 1).filter(p1.and(p2)).toList();
        System.out.println(collect);

    }
}

class TestInter {

    public static Integer test(Integer i) {
        return i+1;
    }
}