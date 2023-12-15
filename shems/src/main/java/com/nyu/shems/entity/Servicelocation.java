package com.nyu.shems.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ServiceLocation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicelocation implements Serializable {
    private Integer slid;

    private Integer cid;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acquiredDate;

    private BigDecimal area;

    private Integer bedroomNum;

    private Integer occupantNum;

    private String street;

    private String unit;

    private String city;

    private String state;

    private String zipcode;

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
        Servicelocation other = (Servicelocation) that;
        return (this.getSlid() == null ? other.getSlid() == null : this.getSlid().equals(other.getSlid()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getAcquiredDate() == null ? other.getAcquiredDate() == null : this.getAcquiredDate().equals(other.getAcquiredDate()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getBedroomNum() == null ? other.getBedroomNum() == null : this.getBedroomNum().equals(other.getBedroomNum()))
            && (this.getOccupantNum() == null ? other.getOccupantNum() == null : this.getOccupantNum().equals(other.getOccupantNum()))
            && (this.getStreet() == null ? other.getStreet() == null : this.getStreet().equals(other.getStreet()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getZipcode() == null ? other.getZipcode() == null : this.getZipcode().equals(other.getZipcode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSlid() == null) ? 0 : getSlid().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getAcquiredDate() == null) ? 0 : getAcquiredDate().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getBedroomNum() == null) ? 0 : getBedroomNum().hashCode());
        result = prime * result + ((getOccupantNum() == null) ? 0 : getOccupantNum().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getZipcode() == null) ? 0 : getZipcode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", slid=").append(slid);
        sb.append(", cid=").append(cid);
        sb.append(", acquiredDate=").append(acquiredDate);
        sb.append(", area=").append(area);
        sb.append(", bedroomNum=").append(bedroomNum);
        sb.append(", occupantNum=").append(occupantNum);
        sb.append(", street=").append(street);
        sb.append(", unit=").append(unit);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}