package cn.yy.reggie.controller;

import cn.yy.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${upload.basename}")
    private String baseName;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info("文件上传 {}",file.getOriginalFilename());
        File baseFile = new File(baseName);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        String fileName = UUID.randomUUID().toString()+
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        file.transferTo(new File(baseName+fileName));
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        log.info("文件下载 {}",name);
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            FileInputStream is = new FileInputStream(baseName + name);
            ServletOutputStream os = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf))!=-1) {
                os.write(buf, 0, len);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
