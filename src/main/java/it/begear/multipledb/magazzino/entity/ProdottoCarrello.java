package it.begear.multipledb.magazzino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prodottocarrello")
public class ProdottoCarrello {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prodottocarrello", unique = true, nullable = false)
	private Integer idProdottocarrello;
	@Column(name = "quantity")
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "id_prodotto")
	private Prodotto prodotto;
	@ManyToOne
	@JoinColumn(name = "id_carrello")
	private Carrello carrello;
}
