package it.begear.multipledb.magazzino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.begear.multipledb.magazzino.entity.Prodotto;
import it.begear.multipledb.magazzino.entity.ProdottoCarrello;
import it.begear.multipledb.magazzino.repository.ProdottoCarrelloRepository;
import it.begear.multipledb.magazzino.repository.ProdottoRepository;

@Service
public class ProdottoService {

	@Autowired
	private ProdottoRepository prodottoRepository;

	@Autowired
	private ProdottoCarrelloRepository prodCarRepository;

	public Prodotto addProdotto(String marca, String modello, Double prezzo) {
		Prodotto prodotto = new Prodotto(null, marca, modello, prezzo);
		return prodottoRepository.save(prodotto);
	}

	public List<Prodotto> getListProdotto(String marca, String modello, Double prezzo) {
		Prodotto prodotto = new Prodotto(null, marca, modello, prezzo);
		Example<Prodotto> prodottoExample = Example.of(prodotto);
		List<Prodotto> listProdotto = prodottoRepository.findAll(prodottoExample);
		return listProdotto;
	}

	public Double getPrezzoTotaleCarrello(Integer id_carrello) {
		List<ProdottoCarrello> listProdCar = prodCarRepository.findByCarrelloIdCarrello(id_carrello);
		Double prezzo_totale = 0D;
		for (ProdottoCarrello id_prodotto : listProdCar) {
			Optional<Prodotto> prodotto = prodottoRepository.findById(id_prodotto.getProdotto().getIdProdotto());
			prezzo_totale += prodotto.get().getPrezzo();
		}
		return prezzo_totale;
	}
}
