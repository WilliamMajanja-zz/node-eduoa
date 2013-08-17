package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

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
@javax.persistence.Table(name = "oa_certificate_type")
@Entity
public class OaCertificateType extends IdEntity {

    private static final long serialVersionUID = -3742684097525022924L;
    @javax.persistence.Column(name = "type_name")
    private String typeName;
    @javax.persistence.Column(name = "description")
    private String description;
    @javax.persistence.Column(name = "type_level")
    private Integer typeLevel;
    @OneToMany(mappedBy = "oaCertificateTypeByTypeId")
    private List<OaCertificate> oaCertificatesById;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }

    public List<OaCertificate> getOaCertificatesById() {
        return oaCertificatesById;
    }

    public void setOaCertificatesById(List<OaCertificate> oaCertificatesById) {
        this.oaCertificatesById = oaCertificatesById;
    }

}
