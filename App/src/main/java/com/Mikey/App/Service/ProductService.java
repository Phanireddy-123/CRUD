package com.Mikey.App.Service;

import com.Mikey.App.Model.Product;
import com.Mikey.App.Repo.ProductReo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductReo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repo.save(product);
    }

    public Product UpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repo.save(product);
    }

    public void DeleteProduct(int id) {
         repo.findById(id);

    }

    public List<Product> searchproduct(String keyword) {
       return repo.searchProductBy(keyword);

    }
}