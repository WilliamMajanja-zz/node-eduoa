package com.node.eduoa.service.impl;

import com.node.eduoa.dao.CertificateTypeDAO;
import com.node.eduoa.entity.OaCertificateType;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.service.CertificateTypeService;
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
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class CertificateTypeServiceImpl implements CertificateTypeService {


    @Qualifier("certificateTypeDAO")
    @Autowired
    private CertificateTypeDAO certificateTypeDAO;

    @Override
    public void save(OaCertificateType certificateType) {
        certificateTypeDAO.save(certificateType);
    }

    @Override
    public void delete(Long id) {
        certificateTypeDAO.delete(id);
    }

    @Override
    public OaCertificateType get(Long id) {
        return certificateTypeDAO.findOne(id);
    }

    @Override
    public void update(OaCertificateType certificateType) {
        certificateTypeDAO.save(certificateType);
    }

    @Override
    public List<OaCertificateType> find(Page page, String typeName) {
        org.springframework.data.domain.Page<OaCertificateType> springDataPage =
                (org.springframework.data.domain.Page<OaCertificateType>) certificateTypeDAO.findByTypeNameContaining(typeName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaCertificateType> findAll(Page page) {
        org.springframework.data.domain.Page<OaCertificateType> springDataPage = certificateTypeDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaCertificateType> findAll() {
        return certificateTypeDAO.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
