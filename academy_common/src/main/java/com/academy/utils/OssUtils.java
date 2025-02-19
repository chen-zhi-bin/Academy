package com.academy.utils;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Component
public class OssUtils {

    private final OSS ossClient;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    public OssUtils(OSS ossClient) {
        this.ossClient = ossClient;
    }

    /**
     * 上传文件
     */
    public String uploadFile(String filePath, InputStream inputStream) {
        try {
            // 上传文件到指定路径
            ossClient.putObject(bucketName, filePath, inputStream);
            // 生成文件的外网访问URL
            return getPreviewUrl(filePath);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e);
        } finally {
            try {
                inputStream.close();  // 确保流关闭
            } catch (Exception e) {
                // 忽略关闭流时的异常
            }
        }
    }

    /**
     * 浏览器预览
     *
     * @param filePath
     * @return
     */
    private String generateFileUrl(String filePath) {
        return "https://" + bucketName + "." + endpoint.replace("https://", "") + "/" + filePath;
    }

    /**
     * 下载文件
     *
     * @param downloadFilePath
     */
    public void downloadFile(String downloadFilePath) {
        try {
            // 下载文件到本地
            ossClient.getObject(bucketName, downloadFilePath);
            System.out.println("文件下载成功: " + downloadFilePath);
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败", e);
        }
    }

    /**
     * 获取浏览器预览URL
     *
     * @param objectName
     * @return
     */
    public String getPreviewUrl(String objectName) {
        try {
            // 设置1小时有效期
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            //Date expiration = new Date(System.currentTimeMillis() + (100L * 365 * 24 * 60 * 60 * 1000));
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            return url.toString();
        } catch (Exception e) {
            throw new RuntimeException("获取预览链接失败", e);
        }
    }

    public String generatePermanentUrl(String bucketName, String endpoint, String objectName) {
        // 拼接公共访问 URL
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }

    /**
     * 删除文件
     *
     * @param objectName
     */
    public void deleteFile(String objectName) {
        try {
            // 删除文件
            ossClient.deleteObject(bucketName, objectName);
            System.out.println("文件删除成功: " + objectName);
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败", e);
        }
    }
}
