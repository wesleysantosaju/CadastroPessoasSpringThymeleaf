package com.example.th.controller;

import com.example.th.model.Pessoa;
import com.example.th.repositories.PessoaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    //injeção de dependência
    private final PessoaRepository pessoaRepository;

    //construtor
    public UserController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    //método para chamar a página index listando todos os registros
    @GetMapping("/index")
    public String showUserList(Model model){
        /* model fornece atributos usados para renderizar visualização
        pegamos o elemento model e atribuimos a ele todos os registros para serem listados,
        e damos a ele o nome de users
        sendo assim, todas as vezes que chamarmos users, vamos ter uma lista com todos os registros.*/
        model.addAttribute("users", pessoaRepository.findAll());
        return "index";
    }

    //Aqui ao chamar o mapping, teremos em tela a nossa página add-user
    @GetMapping("/signup")
    public String showSignUpForm(Pessoa pessoa){
        return "add-user";
    }

    //método post para adicionar usuários
    //acionamos esse método no front para adicionar usuários
    @PostMapping("/adduser")
    //BindingResult é um argumento para aplicar ao método, quando usado, você pode procurar validações dentro do método.
    public String addUser(@Valid Pessoa pessoa, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-user";
        }

        pessoaRepository.save(pessoa);
        return "redirect:/index";
    }

    //método GET para listar os registros a serem editados no front
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id da pessoa é inválido" + id));
        model.addAttribute("user", pessoa);
        return "update-user";
    }

    //método que faz a atualização do registro que foi alterado no método acima
    @PostMapping("/update/{id}")
    //novamente passando o BidingResult para validar
    public String updateUser(@PathVariable("id") long id, @Valid Pessoa pessoa, BindingResult result, Model model){
        if(result.hasErrors()){
            pessoa.setId(id);
            return "update-user";
        }

        pessoaRepository.save(pessoa);
        return "redirect:/index";
    }

    //método que deleta os registros
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model){
        //instaciando a model e passando os dados pelo id, caso de erro ele cai em uma exception que está dentro de uma lambda.
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id da pessoa não existe: " +id));
        pessoaRepository.delete(pessoa);
        //após deletado, retorne a index.
        return "redirect:/index";
    }
}
