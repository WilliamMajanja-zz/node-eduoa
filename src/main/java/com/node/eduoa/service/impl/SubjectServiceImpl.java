package com.node.eduoa.service.impl;

import com.node.eduoa.dao.SubjectDao;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaSubject;
import com.node.eduoa.service.SubjectService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {


    @Qualifier("subjectDao")
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public void save(OaSubject subject) {
        subjectDao.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectDao.delete(id);
    }

    @Override
    public OaSubject get(Long id) {
        return subjectDao.findOne(id);
    }

    @Override
    public void update(OaSubject subject) {
        subjectDao.save(subject);
    }

    @Override
    public List<OaSubject> find(Page page, String subjectName) {
        org.springframework.data.domain.Page<OaSubject> springDataPage =
                (org.springframework.data.domain.Page<OaSubject>) subjectDao.findBySubjectNameContaining(subjectName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaSubject> findAll(Page page) {
        org.springframework.data.domain.Page<OaSubject> springDataPage = subjectDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaSubject> findAll() {
        return subjectDao.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
