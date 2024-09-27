package cn.fudges.baseapi.request;

import java.time.LocalDateTime;

/**
 * @author 王平远
 * @since 2024/9/27
 */
public class RequestEntity {

    private Integer pageNum;

    private Integer pageSize;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
