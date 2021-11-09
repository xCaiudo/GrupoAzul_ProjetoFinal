package br.com.letscode.spring.pdv.funcionario;

import br.com.letscode.spring.pdv.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Integer>{

}
