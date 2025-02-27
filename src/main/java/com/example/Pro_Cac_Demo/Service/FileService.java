package com.example.Pro_Cac_Demo.Service;

import com.example.Pro_Cac_Demo.Entity.StoreFile;
import com.example.Pro_Cac_Demo.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private final FileRepository fileRepository;

    public FileService (FileRepository fileRepository){
        this.fileRepository=fileRepository;
    }

    public String storeFile(MultipartFile file) throws IOException {
        StoreFile storeFile=new StoreFile();
        storeFile.setFileName(file.getName());
        storeFile.setFileType(file.getContentType());
        storeFile.setImageData(file.getBytes());
        StoreFile attach= fileRepository.save(storeFile);
        if (attach!=null){
            return "Stored SuccessFully..!!";
        }
        return "Not Stored..!!";
    }

    public StoreFile getFile(Long fileId){
        return fileRepository.getById(fileId);
    }

}
