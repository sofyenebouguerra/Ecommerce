package exam.portal.tn.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="lvente")
public class Lvente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id; 
	private int numero;
	private String code;
	private String libelle;
	private int pv;
	private double qte;
	private int tva;
	private double montht;
	private double monttva;
	private double tottva;
	private double montttc;
	private String modreg;
	@JsonManagedReference
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Vente vente;
	public Lvente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lvente(long id, int numero, String code, String libelle, int pv, double qte, int tva, double montht,
			double monttva, double tottva, double montttc, String modreg, Vente vente) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		this.libelle = libelle;
		this.pv = pv;
		this.qte = qte;
		this.tva = tva;
		this.montht = montht;
		this.monttva = monttva;
		this.tottva = tottva;
		this.montttc = montttc;
		this.modreg = modreg;
		this.vente = vente;
	}
	public Lvente(int numero, String code, String libelle, int pv, double qte, int tva, double montht, double monttva,
			double tottva, double montttc, String modreg, Vente vente) {
		super();
		this.numero = numero;
		this.code = code;
		this.libelle = libelle;
		this.pv = pv;
		this.qte = qte;
		this.tva = tva;
		this.montht = montht;
		this.monttva = monttva;
		this.tottva = tottva;
		this.montttc = montttc;
		this.modreg = modreg;
		this.vente = vente;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public double getQte() {
		return qte;
	}
	public void setQte(double qte) {
		this.qte = qte;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public double getMontht() {
		return montht;
	}
	public void setMontht(double montht) {
		this.montht = montht;
	}
	public double getMonttva() {
		return monttva;
	}
	public void setMonttva(double monttva) {
		this.monttva = monttva;
	}
	public double getTottva() {
		return tottva;
	}
	public void setTottva(double tottva) {
		this.tottva = tottva;
	}
	public double getMontttc() {
		return montttc;
	}
	public void setMontttc(double montttc) {
		this.montttc = montttc;
	}
	public String getModreg() {
		return modreg;
	}
	public void setModreg(String modreg) {
		this.modreg = modreg;
	}
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 

}
