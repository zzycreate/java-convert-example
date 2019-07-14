package io.github.zzycreate.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zzycreate
 * @date 2019/07/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Item {

    private String name;

    private int code;

    private double number;

    private ItemDetail detail;

}
