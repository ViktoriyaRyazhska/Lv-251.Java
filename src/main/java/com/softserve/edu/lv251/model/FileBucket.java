package com.softserve.edu.lv251.model;


import com.softserve.edu.lv251.customannotations.ValidPhoto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Admin on 04.08.2017.
 */

public class FileBucket {
@ValidPhoto
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}
