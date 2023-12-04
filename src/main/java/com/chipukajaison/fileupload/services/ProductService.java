package com.chipukajaison.fileupload.services;

import com.chipukajaison.fileupload.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : Jaison.Chipuka
 * Email    : chipukajaison@gmail.com
 * Date     : Monday 04 December 2023
 * Project  : file-upload
 */
public interface ProductService {
    Product saveAttachment(MultipartFile file) throws Exception;
    void saveFiles(MultipartFile[] files) throws Exception;
    List<Product> getAllFiles();
}
