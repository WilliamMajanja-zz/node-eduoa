package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "oa_dictionary")
@Entity
public class OaDictionary extends IdEntity {

    private static final long serialVersionUID = 6182010979348600175L;
    @Column(name = "dict_name")
    private String dictName;
    @Column(name = "dict_key")
    private String dictKey;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private OaDictionary oaDictionaryByParentId;
    @OneToMany(mappedBy = "oaDictionaryByParentId")
    private List<OaDictionary> oaDictionariesById;
    @OneToMany(mappedBy = "oaDictionaryByDictionaryId")
    private List<OaDictionaryValue> oaDictionaryValuesById;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public OaDictionary getOaDictionaryByParentId() {
        return oaDictionaryByParentId;
    }

    public void setOaDictionaryByParentId(OaDictionary oaDictionaryByParentId) {
        this.oaDictionaryByParentId = oaDictionaryByParentId;
    }

    public List<OaDictionary> getOaDictionariesById() {
        return oaDictionariesById;
    }

    public void setOaDictionariesById(List<OaDictionary> oaDictionariesById) {
        this.oaDictionariesById = oaDictionariesById;
    }

    public List<OaDictionaryValue> getOaDictionaryValuesById() {
        return oaDictionaryValuesById;
    }

    public void setOaDictionaryValuesById(List<OaDictionaryValue> oaDictionaryValuesById) {
        this.oaDictionaryValuesById = oaDictionaryValuesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OaDictionary that = (OaDictionary) o;

        if (id != that.id) return false;
        if (dictKey != null ? !dictKey.equals(that.dictKey) : that.dictKey != null) return false;
        if (dictName != null ? !dictName.equals(that.dictName) : that.dictName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dictName != null ? dictName.hashCode() : 0);
        result = 31 * result + (dictKey != null ? dictKey.hashCode() : 0);
        return result;
    }


}
