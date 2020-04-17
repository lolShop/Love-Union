package edu.nf.lol.util;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @date 2020/4/7
 */
public class AliyunUpload {
    public String Upload(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4Fqirc9BaQk4gbMa6V7L";
        String accessKeySecret = "4SjxW2VMnNvkws4glh3p7o7FFoYEts";
        String bucketName = "cffile";
        String fileName = "F:\\QQ文件\\MobileFile\\IMG_20200331_143158.jpg";
        String suffixName =fileName.substring(fileName.lastIndexOf("."));
        String finalFileName =System.currentTimeMillis()+""+new SecureRandom().nextInt(0*0400)+""+suffixName;
        String objName =sdf.format(new Date())+"/"+suffixName;
        File file = new File(fileName);
        OSSClient ossClient =new OSSClient(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,objName,file);
        Date date =new Date(System.currentTimeMillis()+3600*1000);
        URL url = ossClient.generatePresignedUrl(bucketName,objName,date);
        ossClient.shutdown();
        System.out.println(url.toString());
        return url.toString();
    }
}