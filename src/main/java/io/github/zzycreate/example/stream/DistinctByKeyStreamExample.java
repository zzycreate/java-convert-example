package io.github.zzycreate.example.stream;

import io.github.zzycreate.example.model.Item;
import io.github.zzycreate.example.model.ItemDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 由于 Stream 提供的方法中 distinct() 可以用于去重，但是只能靠重写 hashCode() 和 equals() 方法实现，而没有便捷的参数条件用于控制
 * 如果需要根据自定义条件进行去重，则需要使用其他方式来实现
 *
 * @author zzycreate
 * @date 2019/07/14
 */
public class DistinctByKeyStreamExample {

    /**
     * 利用 Map 的 key 不能重复的特性进行去重
     *
     * @param keyExtractor 对象转换方式
     * @param <T>          判断条件的处理对象类型
     * @return 判断是否重复，如果不重复，则为 true，重复则为 false
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        // putIfAbsent 如果map中有值，则返回原值，新值也不会放入map中，如果原来没有值，则返回null，本次put的值也会放入map中
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public void example() {
        List<Item> items = new ArrayList<>();
        items.add(Item.builder().name("Name11").code(1).number(1.1)
                .detail(ItemDetail.builder().id(101L).value("v1").build())
                .build());
        items.add(Item.builder().name("Name22").code(2).number(2.2)
                .detail(ItemDetail.builder().id(202L).value("v2").build())
                .build());
        items.add(Item.builder().name("Name33").code(3).number(3.3)
                .detail(ItemDetail.builder().id(303L).value("v3").build())
                .build());
        items.add(Item.builder().name("Name22").code(2).number(2.2)
                .detail(ItemDetail.builder().id(202L).value("v2").build())
                .build());

        List<Item> distinct = items.stream().filter(distinctByKey(Item::getName)).collect(Collectors.toList());
    }

}
