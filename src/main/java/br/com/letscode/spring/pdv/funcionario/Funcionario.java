package br.com.letscode.spring.pdv.funcionario;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int cod_interno;
    private String email;
    private String telefone;
}

