package com.Mikey.App.contoller;

import com.Mikey.App.Model.Product;
import com.Mikey.App.Service.ProductService;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;


     @GetMapping("/products")
     public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
     }

     @GetMapping("/product/{id}")
     public ResponseEntity<Product> fetchProduct(@PathVariable int id) {
           Product product= service.getById(id);

           if(product !=null) {
               return new ResponseEntity<>(product,HttpStatus.OK);
           }
           else{
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
     }

     @PostMapping("/product")
     public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
         Product saveproduct = null;
         try {
             saveproduct = service.addProduct(product, imageFile);
             return new ResponseEntity<>(saveproduct, HttpStatus.CREATED);
         } catch (IOException e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @GetMapping("/product/{id}/image")
     public ResponseEntity<byte[]> getProductByImageId(@PathVariable int id){
         Product product= service.getById(id);
         if(product.getId()!= null) {
             return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
         }
         else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
     }

     @PutMapping("/product/{id}")
     public ResponseEntity<String> UpadateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
             Product updateProduct=null;
         updateProduct = service.UpdateProduct(product, imageFile);
         return new ResponseEntity<>("Updated", HttpStatus.CREATED);
     }
     @DeleteMapping("/product/{id}")
     public ResponseEntity<String> DeleteProduct(@PathVariable int id) {
         Product Deletedproduct= service.getById(id);

         if(Deletedproduct!=null) {
             service.DeleteProduct(id);
             return new ResponseEntity<>("Deleted", HttpStatus.OK);
         }
         else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     }


     @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {

         List<Product> product=service.searchproduct(keyword);
         System.out.println("Searching with: "+keyword);
         return new ResponseEntity<>(product, HttpStatus.OK);
     }
}

