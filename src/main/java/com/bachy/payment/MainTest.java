package com.bachy.payment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {

        List<Foo> list = Arrays.asList(
                new Foo(1, "P1", 300, 400),
                new Foo(2, "P2", 600, 400),
                new Foo(3, "P3", 30, 20),
                new Foo(3, "P3", 70, 20),
                new Foo(1, "P1", 360, 40),
                new Foo(4, "P4", 320, 200),
                new Foo(4, "P4", 500, 900));

        /*List<Optional<Foo>> collect = list.stream()
                .collect(Collectors.groupingBy(foo -> foo.getId()))
                .entrySet()
                .stream()
                .map(x -> x.getValue()
                        .stream()
                        .reduce((a, b) -> new Foo(a.getId(), a.getPid(), a.getCant() + b.getCant(), a.getCant2() + b.getCant2()))
                ).collect(Collectors.toList());
        */

        List<Foo> transform = list.stream()
                .collect(Collectors.groupingBy(foo -> foo.getId()))
                .entrySet().stream()
                .map(e -> e.getValue().stream()
                        .reduce((f1,f2) -> new Foo(f1.getId(),f1.getPid(),f1.getCant() + f2.getCant(),f1.getCant2() + f2.getCant2())))
                .map(f -> f.get())
                .collect(Collectors.toList());

        System.out.println(transform);
    }
}
