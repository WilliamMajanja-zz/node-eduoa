package com.node.eduoa.service.impl;

import com.node.eduoa.dao.AttachmentDao;
import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.SysAttachment;
import com.node.eduoa.service.AttachmentService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {


    @Qualifier("attachmentDao")
    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public void save(SysAttachment attachment) {
        attachmentDao.save(attachment);
    }

    @Override
    public void delete(Long id) {
        attachmentDao.delete(id);
    }

    @Override
    public SysAttachment get(Long id) {
        return attachmentDao.findOne(id);
    }

    @Override
    public void update(SysAttachment attachment) {
        attachmentDao.save(attachment);
    }

    @Override
    public List<SysAttachment> find(Page page, String fileName) {
        org.springframework.data.domain.Page<SysAttachment> springDataPage =
                (org.springframework.data.domain.Page<SysAttachment>) attachmentDao.findByFileNameContaining(fileName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
