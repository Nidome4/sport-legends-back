package org.software.product;

import org.software.category.Category;
import org.software.repository.CategoryRepository;
import org.software.repository.ProductRepository;
import org.software.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(@Autowired ProductRepository productRepository, @Autowired CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Servicio que me crea un producto
     * @param product producto a crear en BD
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Product> add(@RequestBody Product product){
        ResponseEntity<Product> response;
        try{
            response = new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que me consulta todos los productos
     * @return lista de productos
     */
    @GetMapping("/")
    public ResponseEntity<ProductList> getAll(){
        ResponseEntity<ProductList> response;
        ProductList productList;
        try{
            productList = new ProductList(productRepository.findAll());
            response = new ResponseEntity<>(productList, HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que me consulta la lista de productos por categoría
     * @param idCategory id de la categoría de los productos a consultar
     * @return productos de una categoría
     */
    @GetMapping("/{idCategory}")
    public ResponseEntity<ProductList> getProductsByCategory(@PathVariable long idCategory){
        ResponseEntity<ProductList> response;
        ProductList productList = new ProductList();
        try{
            Category category = categoryRepository.findById(idCategory).orElse(null);
            if(category != null) {
                productList.setItems(productRepository.findALlByCategory(category));
            }
            response = new ResponseEntity<>(productList, HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  response;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable long id){
        ResponseEntity<?> response;
        Product prevProduct;
        try {
            prevProduct = productRepository.findById(id).orElse(null);
            if(prevProduct != null){
                prevProduct.updateAll(product);
                prevProduct.setUpdatedAt(LocalDateTime.now());
                productRepository.save(prevProduct);
            }
            response = new ResponseEntity<>(prevProduct,HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        ResponseEntity<?> response;
        try {
            productRepository.deleteById(id);
            response = new ResponseEntity<>("se borró correctamente", HttpStatus.OK);
        }catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
        
}
