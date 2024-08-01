package com.microservices.product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

      @Autowired
      private ProductService productService;

      @PostMapping("/")
      public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request){

          Integer product = this.productService.createProduct(request);
          return new ResponseEntity<>(product, HttpStatus.CREATED);
      }


      @PostMapping("/purchase")
      public ResponseEntity<List<ProductPurchaseResponseDto>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> request){

          List<ProductPurchaseResponseDto> productPurchaseResponseDtos = this.productService.purchaseProduct(request);
          return new ResponseEntity<>(productPurchaseResponseDtos,HttpStatus.CREATED);
      }

      @GetMapping("/{productId}")
      public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Integer productId){
          ProductResponseDto product = this.productService.getProduct(productId);
          return new ResponseEntity<>(product,HttpStatus.OK);
      }

      @GetMapping("/")
      public ResponseEntity<List<ProductResponseDto>> getAllProduct(){

          List<ProductResponseDto> allProduct = this.productService.getAllProduct();
          return new ResponseEntity<>(allProduct,HttpStatus.OK);
      }
}
