package it.begear.multipledb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.begear.multipledb.commerciale.entity.Fattura;
import it.begear.multipledb.commerciale.entity.RigaFattura;
import it.begear.multipledb.commerciale.service.FatturaService;
import it.begear.multipledb.commerciale.service.RigaFatturaService;
import it.begear.multipledb.magazzino.entity.Carrello;
import it.begear.multipledb.magazzino.entity.Prodotto;
import it.begear.multipledb.magazzino.entity.ProdottoCarrello;
import it.begear.multipledb.magazzino.service.CarrelloService;
import it.begear.multipledb.magazzino.service.ProdottoCarrelloService;
import it.begear.multipledb.magazzino.service.ProdottoService;

@RestController
public class AppController {

	@Autowired
	private ProdottoService prodottoService;

	@Autowired
	private CarrelloService carrelloService;

	@Autowired
	private ProdottoCarrelloService prodCarService;

	@Autowired
	private FatturaService fatturaService;

	@Autowired
	private RigaFatturaService rigaFatturaService;

	@PostMapping("/creazioneProdotto")
	public ResponseEntity<Prodotto> creazioneProdotto(@RequestParam String marca, @RequestParam String modello,
			@RequestParam Double prezzo) {
		return ResponseEntity.ok(prodottoService.addProdotto(marca, modello, prezzo));
	}

	@PostMapping("/creazioneCarrello")
	public ResponseEntity<Carrello> creazioneCarrello(@RequestParam Boolean fatturato) {
		return ResponseEntity.ok(carrelloService.creazioneCarrello(fatturato));
	}

	@PostMapping("/aggiungiProdottoAlCarrello")
	public ResponseEntity<ProdottoCarrello> aggiungiProdottoAlCarrello(@RequestParam Integer quantity,
			@RequestParam Integer id_prodotto, @RequestParam Integer id_carrello) {
		return ResponseEntity.ok(prodCarService.aggiungiProdottoAlCarrello(quantity, id_prodotto, id_carrello));
	}

	@PutMapping("/modificaQuantitaProdottoDelCarrello")
	public ResponseEntity<ProdottoCarrello> modificaQuantitaProdottoDelCarrello(@RequestParam Integer quantity,
			@RequestParam Integer id_prodottocarrello) {
		return ResponseEntity.ok(prodCarService.modificaQuantitaProdottoDelCarrello(quantity, id_prodottocarrello));
	}

	@GetMapping("/aggiungiFattura")
	public ResponseEntity<Fattura> aggiungiFattura(@RequestParam Integer id_carrello) {
		return ResponseEntity.ok(fatturaService.aggiungiFattura(id_carrello));
	}

	@GetMapping("/aggiungiRigaFattura")
	public ResponseEntity<RigaFattura> aggiungiRigaFattura(@RequestParam Integer id_carrello,
			@RequestParam Integer id_fattura, @RequestParam Double prezzo, @RequestParam Integer id_prodottocarrello,
			@RequestParam Integer quantity) {
		return ResponseEntity.ok(
				rigaFatturaService.aggiungiRigaFattura(id_carrello, id_fattura, prezzo, quantity, id_prodottocarrello));
	}

	@GetMapping("/getListCarrello")
	public ResponseEntity<List<Carrello>> getListCarrello(@RequestParam(required = false) Boolean fatturato) {
		return ResponseEntity.ok(carrelloService.getListCarrello(fatturato));
	}

	@GetMapping("/getListProdotto")
	public ResponseEntity<List<Prodotto>> getListLibro(@RequestParam(required = false) String marca,
			@RequestParam(required = false) String modello, @RequestParam(required = false) Double prezzo) {
		return ResponseEntity.ok(prodottoService.getListProdotto(marca, modello, prezzo));
	}

	@DeleteMapping("/rimuoviProdottoDalCarrello/{id_prodotto}")
	public void rimuoviProdottoDalCarrello(@PathVariable("id_prodotto") Integer id_prodotto) {
		prodCarService.rimuoviProdottoDalCarrello(id_prodotto);
	}
}
