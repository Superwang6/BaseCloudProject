package cn.fudges.baseapi.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 王平远
 * @since 2024/9/27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString
public class ResponseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
