package br.com.letscode.spring.pdv.exception;

public class ExceptionProdutoNaoExiste extends RuntimeException {
    public ExceptionProdutoNaoExiste(int codInterno) { super("Produto de código: " + codInterno + "não foi encontrado");}
}
