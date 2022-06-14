package com.example.th.repositories;

import com.example.th.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PessoaRepository extends MongoRepository<Pessoa, Long> {
    //Classe repositório herdando Mongo
}
