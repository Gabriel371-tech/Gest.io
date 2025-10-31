package br.com.io.Gest.repository;

import br.com.io.Gest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoriaId(Long categoriaId);
}