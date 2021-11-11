package br.com.letscode.spring.pdv.controller;

import br.com.letscode.spring.pdv.cliente.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.letscode.spring.pdv.exception.ExceptionClienteNaoExiste;
import br.com.letscode.spring.pdv.funcionario.Funcionario;
import br.com.letscode.spring.pdv.produto.Produto;
import br.com.letscode.spring.pdv.venda.ItemVenda;
import br.com.letscode.spring.pdv.venda.Venda;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("/cliente")
@RestController
public class RestControllerCliente {

    private final List<Cliente> clientes = new ArrayList<>();
    private final Venda compra = new Venda();
    private final List<ItemVenda> carrinhoCompras = new ArrayList<>();

    @GetMapping
    public List<Cliente>listarTodos() {return this.clientes;}

    @GetMapping("{codInterno}")
    public Cliente buscaDetalhada(@PathVariable int codInterno){
        Optional<Cliente> clienteEncontrado = this.buscar(codInterno);
        return clienteEncontrado
                .orElseThrow(()-> new ExceptionClienteNaoExiste(codInterno));
    }

    private Optional<Cliente> buscar(int codInterno) {
        return this.clientes.stream()
                .filter(c -> c.getCodInterno() == codInterno)
                .findFirst();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente) {
    this.clientes.add(cliente);
    log.info("Novo cliente cadastrado: {}", cliente);
    return cliente;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{codInterno}/carrinho")
    public ItemVenda adicionarItemCarrinho(ItemVenda produto){
        this.carrinhoCompras.add(produto);
        log.info("Produto adicionado ao carrinho.");
        return produto;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{codInterno}/compra")
    public Venda efetuarCompra(Funcionario funcionario, @RequestBody Cliente cliente){
        this.compra.setFuncionario(funcionario);
        this.compra.setCliente(cliente);
        this.compra.setData_venda(LocalDate.now());
        this.carrinhoCompras.stream()
                .forEach(e -> this.compra.setTotal(e.getSubtotal()));
        return this.compra;
    }

    @PutMapping("{codInterno}")
    public Cliente atualizar(@PathVariable int codInterno, @RequestBody Cliente cliente) {
    Optional<Cliente> clienteEncontrado = this.buscar(codInterno);
    int indice = this.clientes.indexOf(clienteEncontrado);
    this.clientes.remove(clienteEncontrado);
    this.clientes.add(indice, cliente);
    return cliente;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{codInterno}")
    public void excluir(@PathVariable int codInterno) {
        Optional<Cliente> clienteEncontrado = this.buscar(codInterno);
        this.clientes.remove(clienteEncontrado);
        log.info("O cliente de codigo interno {} foi excluido", codInterno);
    }

}
