package io.github.zzycreate.example.stream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static io.github.zzycreate.example.constant.FileConstant.FILE_NAME_INPUT;

/**
 * Stream 流示例
 *
 * @author zzycreate
 * @date 2019/07/14
 */
public class StreamExample {

    public void create() throws FileNotFoundException {
        String[] array = new String[]{"A", "B", "C", "D", "E"};
        List<String> list = new ArrayList<>(Arrays.asList(array));
        Set<String> set = new HashSet<>(list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_NAME_INPUT), StandardCharsets.UTF_8));

        // 集合对象 -> Stream
        Stream<String> listStream = list.stream();
        Stream<String> listParallelStream = list.parallelStream();
        Stream<String> setStream = set.stream();
        Stream<String> setParallelStream = set.parallelStream();

        // 数组对象 -> Stream
        Stream<String> arrayStream = Arrays.stream(array);
        Stream<String> arrayStream1 = Stream.of(array);

        // BufferedReader -> Stream
        Stream<String> lines = reader.lines();

        // 流对象提供的构造方法
        IntStream intStream = IntStream.range(1, 4);
        DoubleStream doubleStream = DoubleStream.builder().add(1.1).add(2.1).add(3.1).add(4.1).build();
        LongStream longStream = LongStream.of(1L, 2L, 3L, 4L);

     }



}
