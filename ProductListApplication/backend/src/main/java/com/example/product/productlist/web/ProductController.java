package com.example.product.productlist.web;


import com.example.product.productlist.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public Iterable<ProductDTO> list(Pageable pageable) {
        return productService.listPaginated(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/product")
    private Long create(@Valid @RequestBody ProductDTO product) {
        return productService.saveOrUpdate(product).getId();
    }

    @PutMapping("/{id}")
    private ProductDTO update(@Valid @RequestBody ProductDTO product, @PathVariable("id") Long id) {
        return productService.update(product, id);
    }

    @GetMapping("/{id}")
    private ResponseEntity<RegistrationDTO> getProductById(@PathVariable("id") Long id) {
        ProductDTO result = productService.getProductById(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

}
