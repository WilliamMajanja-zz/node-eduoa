package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaContact;
import com.node.eduoa.entity.OaScore;
import com.node.eduoa.entity.OaStudent;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 上午8:31
 * To change this template use File | Settings | File Templates.
 */
public class ScoreModel implements Serializable {
    private static final long serialVersionUID = -2358539544538371627L;

    private OaScore score;
    private GradeClassModel classModel;

    public GradeClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(GradeClassModel classModel) {
        this.classModel = classModel;
    }

    public OaScore getScore() {
        return score;
    }

    public void setScore(OaScore score) {
        this.score = score;
    }
}
