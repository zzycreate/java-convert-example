package io.github.zzycreate.example.stream;

import io.github.zzycreate.example.model.Item;
import io.github.zzycreate.example.model.ItemDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 的 Intermediate 操作
 *
 * @author zzycreate
 * @date 2019/07/14
 */
public class StreamIntermediateExample {

    public static List<String> newStringList() {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("efG");
        list.add("HiJ");
        return list;
    }

    public static List<Item> newItems() {
        List<Item> items = new ArrayList<>();
        items.add(Item.builder().name("Name1").code(1).number(1.1)
                .detail(ItemDetail.builder().id(101L).value("v1").build())
                .build());
        items.add(Item.builder().name("Name2").code(2).number(2.2)
                .detail(ItemDetail.builder().id(202L).value("v2").build())
                .build());
        items.add(Item.builder().name("Name3").code(3).number(3.3)
                .detail(ItemDetail.builder().id(303L).value("v3").build())
                .build());
        return items;
    }

    public void map() {

        // 转大写
        List<String> stringList = StreamIntermediateExample.newStringList().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // 数据计算
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        // 获取对象属性
        List<String> list = StreamIntermediateExample.newItems().stream()
                .map(Item::getDetail).map(ItemDetail::getValue)
                .collect(Collectors.toList());
    }

    public void flatMap() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        // 将集合对象里面的数据拿出来转换为扁平结构
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
    }

    public void filter() {
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        // 对2取模等于0的是偶数，filter留下数字中的偶数
        Integer[] evens = Stream.of(sixNums)
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);

    }

    public void distinct() {
        Integer[] nums = {1, 1, 2, 3, 4, 5, 4, 5, 6};
        Integer[] evens = Stream.of(nums)
                .distinct()
                .toArray(Integer[]::new);//[1, 2, 3, 4, 5, 6]
        System.out.println(evens);
    }

}
