package br.com.letscode.spring.pdv.exception;

public class ExceptionClienteNaoExiste extends RuntimeException {
    public ExceptionClienteNaoExiste(int codInterno) { super("Cliente de código: " + codInterno + " não existe em nosso sistema.");
    }
}
