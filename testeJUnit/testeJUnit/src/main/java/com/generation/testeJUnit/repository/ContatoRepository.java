package com.generation.testeJUnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.testeJUnit.model.ContatoModel;

public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {

}
