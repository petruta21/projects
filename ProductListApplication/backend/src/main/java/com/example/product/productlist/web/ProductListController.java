package com.example.product.productlist.web;

import com.example.product.productlist.service.ProductListService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product_list")
public class ProductListController {
    private final ProductListService productListService;

    public ProductListController(ProductListService productListService) {
        this.productListService = productListService;
    }

    @GetMapping("/list")
    public Iterable<ProductListDTO> list(Pageable pageable) {
        return productListService.listPaginated(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        productListService.deleteById(id);
    }

    @PostMapping("/new_list")
    private Long create(@Valid @RequestBody ProductListDTO productList) {
        return productListService.save(productList).getId();
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductListDTO> getUserById(@PathVariable("id") Long id) {
        ProductListDTO result = productListService.getUserById(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    private ProductListDTO update(@Valid @RequestBody ProductListDTO productList, @PathVariable("id") Long id) {
        return productListService.update(productList, id);
    }
}
