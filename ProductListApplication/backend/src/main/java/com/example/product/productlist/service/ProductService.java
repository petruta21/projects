package com.example.product.productlist.service;

import com.example.product.productlist.persistence.Product;
import com.example.product.productlist.persistence.ProductPagingRepository;
import com.example.product.productlist.web.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductPagingRepository productRepository;

    public ProductService(ProductPagingRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> list() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).map(ProductService::convertToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO save(ProductDTO productDto) {
        return convertToProductDTO(productRepository.save(convertToProduct(productDto)));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO saveOrUpdate(ProductDTO productDto) {
        return convertToProductDTO(productRepository.save(convertToProduct(productDto)));
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id).map(ProductService::convertToProductDTO).orElse(null);
    }

    public ProductDTO update(ProductDTO productDto, Long id) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(productDto.getName());
        product.setProductCategory(productDto.getCategory());
        return convertToProductDTO(productRepository.save(product));
    }

    private Product convertToProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setProductId(productDto.getId());
        product.setProductName(productDto.getName());
        product.setProductCategory(productDto.getCategory());
        return product;
    }

    private static ProductDTO convertToProductDTO(Product c) {
        ProductDTO result = new ProductDTO();
        result.setId(c.getId());
        result.setName(c.getProductName());
        result.setCategory(c.getProductCategory());
        return result;
    }

    public List<ProductDTO> listPaginated(Pageable pageable) {
        Page<Product> pagedResult = productRepository.findAll(pageable);
        return pagedResult.toList().stream().map(ProductService::convertToProductDTO).collect(Collectors.toList());
    }
}
