package exam.portal.tn.services;



import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.portal.tn.entities.Compteur;
import exam.portal.tn.repository.CompteurRepository;


@Service
@Transactional
public class CompteurService {
	@Autowired
	CompteurRepository repository;
	public CompteurService() {
		// TODO Auto-generated constructor stub
	}

	public int nbre(int annee) {
		return repository.nbre(annee);
	}

	public void create(int annee) {
		 Compteur cpt = new Compteur();
	        cpt.setAnnee(annee);
	        cpt.setNumpanier(1);
	        repository.save(cpt);
		
	}

	public Optional<Compteur> findByAnnee(int annee) {
		// TODO Auto-generated method stub
		return repository.findByAnnee(annee);
	}

}
