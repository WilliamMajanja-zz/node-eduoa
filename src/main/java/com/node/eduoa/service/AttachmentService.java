package com.node.eduoa.service;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.SysAttachment;
import com.node.system.util.dwz.Page;

import java.util.List;

/**
 * 附件
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public interface AttachmentService {

    void save(SysAttachment attachment);

    void delete(Long id);

    SysAttachment get(Long id);

    void update(SysAttachment attachment);

    List<SysAttachment> find(Page page, String fileName);



}
