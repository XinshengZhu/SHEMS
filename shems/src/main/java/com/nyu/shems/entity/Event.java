package com.nyu.shems.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Event
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
    private Integer evid;

    private Integer enid;

    private Date occurredTime;

    private String eventLabel;

    private BigDecimal changedValue;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Event other = (Event) that;
        return (this.getEvid() == null ? other.getEvid() == null : this.getEvid().equals(other.getEvid()))
            && (this.getEnid() == null ? other.getEnid() == null : this.getEnid().equals(other.getEnid()))
            && (this.getOccurredTime() == null ? other.getOccurredTime() == null : this.getOccurredTime().equals(other.getOccurredTime()))
            && (this.getEventLabel() == null ? other.getEventLabel() == null : this.getEventLabel().equals(other.getEventLabel()))
            && (this.getChangedValue() == null ? other.getChangedValue() == null : this.getChangedValue().equals(other.getChangedValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEvid() == null) ? 0 : getEvid().hashCode());
        result = prime * result + ((getEnid() == null) ? 0 : getEnid().hashCode());
        result = prime * result + ((getOccurredTime() == null) ? 0 : getOccurredTime().hashCode());
        result = prime * result + ((getEventLabel() == null) ? 0 : getEventLabel().hashCode());
        result = prime * result + ((getChangedValue() == null) ? 0 : getChangedValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", evid=").append(evid);
        sb.append(", enid=").append(enid);
        sb.append(", occurredTime=").append(occurredTime);
        sb.append(", eventLabel=").append(eventLabel);
        sb.append(", changedValue=").append(changedValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}