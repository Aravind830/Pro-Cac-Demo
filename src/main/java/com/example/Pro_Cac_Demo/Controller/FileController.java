package com.example.Pro_Cac_Demo.Controller;

import com.example.Pro_Cac_Demo.Entity.StoreFile;
import com.example.Pro_Cac_Demo.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile storeFile) throws IOException {
       return ResponseEntity.status(HttpStatus.OK).body(fileService.storeFile(storeFile));
//       String downUrl= ServletUriComponentsBuilder
//               .fromCurrentContextPath().path("/download").path(storeFile1.getFileName()).toUriString();
//       return ResponseEntity.status(HttpStatus.OK)
//               .body(new ResponseDto(storeFile1.getFileName(),downUrl,storeFile.getContentType(),storeFile.getSize()));
    }

    @GetMapping("/get/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getFile(fileId).getImageData());
    }
}
