package io.github.zzycreate.example.stream;

import io.github.zzycreate.example.constant.StreamConstant;
import io.github.zzycreate.example.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhenyao.zhao
 * @date 2019/7/18
 */
public class StreamTerminalExample {

    public void collect() {
        List<String> list = Arrays.asList("apple", "orange", "banana", "pear");
        List<String> collectList = list.stream().filter(s -> s.length() > 5).collect(Collectors.toList());//[orange, banana]
        Set<String> collectSet = list.stream().filter(s -> s.length() > 5).collect(Collectors.toSet());// [orange, banana]

        Map<Integer, Item> collectMap1 = StreamConstant.newItems().stream()
                .collect(Collectors.toMap(Item::getCode, Function.identity()));
        Map<Integer, Item> collectMap2 = StreamConstant.newItems().stream()
                .collect(Collectors.toMap(Item::getCode, Function.identity(), (a, b) -> a));

        Stack<String> collect = StreamConstant.newItems().stream()
                .map(Item::getName)
                .collect(Collectors.toCollection(Stack::new));

        Map<Integer, List<Item>> groupingByCollect = StreamConstant.newItems().stream()
                .collect(Collectors.groupingBy(Item::getCode));

        Double average = StreamConstant.newItems().stream()
                .collect(Collectors.averagingInt(Item::getCode));

        IntSummaryStatistics summaryStatistics = StreamConstant.newItems().stream()
                .collect(Collectors.summarizingInt(Item::getCode));// IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}

        String join = list.stream()
                .collect(Collectors.joining(" and ", "The ", " are fruits"));// The apple and orange and banana and pear are fruits

        Collector<Item, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),      // supplier
                        (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),                    // combiner
                        StringJoiner::toString);                     // finisher
        String names = StreamConstant.newItems().stream()
                .collect(personNameCollector);
        System.out.println(names);  // NAME1 | NAME5 | NAME3 | NAME2 | NAME4

    }

    public void toArray() {
        List<String> list = Arrays.asList("apple", "orange", "banana", "pear");
        Object[] objects = list.stream().filter(s -> s.length() > 5).toArray();
        String[] strings = list.stream().filter(s -> s.length() > 5).toArray(String[]::new);
    }

    public void forEach() {
        List<String> list = Arrays.asList("apple", "orange", "banana", "pear");

        // Java 8
        list.stream()
                .filter(s -> s.length() > 5)
                .forEach(System.out::println);
        // Pre-Java 8
        for (String s : list) {
            if (s.length() > 5) {
                System.out.println(s);
            }
        }

        Stream<String> stream = list.stream().filter(s -> s.length() > 5);
        stream.forEach(element -> System.out.println("1: " + element));// ok
        stream.forEach(element -> System.out.println("2: " + element));// java.lang.IllegalStateException: stream has already been operated upon or closed

        Supplier<Stream<String>> streamSupplier = () -> (list.stream().filter(s -> s.length() > 5));
        streamSupplier.get().forEach(element -> System.out.println("1: " + element));
        streamSupplier.get().forEach(element -> System.out.println("2: " + element));

    }

    public void reduce() {
        Supplier<Stream<Integer>> supplier = () -> (Stream.of(1, 2, 3, 4).filter(p -> p > 2));
        List<Integer> result = supplier.get()
                .collect(() -> new ArrayList<>(), (list, item) -> list.add(item), (one, two) -> one.addAll(two));
        System.out.println(result);// [3, 4]
        /* 或者使用方法引用 */
        result = supplier.get().collect(ArrayList::new, List::add, List::addAll);
        System.out.println(result);// [3, 4]
    }

    public void minAndMaxAndCount() {
        Supplier<Stream<Integer>> supplier = () -> (Stream.of(1, 2, 3, 4).filter(p -> p > 2));
        Optional<Integer> min = supplier.get().min(Integer::compareTo);// Optional[3]
        Optional<Integer> max = supplier.get().max(Integer::compareTo);// Optional[4]
        long count = supplier.get().count();// 2
    }

    public void match() {
        boolean b1 = StreamConstant.newItems().stream().map(Item::getCode).anyMatch(i -> i > 3);// true
        boolean b2 = StreamConstant.newItems().stream().map(Item::getCode).anyMatch(i -> i > 5);// false
        boolean b3 = StreamConstant.newItems().stream().map(Item::getCode).allMatch(i -> i > 3);// false
        boolean b4 = StreamConstant.newItems().stream().map(Item::getCode).allMatch(i -> i > 5);// false
        boolean b5 = StreamConstant.newItems().stream().map(Item::getCode).noneMatch(i -> i > 3);// false
        boolean b6 = StreamConstant.newItems().stream().map(Item::getCode).noneMatch(i -> i > 5);// true

    }


    public static void main(String[] args) {
//        new StreamTerminalExample().reduce();
        new StreamTerminalExample().match();
    }

}
