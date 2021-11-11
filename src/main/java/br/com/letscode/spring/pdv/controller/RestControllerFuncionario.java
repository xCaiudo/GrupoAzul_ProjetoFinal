package br.com.letscode.spring.pdv.controller;

import br.com.letscode.spring.pdv.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.letscode.spring.pdv.funcionario.Funcionario;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/funcionarios")
@RestController
public class RestControllerFuncionario {
    private final List<Funcionario> funcionario = new ArrayList<>();

    @GetMapping
    public List<Funcionario>listarTodos() {return this.funcionario;}

    @GetMapping("{codInterno}")
    public Optional<Funcionario> buscaDetalhada(@PathVariable String codInterno){
        final Optional<Funcionario> clienteEncontrado = this.buscar(codInterno);
        return clienteEncontrado;
    }

    private Optional<Funcionario> buscar(String codInterno) {
        return this.funcionario.stream()
                .filter(e -> codInterno.equals(e.getCodInterno()))
                .findFirst();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
        this.funcionario.add(funcionario);
      //  log.info("Novo cliente cadastrado: {}", funcionario);
        return funcionario;
    }

    @PutMapping("{codInterno}")
    public Funcionario atualizar(@PathVariable String codInterno, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = this.buscar(codInterno);
        int indice = this.funcionario.indexOf(funcionarioEncontrado);
        this.funcionario.remove(funcionarioEncontrado);
        this.funcionario.add(indice, funcionario);
        return funcionario;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{codInterno}")
    public void excluir(@PathVariable String codInterno) {
        Optional<Funcionario> funcionarioEncontrado = this.buscar(codInterno);
        this.funcionario.remove(funcionarioEncontrado);
        //log.info("O cliente de codigo interno {} foi excluido", codInterno);
    }

}


