package com.nyu.shems.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Enrollment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment implements Serializable {
    private Integer enid;

    private Integer slid;

    private Integer did;

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
        Enrollment other = (Enrollment) that;
        return (this.getEnid() == null ? other.getEnid() == null : this.getEnid().equals(other.getEnid()))
            && (this.getSlid() == null ? other.getSlid() == null : this.getSlid().equals(other.getSlid()))
            && (this.getDid() == null ? other.getDid() == null : this.getDid().equals(other.getDid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEnid() == null) ? 0 : getEnid().hashCode());
        result = prime * result + ((getSlid() == null) ? 0 : getSlid().hashCode());
        result = prime * result + ((getDid() == null) ? 0 : getDid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", enid=").append(enid);
        sb.append(", slid=").append(slid);
        sb.append(", did=").append(did);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}