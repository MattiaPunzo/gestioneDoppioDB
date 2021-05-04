package it.begear.multipledb.magazzino.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.begear.multipledb.magazzino.entity.Carrello;
import it.begear.multipledb.magazzino.entity.Prodotto;
import it.begear.multipledb.magazzino.entity.ProdottoCarrello;
import it.begear.multipledb.magazzino.repository.ProdottoCarrelloRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdottoCarrelloService {

	@Autowired
	private ProdottoCarrelloRepository prodCarRepository;

	public ProdottoCarrello aggiungiProdottoAlCarrello(Integer quantity, Integer id_prodotto, Integer id_carrello) {
		ProdottoCarrello prodCarEx = new ProdottoCarrello();
		Prodotto prodotto = new Prodotto(id_prodotto, null, null, null);
		Carrello carrello = new Carrello(id_carrello, null);
		prodCarEx.setProdotto(prodotto);
		prodCarEx.setCarrello(carrello);
		Optional<ProdottoCarrello> prodCarrello = prodCarRepository.findOne(Example.of(prodCarEx));
		ProdottoCarrello prodCar;
		if (prodCarrello.isPresent()) {
			prodCar = prodCarrello.get();
			prodCar.setQuantity(prodCar.getQuantity() + quantity);
		} else {
			prodCar = new ProdottoCarrello(null, quantity, prodotto, carrello);
		}
		return prodCarRepository.save(prodCar);
	}

	public void rimuoviProdottoDalCarrello(Integer id_prodotto) {
		Optional<ProdottoCarrello> prodCarOp = prodCarRepository.findById(id_prodotto);
		if (prodCarOp.isPresent()) {
			prodCarRepository.delete(prodCarOp.get());
		} else {
			log.info("il prodotto non è presente nel carrello");
		}

	}

	public ProdottoCarrello modificaQuantitaProdottoDelCarrello(Integer quantity, Integer id_prodottocarrello) {
		Optional<ProdottoCarrello> prodCarOp = prodCarRepository.findById(id_prodottocarrello);
		if (prodCarOp.isPresent()) {
			ProdottoCarrello prodCar;
			prodCar = prodCarOp.get();
			prodCar.setQuantity(quantity);
			return prodCarRepository.save(prodCar);
		}
		return null;
	}

}