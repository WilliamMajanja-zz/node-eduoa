package com.node.eduoa.dao;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.SysAttachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 附件
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public interface AttachmentDao extends JpaRepository<SysAttachment, Long>, JpaSpecificationExecutor<SysAttachment> {

    Page<SysAttachment> findByFileNameContaining(String fileName, Pageable pageable);

}
