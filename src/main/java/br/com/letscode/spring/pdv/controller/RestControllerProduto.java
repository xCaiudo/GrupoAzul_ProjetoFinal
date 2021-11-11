package br.com.letscode.spring.pdv.controller;

import br.com.letscode.spring.pdv.exception.ExceptionProdutoNaoExiste;
import br.com.letscode.spring.pdv.produto.Produto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequestMapping("/produto")
@RestController

public class RestControllerProduto {
    private final List<Produto> produto = new ArrayList<>();

    @GetMapping
    public List<Produto> listarGeral() {
        return this.produto;
    }

    @GetMapping("{codInterno}")
    public Produto buscarDetalhar(@PathVariable int codInterno) {
        Optional<Produto> produtoEncontrado = this.busca(codInterno);
        return produtoEncontrado
                .orElseThrow(() -> new ExceptionProdutoNaoExiste(codInterno));
    }

    private Optional<Produto> busca(int codInterno) {
        return this.produto.stream()
                .filter(c -> c.getCodInterno() == codInterno)
                .findFirst();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/produto")
    public Produto cadastro(@RequestBody Produto produto) {
        this.produto.add(produto);
        log.info("Novo produto cadastrado: {}", produto);
        return produto;
    }

    @PutMapping("{CodInterno}")
    public Produto atualizar(@PathVariable int codInterno, @RequestBody Produto produto) {
        Optional<Produto> produtoEncontrado = this.busca(codInterno);
        int indice = this.produto.indexOf(produtoEncontrado);
        this.produto.remove(produtoEncontrado);
        this.produto.add(indice, produto);
        return produto;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{CodInterno}")
    public void excluir(@PathVariable int codInterno) {
        Optional<Produto> produtoEncontrado = this.busca(codInterno);
        this.produto.remove(produtoEncontrado);
        log.info("O produto {} foi excluído,", codInterno);
    }
}

