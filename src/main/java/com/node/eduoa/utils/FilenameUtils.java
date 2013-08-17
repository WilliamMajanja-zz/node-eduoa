package com.node.eduoa.utils;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午12:06
 * To change this template use File | Settings | File Templates.
 */
public class FilenameUtils {

    public static String getExtension(String originalFilename) {
        String[] ext = originalFilename.split("\\.");
        if (ext.length > 1) {
            return ext[1];
        }
        return null;
    }
}
