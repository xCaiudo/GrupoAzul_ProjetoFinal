package br.com.letscode.spring.pdv.funcionario;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String login;

    private String senha;

    @OneToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;


}
