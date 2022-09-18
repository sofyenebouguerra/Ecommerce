package exam.portal.tn.controller;

import java.awt.PageAttributes;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import exam.portal.tn.entities.ImageModel;
import exam.portal.tn.entities.Product;
import exam.portal.tn.services.IProductServices;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	IProductServices iProductServices;
	
	@PostMapping(value={"/"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addProduct(@RequestPart("product") Product pro,
			@RequestPart("imageFile") MultipartFile[] file) {
		//return iProductServices.addProduct(pro);
		try {
			Set<ImageModel> images=uploadImage(file);
			pro.setProductImages(images);
			return iProductServices.addProduct(pro);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		
	}
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> imageModels=new HashSet<>();
		for(MultipartFile file:multipartFiles) {
			ImageModel imageModel=new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes());
		imageModels.add(imageModel);
		
		}
		return imageModels;
	}
}
