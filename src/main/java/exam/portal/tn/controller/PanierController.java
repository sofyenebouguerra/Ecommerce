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

import exam.portal.tn.entities.Panier;
import exam.portal.tn.services.PanierService;



@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PanierController {
	@Autowired
    private PanierService panierService;
	
 
 @GetMapping("/paniers")
    public List<Panier> list() {
	 System.out.println("Get all paniers...");
             return panierService.getAll();
   }
 	 
 @GetMapping("/paniers/{id}")
 public ResponseEntity<Panier> post(@PathVariable long id) {
        Optional<Panier> cat = panierService.findById(id);
        return cat.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound()
                                              .build());
    }
    
 @PostMapping("/paniers")
    public long save(@RequestBody Panier Panier) {
	 System.out.println("Save Panier...");
        return panierService.save(Panier);
    }

 @PutMapping("/paniers/{code}")
    public void update(@PathVariable long id, @RequestBody Panier Panier) {
     
    }

   
     
}
