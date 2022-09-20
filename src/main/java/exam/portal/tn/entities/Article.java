package exam.portal.tn.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id; 
	private String code;
	private String libelle;
	private float pa;
	private float pv;
	private int tva;
	private String ccateg;
	private String filename;
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(long id, String code, String libelle, float pa, float pv, int tva, String ccateg, String filename) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.pa = pa;
		this.pv = pv;
		this.tva = tva;
		this.ccateg = ccateg;
		this.filename = filename;
	}
	public Article(String code, String libelle, float pa, float pv, int tva, String ccateg, String filename) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.pa = pa;
		this.pv = pv;
		this.tva = tva;
		this.ccateg = ccateg;
		this.filename = filename;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public float getPa() {
		return pa;
	}
	public void setPa(float pa) {
		this.pa = pa;
	}
	public float getPv() {
		return pv;
	}
	public void setPv(float pv) {
		this.pv = pv;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public String getCcateg() {
		return ccateg;
	}
	public void setCcateg(String ccateg) {
		this.ccateg = ccateg;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
