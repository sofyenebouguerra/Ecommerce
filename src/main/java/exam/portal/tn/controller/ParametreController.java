package exam.portal.tn.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.portal.tn.entities.Parametre;
import exam.portal.tn.services.ParametreService;
;
@CrossOrigin("*")

@RestController
@RequestMapping("/para")
public class ParametreController {
	 @Autowired
	    private ParametreService paramService;
	 
	 @GetMapping("/parametres")
	    public List<Parametre> list() {
		 System.out.println("Get all Parametres...");
	             return paramService.getAll();
	   }
	 	 
	 @GetMapping("/parametres/{id}")
	 public ResponseEntity<Parametre> post(@PathVariable long id) {
	        Optional<Parametre> param = paramService.findById(id);
	        return param.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                            .build());
	    }
	    
	 @PostMapping("/parametres")
	    public long save(@RequestBody Parametre Parametre) {
		 
	        return paramService.save(Parametre);
	    }

	 @PutMapping("/parametres/{id}")
	    public void update(@PathVariable long id, @RequestBody Parametre Parametre) {
	        Optional<Parametre> four = paramService.findById(id);
	        if (four.isPresent()) {
	            paramService.update(id, Parametre);
	        }
	    }

	    @DeleteMapping("/parametres/{id}")
	    public void delete(@PathVariable long id) {
	        paramService.delete(id);
	    }


}
