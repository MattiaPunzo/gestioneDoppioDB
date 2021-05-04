package it.begear.multipledb.commerciale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "rigafattura")
public class RigaFattura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rigafattura", unique = true, nullable = false)
	private Integer idRigafattura;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "prezzo")
	private Double prezzo;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fattura")
	private Fattura fattura;
}
