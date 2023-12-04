package com.chipukajaison.fileupload.dto.response;

/**
 * @author : Jaison.Chipuka
 * Email    : chipukajaison@gmail.com
 * Date     : Monday 04 December 2023
 * Project  : file-upload
 */
public record ResponseClass(
        String filename,
        String downloadUrl,
        String fileType,
        long fileSize
) { }
