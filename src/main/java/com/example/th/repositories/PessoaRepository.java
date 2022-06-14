package com.example.th.repositories;

import com.example.th.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    //Classe repositório herdando Crud
}
