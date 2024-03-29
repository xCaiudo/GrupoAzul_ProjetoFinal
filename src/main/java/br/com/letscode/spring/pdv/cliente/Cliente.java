package br.com.letscode.spring.pdv.cliente;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;


    private String email;
    private String telefone;

    @Column(unique = true, nullable = false)
    private int codInterno;

}


