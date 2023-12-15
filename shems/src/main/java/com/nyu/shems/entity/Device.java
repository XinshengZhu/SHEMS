package com.nyu.shems.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Device
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {
    private Integer did;

    private Integer dtid;

    private String model;

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
        Device other = (Device) that;
        return (this.getDid() == null ? other.getDid() == null : this.getDid().equals(other.getDid()))
            && (this.getDtid() == null ? other.getDtid() == null : this.getDtid().equals(other.getDtid()))
            && (this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDid() == null) ? 0 : getDid().hashCode());
        result = prime * result + ((getDtid() == null) ? 0 : getDtid().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", did=").append(did);
        sb.append(", dtid=").append(dtid);
        sb.append(", model=").append(model);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}