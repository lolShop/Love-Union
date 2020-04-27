package edu.nf.lol.service.test.user;

//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.ObjectMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @date 2020/3/22
 */
@SpringBootTest
public class CollectTest {
    @Test
    public void Upload(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "LTAI4Fqirc9BaQk4gbMa6V7L";
//        String accessKeySecret = "4SjxW2VMnNvkws4glh3p7o7FFoYEts";
//        String bucketName = "cffile";
//        String fileName [] ={"F:\\timg (1).jpg","F:\\timg.jpg"} ;
//        OSSClient ossClient =new OSSClient(endpoint,accessKeyId,accessKeySecret);
//        for(int i=0;i<fileName.length;i++){
//            String suffixName =fileName[i].substring(fileName[i].lastIndexOf("."));
//            String finalFileName =System.currentTimeMillis()+""+suffixName;
//            System.out.println(suffixName);
////        String objName =sdf.format(new Date())+"/"+finalFileName;
//            String objName ="image/"+finalFileName;
//            System.out.println(finalFileName);
//            File file = new File(fileName[i]);
////        ObjectMetadata meta =new ObjectMetadata();
////        meta.setContentDisposition("attachment");
//
//
//            ossClient.putObject(bucketName,objName,file);
//            System.out.println("-------------------------");
//        }
//        ossClient.shutdown();

//        Date date =new Date(System.currentTimeMillis() + 3600 * 1000);
//        URL url = ossClient.generatePresignedUrl(bucketName,objName,date);

//        System.out.println(url.toString());
    }

}