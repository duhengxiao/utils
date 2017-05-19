package com.betel.utlis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

/**
 * @ClassName:TarUtil
 * @Description:
 * @author:Du.hx
 * @Date:2016年7月29日下午2:04:34
 * @version 1.0
 */
public class TarUtil {

    /**
     * 目录分隔符
     */
    private static final String PATH = "/";
    /**
     * 读写片段大小
     */
    private static final int BUFFER = 1024;
    /**
     * 归档文件后缀
     */
    private static final String EXT = ".tar";

    /**
     * 将指定路径的目录或文件归档到指定目录下
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:01:14
     * @param srcPath:归档目标文件或目录
     * @param destDir：归档到指定目录
     * @throws Exception
     */
    public static void archive(String srcPath, String destDir) throws Exception {
        File srcFile = new File(srcPath);
        archive(srcFile, destDir);
    }

    /**
     * 将指定目录或文件归档到指定目录下
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午5:01:11
     * @param srcFile
     * @param destDir
     * @throws Exception
     */
    public static void archive(File srcFile, String destDir) throws Exception {
        String srcName = srcFile.getName();
        if (!srcFile.isDirectory()) {
            srcName = srcName.substring(0, srcName.lastIndexOf("."));
        }

        String destPath = destDir + PATH + srcName + EXT;
        File destFile = new File(destPath);

        TarArchiveOutputStream tarOutput = new TarArchiveOutputStream(new FileOutputStream(destFile));
        archive(srcFile, tarOutput, destDir);
        tarOutput.close();
    }

    /**
     * 将指定路径tar文件解压到指定目录下
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:51:42
     * @param srcPath
     * @param destDir
     * @throws Exception
     */
    public static void dearchive(String srcPath, String destDir) throws Exception {
        File srcFile = new File(srcPath);
        dearchive(srcFile, destDir);
    }

    /**
     * 将指定tar文件解压到指定目录下
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:52:09
     * @param srcFile
     * @param destDir
     * @throws Exception
     */
    public static void dearchive(File srcFile, String destDir) throws Exception {
        TarArchiveInputStream tarInput = new TarArchiveInputStream(new FileInputStream(srcFile));

        String srcName = srcFile.getName();
        if (!srcFile.isDirectory()) {
            srcName = srcName.substring(0, srcName.lastIndexOf("."));
        }
        String destPath = destDir + PATH + srcName;
        File destFile = new File(destPath);
        dearchive(destFile, tarInput);
        tarInput.close();
    }

    // ******************************归档私有方法******************************
    /**
     * 归档
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:01:52
     * @param srcFile
     * @param tarOutput
     * @param destDir
     * @throws Exception
     */
    private static void archive(File srcFile, TarArchiveOutputStream tarOutput, String destDir) throws Exception {
        if (srcFile.isDirectory()) {
            archiveDir(srcFile, tarOutput, destDir);
        } else {
            archiveFile(srcFile, tarOutput, destDir);
        }
    }

    /**
     * 归档目录
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:02:00
     * @param dir
     * @param tarOutput
     * @param destDir
     * @throws Exception
     */
    private static void archiveDir(File dir, TarArchiveOutputStream tarOutput, String destDir) throws Exception {

        File[] files = dir.listFiles();

        if (files.length < 1) {
            TarArchiveEntry entry = new TarArchiveEntry(destDir + dir.getName() + PATH);

            tarOutput.putArchiveEntry(entry);
            tarOutput.closeArchiveEntry();
        }
        for (File file : files) {
            // 递归归档
            archive(file, tarOutput, destDir + dir.getName() + PATH);

        }
    }

    /**
     * 归档文件
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:02:14
     * @param file
     * @param tarOutput
     * @param destDir
     * @throws Exception
     */
    private static void archiveFile(File file, TarArchiveOutputStream tarOutput, String destDir) throws Exception {

        TarArchiveEntry entry = new TarArchiveEntry(destDir + file.getName());
        entry.setSize(file.length());
        tarOutput.putArchiveEntry(entry);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            tarOutput.write(data, 0, count);
        }
        bis.close();
        tarOutput.closeArchiveEntry();
    }

    // ******************************解归档私有方法******************************
    /**
     * 解归档递归
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午5:02:15
     * @param destFile
     * @param tarInput
     * @throws Exception
     */
    private static void dearchive(File destFile, TarArchiveInputStream tarInput) throws Exception {
        TarArchiveEntry entry = null;
        while ((entry = tarInput.getNextTarEntry()) != null) {
            // 文件
            String dir = destFile.getPath() + File.separator + entry.getName();
            File dirFile = new File(dir);
            // 文件检查
            fileProber(dirFile);
            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                dearchiveFile(dirFile, tarInput);
            }
        }
    }

    /**
     * 解归档文件
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午5:02:38
     * @param destFile
     * @param tarInput
     * @throws Exception
     */
    private static void dearchiveFile(File destFile, TarArchiveInputStream tarInput) throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = tarInput.read(data, 0, BUFFER)) != -1) {
            bos.write(data, 0, count);
        }
        bos.close();
    }

    /**
     * 当父目录不存在时，创建目录
     * 
     * @author:Du.hx
     * @Date:2016年7月29日下午4:53:05
     * @param dirFile
     */
    private static void fileProber(File dirFile) {
        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {
            // 递归寻找上级目录
            fileProber(parentFile);
            parentFile.mkdir();
        }

    }

}
