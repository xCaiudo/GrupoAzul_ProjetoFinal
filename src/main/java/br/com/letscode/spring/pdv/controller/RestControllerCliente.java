package br.com.letscode.spring.pdv.controller;

import br.com.letscode.spring.pdv.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("/cliente")
@RestController
public class RestControllerCliente {

    private final List<Cliente> clientes = new ArrayList<>();

    @GetMapping
    public List<Cliente>listarTodos() {return this.clientes;}

    @GetMapping("{codInterno}")
    public Optional<Cliente> buscaDetalhada(@PathVariable String codInterno){
        final Optional<Cliente> clienteEncontrado = this.buscar(codInterno);
        return clienteEncontrado;
    }

    private Optional<Cliente> buscar(String codInterno) {
        return this.clientes.stream()
                .filter(e -> codInterno.equals(e.getCodInterno()))
                .findFirst();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente) {
    this.clientes.add(cliente);
    log.info("Novo cliente cadastrado: {}", cliente);
    return cliente;
    }

    @PutMapping("{codInterno}")
    public Cliente atualizar(@PathVariable String codInterno, @RequestBody Cliente cliente) {
    Optional<Cliente> clienteEncontrado = this.buscar(codInterno);
    int indice = this.clientes.indexOf(clienteEncontrado);
    this.clientes.remove(clienteEncontrado);
    this.clientes.add(indice, cliente);
    return cliente;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{codInterno}")
    public void excluir(@PathVariable String codInterno) {
        Optional<Cliente> clienteEncontrado = this.buscar(codInterno);
        this.clientes.remove(clienteEncontrado);
        log.info("O cliente de codigo interno {} foi excluido", codInterno);
    }

}
