package io.github.zzycreate.example.constant;

import io.github.zzycreate.example.model.Item;
import io.github.zzycreate.example.model.ItemDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenyao.zhao
 * @date 2019/7/18
 */
public class StreamConstant {

    public static List<String> newStringList() {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("efG");
        list.add("HiJ");
        return list;
    }

    public static List<Item> newItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Name1", 1, 1.1, new ItemDetail(101L, "v1", new BigDecimal(11.1))));
        items.add(new Item("Name5", 5, 5.5, new ItemDetail(505L, "v5", new BigDecimal(22.2))));
        items.add(new Item("Name3", 3, 3.3, new ItemDetail(303L, "v3", new BigDecimal(33.3))));
        items.add(new Item("Name2", 2, 2.2, new ItemDetail(202L, "v2", new BigDecimal(44.4))));
        items.add(new Item("Name4", 4, 4.4, new ItemDetail(404L, "v4", new BigDecimal(55.5))));
        return items;
    }


}
