package com.scm20.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

   public String uploadImage(MultipartFile contactImage , String fileName);

   String getUrlFromPublicId(String publicId);

}
