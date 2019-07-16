package io.github.zzycreate.example.stream;

import io.github.zzycreate.example.model.Item;
import io.github.zzycreate.example.model.ItemDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        items.add(new Item("Name1", 1, 1.1, new ItemDetail(101L, "v1")));
        items.add(new Item("Name5", 5, 5.5, new ItemDetail(505L, "v5")));
        items.add(new Item("Name3", 3, 3.3, new ItemDetail(303L, "v3")));
        items.add(new Item("Name2", 2, 2.2, new ItemDetail(202L, "v2")));
        items.add(new Item("Name4", 4, 4.4, new ItemDetail(404L, "v4")));
        return items;
    }

    public void map() {

        // 转大写
        List<String> stringList = StreamIntermediateExample.newStringList().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()); // [ABC, EFG, HIJ]

        // 数据计算
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream()
                .map(n -> n * n)
                .collect(Collectors.toList()); // [1, 4, 9, 16, 25, 36, 49, 64, 81]

        // 获取对象属性
        List<String> list = StreamIntermediateExample.newItems().stream()
                .map(Item::getDetail).map(ItemDetail::getValue)
                .collect(Collectors.toList()); // [v1, v5, v3, v2, v4]
    }

    public void flatMap() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        // 将集合对象里面的数据拿出来转换为扁平结构
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream()); // [1, 2, 3, 4, 5, 6]
    }

    public void filter() {
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        // 对2取模等于0的是偶数，filter留下数字中的偶数
        Integer[] evens = Stream.of(sixNums)
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new); // [2, 4, 6]
    }

    public void distinct() {
        Integer[] nums = {1, 1, 2, 3, 4, 5, 4, 5, 6};
        Integer[] evens = Stream.of(nums)
                .distinct()
                .toArray(Integer[]::new);// [1, 2, 3, 4, 5, 6]
    }


    public void sorted() {
        List<Integer> list = Arrays.asList(5, 2, 4, 8, 6, 1, 9, 3, 7);
        // sorted() 无参方法为自然排序
        List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());// [1, 2, 3, 4, 5, 6, 7, 8, 9]
        // 使用 Comparator.reverseOrder() 获得一个自然逆序比较器，用于逆序排序
        List<Integer> reverse = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());// [1, 2, 3, 4, 5, 6, 7, 8, 9]
        // 使用 Comparator.comparing() 获取一个自定义比较器，显现自定义对象的排序
        List<Item> codeSorted = StreamIntermediateExample.newItems().stream()
                .sorted(Comparator.comparing(Item::getCode))
                .collect(Collectors.toList());
        // [Item(name=Name1, code=1, number=1.1, detail=ItemDetail(id=101, value=v1)), Item(name=Name2, code=2, number=2.2, detail=ItemDetail(id=202, value=v2)), Item(name=Name3, code=3, number=3.3, detail=ItemDetail(id=303, value=v3)), Item(name=Name4, code=4, number=4.4, detail=ItemDetail(id=404, value=v4)), Item(name=Name5, code=5, number=5.5, detail=ItemDetail(id=505, value=v5))]
        List<Item> codeReverse = StreamIntermediateExample.newItems().stream()
                .sorted(Comparator.comparing(Item::getCode).reversed())
                .collect(Collectors.toList());
        // [Item(name=Name5, code=5, number=5.5, detail=ItemDetail(id=505, value=v5)), Item(name=Name4, code=4, number=4.4, detail=ItemDetail(id=404, value=v4)), Item(name=Name3, code=3, number=3.3, detail=ItemDetail(id=303, value=v3)), Item(name=Name2, code=2, number=2.2, detail=ItemDetail(id=202, value=v2)), Item(name=Name1, code=1, number=1.1, detail=ItemDetail(id=101, value=v1))]
    }

    public void peek() {
        //peek() 接受一个 Consumer 消费者方法，而 map() 接受一个 Function 方法; Consumer 方法返回值是 void，而 Function 方法有返回值，peek 和 map 方法的区别主要在于流处理过程中返回值的不同。
        //peek() 方法是 Intermediate 方法，而 forEach() 方法是 Terminal 方法; 如果 peek 方法后没有 Terminal 方法，则 peek 并不会真正的执行，forEach 方法则会立即执行。
        //forEach 和 peek 都是接受 Consumer 对象的，因此如果在 Stream 流处理的过程中做一些数据操作或者打印操作，选择 peek 方法，该方法还会返回 Stream 流，用于下一步处理; 如果已经是处理的最后一步，则选择 forEach 用于最终执行整个流。

        // [Abc, efG, HiJ] -> [Abc, efG, HiJ]
        List<String> peek = StreamIntermediateExample.newStringList().stream()
                .peek(str -> {
                    if ("Abc".equals(str)) {
                        str = str.toUpperCase();
                    }
                }).collect(Collectors.toList());

        // [Name1, Name5, Name3, Name2, Name4] -> [xxx, Name5, Name3, Name2, Name4]
        List<String> peek1 = StreamIntermediateExample.newItems().stream()
                .peek(item -> {
                    if (item.getCode() == 1) {
                        item.setName("xxx");
                    }
                })
                .map(Item::getName)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        new StreamIntermediateExample().peek();
    }

}
