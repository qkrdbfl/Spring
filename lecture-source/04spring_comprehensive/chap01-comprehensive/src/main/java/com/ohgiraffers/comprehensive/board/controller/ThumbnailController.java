package com.ohgiraffers.comprehensive.board.controller;

import com.ohgiraffers.comprehensive.board.dto.AttachmentDTO;
import com.ohgiraffers.comprehensive.board.dto.BoardDTO;
import com.ohgiraffers.comprehensive.board.service.BoardService;
import com.ohgiraffers.comprehensive.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/thumbnail")
public class ThumbnailController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final BoardService boardService; //여기부터

    public ThumbnailController(BoardService boardService){
        this.boardService = boardService;
    } //의존성 주입

    @GetMapping("/regist")
    public String getRegistPage() {
        return "thumbnail/thumbnailRegist";
    }

    @PostMapping("/regist")
    public String registThumbnail(BoardDTO board, List<MultipartFile> attachImage,
                                  @AuthenticationPrincipal MemberDTO member) {

        log.info("thumbnail board request : {}", board);
        log.info("thumbnail attachImage request : {}", attachImage);

        //저장 경로 두개로 분리함
        String fileUploadDir = IMAGE_DIR + "original";
        String thumbnailDir = IMAGE_DIR + "thumbnail";

        File dir1 = new File(fileUploadDir);
        File dir2 = new File(thumbnailDir);

        //디렉토리가 없을 경우 생성한다
        if (!dir1.exists() || !dir2.exists()) {
            dir1.mkdirs();
            dir2.mkdirs();
        }

        //업로드 파일에 대한 정보를 담을 리스트
        List<AttachmentDTO> attachmentList = new ArrayList<>();

        try {

            for (int i = 0; i < attachImage.size(); i++) {
                //첨부파일이 실제로 존재하는 경우에만 로직 수행
                if (attachImage.get(i).getSize() > 0) {

                    String originalFileName = attachImage.get(i).getOriginalFilename();
                    log.info("originalFileName : {}", originalFileName);

                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID() + ext;
                    log.info("savedFileName : {}", savedFileName);

                    //서버의 설정 디렉토리에 파일 지정하기
                    attachImage.get(i).transferTo(new File(fileUploadDir + "/" + savedFileName));

                    //DB에 저장할 파일의 정보 처리
                    AttachmentDTO fileInfo = new AttachmentDTO();
                    fileInfo.setOriginalName(originalFileName);
                    fileInfo.setSavedName(savedFileName);
                    fileInfo.setSavePath("/upload/original/");

                    if(i == 0) {
                        fileInfo.setFileType("TITLE");
                        /* 대표 사진에 대한 썸네일을 가공해서 저장한다. */ //여기부터 썸네일 쓰기위해 (밑에 코드2줄) 코끼리 라이브러리에 넣은거임 => implementation 'net.coobird:thumbnailator:0.4.20'
                        Thumbnails.of(fileUploadDir + "/" + savedFileName).size(300, 300)
                                .toFile(thumbnailDir + "/thumbnail_" + savedFileName); //여기까지
                        fileInfo.setThumbnailPath("/upload/thumbnail/thumbnail_" + savedFileName); //// /꼭 슬래쉬 ㅠㅠ조심하기
                    }else  {
                        fileInfo.setFileType("BODY");
                    }
                    attachmentList.add(fileInfo); //반복문 돌면서 파일 첨부 3개면 3개 4개면 4개 잁케 드감
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("attachmentList : {}", attachmentList);

        board.setAttachmentList(attachmentList);
        board.setWriter(member);//글 작성자 담기(로그인 한 사람)

        boardService.registThumbnail(board); //DB에 담기

        return "redirect:/thumbnail/list";
    }

    @GetMapping("/list")
    public String selectThumbnailList(@RequestParam(defaultValue = "1") int page, Model model) {

        Map<String, Object> thumbnailListAndPaging = boardService.selectThumbnailList(page);
        model.addAttribute("paging", thumbnailListAndPaging.get("paging"));
        model.addAttribute("thumbnailList", thumbnailListAndPaging.get("thumbnailList"));

        return "thumbnail/thumbnailList";
    }

    @GetMapping("/detail")
    public String selectThumbnailDetail(Long no, Model model){

        log.info("thumbnail no : {}", no);

        BoardDTO thumbnail = boardService.selectThumbnailDetail(no);
        log.info("thumbnail : {}", thumbnail);

        model.addAttribute("thumbnail", thumbnail);

        return "thumbnail/thumbnailDetail";
    }

}
