package com.ohgiraffers.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam String singleFileDescription,
                                   @RequestParam MultipartFile singleFile,
                                   Model model) {

        System.out.println("singleFileDescription : " + singleFileDescription);
        System.out.println("singleFile : " + singleFile);

        //서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다

        //파일 저장 경로 설정
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());

        if (!dir.exists()) dir.mkdirs();

        //파일명 변경 처리
        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

        //파일 저장
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            //transferTo로 메모리 상에 있던 파일이 물리적 경로로 저장 되도록
            model.addAttribute("message", "파일 업로드 완료");
            //모델 객체에 메세지를 담아서 보낸다
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패");
        }

        return "result";
    }
//============================================================================

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam String multiFileDescription,
                                  @RequestParam List<MultipartFile> multiFiles,
                                  Model model) {

        System.out.println("multiFileDescription : " + multiFileDescription);
        System.out.println("multiFiles : " + multiFiles);

        /* 파일을 저장할 경로 설정 */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        System.out.println("root : " + root);

        if (!dir.exists()) dir.mkdirs();

        List<FileDTO> files = new ArrayList<>();

        try {
            //파일명 변경 처리 후 저장 : 다중 파일 반복문 처리
            for (MultipartFile file : multiFiles) {

                //파일명 변경 처리
                String originFileName = file.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

                //파일에 관한 정보 추출 후 보관
                files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));

                //파일 저장
                file.transferTo(new File(filePath + "/" + savedName));
            }

            //서버의 정해진 경로로 파일 저장이 완료되면 List<FileDTO> 타입의 객체에 저장 된 정보를 DB에 insert한다.

            model.addAttribute("message", "다중 파일 업로드 성공!");

        } catch (IOException e) {

            //파일 저장 중간에 실패 시 이전에 저장 된 파일 삭제
            for (FileDTO file : files){
                new File(filePath + "/" + file.getSavedName()).delete();
            }

            model.addAttribute("message", "다중 파일 업로드 실패!");
        }
        return "result";
    }

}
