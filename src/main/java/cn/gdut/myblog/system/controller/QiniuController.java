package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.exception.GlobalException;
import cn.gdut.myblog.common.properties.MyBlogProperties;
import cn.gdut.myblog.common.properties.QiniuProperties;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.QiNiuEntity;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
@Slf4j
@RestController
@RequestMapping("/api/storage/qiniu")
public class QiniuController {


    @Autowired
    private MyBlogProperties properties;

    @GetMapping(value = "/domain")
    public R domina(){
        QiniuProperties qiniu = properties.getQiniu();
        return new R<>(qiniu.getUrl());
    }

    @GetMapping("/list")
    public R list(){
        QiniuProperties qiniu = properties.getQiniu();
        try {
            Configuration configuration = new Configuration();
            Auth auth = Auth.create(qiniu.getAk(), qiniu.getSk());
            BucketManager bucketManager = new BucketManager(auth, configuration);
            String prefix = "";
            int limit = 1000;
            String delimiter = "";
            BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiniu.getBn(), prefix, limit, delimiter);
            ArrayList<QiNiuEntity> list = new ArrayList<>();
            while (fileListIterator.hasNext()){
                FileInfo[] items = fileListIterator.next();
                for (FileInfo item : items){
                    QiNiuEntity qiNiuEntity = new QiNiuEntity(item.hash, item.key, item.mimeType,item.fsize, qiniu.getUrl().trim()+ item.key);
                    list.add(qiNiuEntity);
                }
            }
            Map map = new HashMap();
            map.put("rows", list);
            return new R<>(map);

        }
        catch (Exception e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @RequestMapping("/upload")
    public R upload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        QiniuProperties qiniu = properties.getQiniu();
        if (!file.isEmpty()){
            // 文件上传的路径
            String FilePath = "";
            //  上传到七牛的文件名
            String key = new Date().getTime()+"";
            try {
                // 将文件对象转化为File，相当于需要以本地作为缓冲区暂存文件
                // 获取文件在服务器中位置
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                File filePath = new File(path.getAbsolutePath(), "upload/");
                if (!filePath.exists() && !filePath.isDirectory()){
                    System.out.println("目录不存在，创建目录=======》");
                    filePath.mkdir();
                }
                // 获取源文件名
                String filename = file.getOriginalFilename();
                //获取类型
                key += filename.substring(filename.lastIndexOf("."));
                File localFile = new File(filePath, key);

                // 写入磁盘中
                file.transferTo(localFile);
                System.out.println("文件的原始路径"+filePath);
                System.out.println("文件的新路径"+key);
                FilePath = filePath + "/" + key;

                // 秘钥配置
                Auth auth = Auth.create(qiniu.getAk(), qiniu.getSk());
                // 自动识别要上传的空间
                Zone zone = Zone.autoZone();

                Configuration configuration = new Configuration(zone);
                // 创建文件上传对象
                UploadManager uploadManager = new UploadManager(configuration);
                // 调用上传的方法
                Response res = uploadManager.put(FilePath, key, auth.uploadToken(qiniu.getBn()));
                // 打印返回的信息
                System.out.println(res.bodyString());
                Map map = new HashMap();
                map.put("name", key);
                map.put("url",qiniu.getUrl()+key);

                if (localFile.exists()){
                    // 删除本地缓存的文件
                    localFile.delete();
                }
                return new R<>(map);

            }catch (Exception e){
                System.out.println("文件上传失败");
                throw new GlobalException(e.getMessage());
            }
        }
        return new  R <>();
    }
}
*/
