package com.microservices.product.product;

import com.microservices.product.category.Category;
import com.microservices.product.category.CategoryRepo;
import com.microservices.product.exception.ProductPurchaseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;


    public Integer createProduct(ProductRequest request){
        Product map = this.modelMapper.map(request, Product.class);
        Category category = this.categoryRepo.findById(request.categoryId()).get();
        map.setName(request.name());
        map.setPrice(request.price());
        map.setDescription(request.description());
        map.setAvailableQuantity(request.availableQuantity());
        map.setCategory(category);
        Product save = this.productRepo.save(map);
        return save.getId();
    }

    public ProductResponseDto getProduct(Integer productId){
        Product product = this.productRepo.findById(productId).get();
        ProductResponseDto map = this.modelMapper.map(product, ProductResponseDto.class);
       // productResponse(product);
        return map;
    }

//    public static ProductResponse toProductResponse(Product product) {
//        return new ProductResponse(
//                product.getId(),
//                product.getName(),
//                product.getDescription(),
//                product.getAvailableQuantity(),
//                product.getPrice(),
//                product.getCategory()
//        );
//    }


    public List<ProductResponseDto> getAllProduct(){
        List<Product> all = this.productRepo.findAll();
        List<ProductResponseDto> collect = all.stream().map(a -> this.modelMapper.map(a, ProductResponseDto.class)).collect(Collectors.toList());
        return collect;
    }

    public List<ProductPurchaseResponseDto> purchaseProduct(List<ProductPurchaseRequest> requests){

        List<Integer> collect = requests.stream().map(ProductPurchaseRequest::productId).toList();

        List<Product> storeProduct = this.productRepo.findAllByIdInOrderById(collect);

        if(collect.size() != storeProduct.size()){

            throw new ProductPurchaseException("One or more product does not exists");
        }

        List<ProductPurchaseRequest> storeRequest = requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        ArrayList<ProductPurchaseResponseDto> productPurchaseResponses = new ArrayList<>();

        for(int i=0; i<storeProduct.size(); i++){

            Product product = storeProduct.get(i);

            ProductPurchaseRequest productPurchaseRequest = storeRequest.get(i);

            if(product.getAvailableQuantity()< productPurchaseRequest.quantity()){

                throw new ProductPurchaseException("Insufficient stock quantity for purchase");

            }

            double newAvialiableQuantity = product.getAvailableQuantity() - productPurchaseRequest.quantity();

            product.setAvailableQuantity(newAvialiableQuantity);

            this.productRepo.save(product);

            productPurchaseResponses.add(this.modelMapper.map(product,ProductPurchaseResponseDto.class));

        }

        return productPurchaseResponses;

    }


}
