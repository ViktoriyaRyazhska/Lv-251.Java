package com.softserve.edu.lv251.dto.pojos;

import com.softserve.edu.lv251.customannotations.ValidPhoto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;

/**
 * Created by Yana Martynyak on 18.08.2017.
 */
public class PhotoDTO {

    @ValidPhoto
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}


