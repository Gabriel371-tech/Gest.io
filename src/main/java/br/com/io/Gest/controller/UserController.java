package br.com.io.Gest.controller;

import br.com.io.Gest.exceptions.UserAlreadyExistsException;
import br.com.io.Gest.model.User;
import br.com.io.Gest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String paginaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String email,
                                 @RequestParam String senha,
                                 Model model) {

        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null || !user.getSenha().equals(senha)) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }

        return "redirect:/dashboard";
    }


    @GetMapping("/register")
    public String paginaRegistro() {
        return "register";
    }


    @PostMapping("/register")
    public String processarRegistro(@ModelAttribute User user, Model model) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("E-mail já cadastrado");
        }

        userRepository.save(user);
        return "redirect:/auth/login";
    }
}

