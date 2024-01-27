package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController
{
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile()
    {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file) throws IOException
    {
        log.info("itemName = {}", itemName);
        log.info("multipartFile = {}", file);

        if (!file.isEmpty())
        {
            //경로로 파일을 저장
            String fullPath = fileDir + file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        return "upload-form";
    }
}