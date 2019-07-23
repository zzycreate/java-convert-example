package io.github.zzycreate.example.stream;

import com.google.common.base.Supplier;
import io.github.zzycreate.example.constant.StreamConstant;
import io.github.zzycreate.example.model.Item;
import io.github.zzycreate.example.model.ItemDetail;
import io.github.zzycreate.example.stream.tools.DistinctByKey;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhenyao.zhao
 * @date 2019/7/23
 */
public class StreamSpecialExample {

    public void distinctByKey() {
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

        List<Item> distinct = items.stream()
                .filter(DistinctByKey.distinctByKey(Item::getName)).collect(Collectors.toList());

    }

    public void sum() {
        Supplier<Stream<Item>> streamSupplier = () -> StreamConstant.newItems().stream();

        //计算 总金额
        BigDecimal totalMoney = streamSupplier.get().map(Item::getDetail).map(ItemDetail::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:" + totalMoney.setScale(2, RoundingMode.HALF_UP));  //totalMoney:166.50

        //计算 数量
        double sum = streamSupplier.get().mapToDouble(Item::getNumber).sum();
        System.err.println("sum:" + sum);  //sum:16.5
    }

    public static void main(String[] args) {
        new StreamSpecialExample().sum();
    }

}
