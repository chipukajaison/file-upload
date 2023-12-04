package com.chipukajaison.fileupload.controller;

import com.chipukajaison.fileupload.dto.response.ResponseClass;
import com.chipukajaison.fileupload.model.Product;
import com.chipukajaison.fileupload.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Jaison.Chipuka
 * Email    : chipukajaison@gmail.com
 * Date     : Monday 04 December 2023
 * Project  : file-upload
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/1.0.0/files")
public class ProductController {
    private final ProductService fileService;

    // for uploading the SINGLE file to the database
    @PostMapping("/single/base")
    public ResponseClass uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        Product attachment = null;
        String downloadURl = "";
        attachment = fileService.saveAttachment(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();

        return new ResponseClass(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    //for uploading the MULTIPLE files to the database
    @PostMapping("/multiple/base")
    public List<ResponseClass> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws Exception {
        List<ResponseClass> responseList = new ArrayList<>();
        for (MultipartFile file : files) {
            Product attachment = fileService.saveAttachment(file);
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(attachment.getId()))
                    .toUriString();
            ResponseClass response = new ResponseClass(attachment.getFileName(),
                    downloadUrl,
                    file.getContentType(),
                    file.getSize());
            responseList.add(response);
        }
        return responseList;
    }
    //for retrieving  all the  files uploaded
    @GetMapping("/all")
    public ResponseEntity<List<ResponseClass>> getAllFiles() {
        List<Product> products = fileService.getAllFiles();
        List<ResponseClass> responseClasses = products.stream().map(product -> {
            String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(product.getId()))
                    .toUriString();
            return new ResponseClass(product.getFileName(),
                    downloadURL,
                    product.getFileType(),
                    product.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(responseClasses);
    }
}
