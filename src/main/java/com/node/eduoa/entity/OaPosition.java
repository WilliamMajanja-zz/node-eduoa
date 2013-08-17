package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_position")
@Entity
public class OaPosition extends IdEntity {

    private static final long serialVersionUID = 6603604563249891863L;

    @NotBlank
    @Length(min = 1, max = 32)
    @Column(name = "position_name", nullable = false, length = 32)
    private String positionName;

    @Length(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "oaPositionByPositionId")
    private List<OaTeacherInfo> oaTeacherInfosById;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OaTeacherInfo> getOaTeacherInfosById() {
        return oaTeacherInfosById;
    }

    public void setOaTeacherInfosById(List<OaTeacherInfo> oaTeacherInfosById) {
        this.oaTeacherInfosById = oaTeacherInfosById;
    }


}
