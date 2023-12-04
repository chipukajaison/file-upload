package com.chipukajaison.fileupload.repository;

import com.chipukajaison.fileupload.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Jaison.Chipuka
 * Email    : chipukajaison@gmail.com
 * Date     : Monday 04 December 2023
 * Project  : file-upload
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
