package cn.fudges.baseapi.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 王平远
 * @since 2024/9/27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString
public class RequestEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer pageNum;

    private Integer pageSize;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
