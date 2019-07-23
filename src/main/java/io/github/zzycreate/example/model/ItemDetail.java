package io.github.zzycreate.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author zzycreate
 * @date 2019/07/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ItemDetail {

    private Long id;

    private String value;

    private BigDecimal money;

}
