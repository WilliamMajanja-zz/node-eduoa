package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_dictionary_value")
@Entity
public class OaDictionaryValue extends IdEntity {


    private static final long serialVersionUID = 2373339588870267444L;
    @javax.persistence.Column(name = "dict_label")
    private String dictLabel;
    @javax.persistence.Column(name = "dict_value")
    private String dictValue;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "dictionary_id", referencedColumnName = "id")
    private OaDictionary oaDictionaryByDictionaryId;

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public OaDictionary getOaDictionaryByDictionaryId() {
        return oaDictionaryByDictionaryId;
    }

    public void setOaDictionaryByDictionaryId(OaDictionary oaDictionaryByDictionaryId) {
        this.oaDictionaryByDictionaryId = oaDictionaryByDictionaryId;
    }


}
