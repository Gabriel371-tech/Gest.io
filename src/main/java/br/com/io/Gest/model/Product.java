package br.com.io.Gest.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String ncm;
    private String cfop;
    private Double precoCusto;
    private Double precoVenda;
    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;
}
