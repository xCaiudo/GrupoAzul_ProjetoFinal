package br.com.letscode.spring.pdv.venda;

import br.com.letscode.spring.pdv.cliente.Cliente;
import br.com.letscode.spring.pdv.funcionario.Funcionario;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter

@ToString
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private double total;
    private LocalDate data_venda;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public void setTotal(double valor) {
        this.total += valor;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}


