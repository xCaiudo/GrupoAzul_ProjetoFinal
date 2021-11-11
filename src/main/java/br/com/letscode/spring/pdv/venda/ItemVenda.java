package br.com.letscode.spring.pdv.venda;

import br.com.letscode.spring.pdv.produto.Produto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    private int qtd;
    private double subtotal = qtd * produto.getValor();


}
