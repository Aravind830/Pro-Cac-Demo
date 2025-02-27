package com.example.Pro_Cac_Demo.Entity;

import jakarta.persistence.*;

@Entity
public class StoreFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] imageData;

//    public StoreFile(String fileName, String fileType, byte[] imageData) {
//        this.fileName = fileName;
//        this.fileType = fileType;
//        this.imageData = imageData;
//    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
