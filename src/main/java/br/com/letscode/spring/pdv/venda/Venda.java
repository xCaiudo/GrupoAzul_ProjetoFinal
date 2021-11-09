package br.com.letscode.spring.pdv.venda;

import br.com.letscode.spring.pdv.cliente.Cliente;
import br.com.letscode.spring.pdv.funcionario.Funcionario;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private String id_funcionario;
    private String id_cliente;
    private Integer total;
    private Integer data_venda;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;



}
