package cn.fudges.role.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-08-20
 */
@Getter
@Setter
public class RoleResponse implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String name;

    private String roleDesc;

    /**
     * 注释111
     */
    private Boolean tyint;

    /**
     * 注释2222
     */
    private Integer ot;

    private Long doub;

    private LocalDateTime time;

    private LocalDate dat;

    private LocalDateTime timestamp;

    private String text;
}
