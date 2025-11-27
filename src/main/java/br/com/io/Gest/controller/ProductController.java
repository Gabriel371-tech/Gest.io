package br.com.io.Gest.controller;

import br.com.io.Gest.model.Product;
import br.com.io.Gest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // LISTAR PRODUTOS NA DASHBOARD
    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", productRepository.findAll());
        model.addAttribute("produto", new Product());
        return "dashboard";
    }

    // SALVAR NOVO PRODUTO
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/produtos";
    }

    // EDITAR PRODUTO
    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Product produto = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));

        model.addAttribute("produto", produto);
        model.addAttribute("produtos", productRepository.findAll());
        return "dashboard";
    }

    // ATUALIZAR PRODUTO
    @PostMapping("/atualizar/{id}")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/produtos";
    }

    // EXCLUIR PRODUTO
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/produtos";
    }
}
