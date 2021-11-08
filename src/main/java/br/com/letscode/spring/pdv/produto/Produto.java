package br.com.letscode.spring.pdv.produto;

import lombok.*;

import javax.persistence.*;

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

    private Integer valor;
    private Integer qtd;
    @Column(unique=true)
    private Integer cod_interno;

}
