package it.begear.multipledb.magazzino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prodotto")
public class Prodotto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prodotto", unique = true, nullable = false)
	private Integer idProdotto;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@Column(name = "prezzo")
	private Double prezzo;
}
