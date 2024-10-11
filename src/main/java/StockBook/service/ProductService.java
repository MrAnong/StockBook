package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.ProductResponse;
import StockBook.model.Product;
import StockBook.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    //1. to add a product
    @Transactional
    public ProductResponse addProducts(Product product){
        ProductResponse response = new ProductResponse();
        response.setProducts(productRepository.save(product));
        response.setMessage("added successfully");
        return response;
    }

    //2. to get a product
    @Transactional
    public ProductResponse getProducts(int id){
        ProductResponse response = new ProductResponse();
        Optional<Product> foundproducts= productRepository.findById((long) id);
        if(foundproducts.isEmpty()){
            response.setMessage("category not found");
            response.setProducts(null);
            return response;
        }
        response.setMessage("success");
        response.setProducts(foundproducts.get());
        return response;
    }

    //3. to get all products
    @Transactional
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    //4. to delete a product
    @Transactional
    public ProductResponse deleteOne(int id){
        ProductResponse response = new ProductResponse();
        Optional<Product> foundProducts = productRepository.findById((long) id);
        if(foundProducts.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setProducts((null));
            return response;
        }
        productRepository.delete(foundProducts.get());
        response.setMessage("delete sucess");
        response.setProducts(foundProducts.get());
        return response;
    }

    //5. to delete a list of product
    @Transactional
    public List<ProductResponse> deleteList(List<Integer> idList){
        List<ProductResponse> list = new ArrayList<>();
        for(int id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a product.
    @Transactional
    public ProductResponse modifyOne(Product products){
        ProductResponse response = new ProductResponse();
        Optional<Product> foundproducts = productRepository.findById((long) products.getId());
        if(foundproducts.isEmpty()){
            response.setMessage("falied! category not found");
            response.setProducts(null);
            return response;
        }
        response.setMessage("success");
        response.setProducts(productRepository.save(products));
        return response;
    }
}
