package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto") // caminho inicial dos métodos do controller
@AllArgsConstructor // cria um construtor com todos os atributos da classe
public class ProdutoController {
    // Injeta (cria automaticamente uma instância do tipo da variável
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto>findAll(){

        return repository.findAll();
    }
    

    @GetMapping("/{id}")
    public Produto findById( @PathVariable long id){
        return repository.
                findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deletar( @PathVariable long id){
        //repository.deleteById(id);
        repository.findById(id)
                .stream()
                .peek(produto -> repository.delete(produto))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public Produto insert( @RequestBody Produto produto){
        return repository.save(produto);
    }
    /* Indica que o métado deve ser chamado enviando requisição HTTP GET.
     * Só após isso é que o método fica disponível para acessar  via HTTP.
     */
    @PutMapping("{id}")
    public Produto update(@PathVariable long id, @RequestBody Produto produto){
       final var msg = "O ID informado não conincide com o ID do objeto passado";
        if (id != produto.getId())
            throw new ResponseStatusException(HttpStatus.CONFLICT, msg);

        return repository.save(produto);
    }
}
