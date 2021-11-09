package br.com.letscode.spring.pdv.controller;

import br.com.letscode.spring.pdv.funcionario.Funcionario;
import br.com.letscode.spring.pdv.funcionario.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/funcionarios")
@RestController
public class RestControllerFuncionario {
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll();
    }

    @GetMapping("/{Id}")
    public Optional<Funcionario> buscarFuncionario(@PathVariable("id") int funcionarioId) {
         return this.funcionarioRepository.findById(funcionarioId);}


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
        return this.funcionarioRepository.save(funcionario);
    }
}
