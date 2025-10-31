package br.com.io.Gest.service;

import br.com.io.Gest.model.Product;
import br.com.io.Gest.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product newProduct) {
        Product product = repository.findById(id).orElseThrow();
        product.setNome(newProduct.getNome());
        product.setPrecoVenda(newProduct.getPrecoVenda());
        product.setPrecoCusto(newProduct.getPrecoCusto());
        product.setEstoque(newProduct.getEstoque());
        product.setCategoria(newProduct.getCategoria());
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Product> findByCategory(Long categoryId) {
        return repository.findByCategoriaId(categoryId);
    }
}