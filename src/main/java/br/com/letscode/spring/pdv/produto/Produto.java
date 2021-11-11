package br.com.letscode.spring.pdv.produto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String descricao;

    private double valor;
    private Integer qtd;

    @Column(unique=true, nullable = false)
    private Integer codInterno;

}
