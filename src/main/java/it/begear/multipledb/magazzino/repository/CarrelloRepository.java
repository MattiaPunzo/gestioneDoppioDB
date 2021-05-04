package it.begear.multipledb.magazzino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.begear.multipledb.magazzino.entity.Carrello;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

}
