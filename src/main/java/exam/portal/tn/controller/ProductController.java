package exam.portal.tn.controller;

import java.awt.PageAttributes;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import exam.portal.tn.entities.ImageModel;
import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.ProductExcel;
import exam.portal.tn.repository.ProductRepository;
import exam.portal.tn.services.IProductServices;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
//@PreAuthorize("hasRole('ADMIN')")
public class ProductController {
	
	@Autowired  ProductRepository productRepository;
	
	@Autowired
	IProductServices iProductServices;
	@Autowired  ServletContext context;
	
	
	@PostMapping(value={"/"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) {
		//return iProductServices.addProduct(pro);
		try {
			Set<ImageModel> images=uploadImage(file);
			product.setProductImages(images);
			return iProductServices.addProduct(product);
			
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
		
		 @PostMapping("/prodIma")
		 public Product createProduct (@RequestParam("file") MultipartFile file,
				 @RequestParam("product") String product) throws JsonParseException , JsonMappingException , Exception
		 {
			 System.out.println("Save Product.............");
           Product prod = new ObjectMapper().readValue(product, Product.class);
		    boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
		    if (!isExit)
		    {
		    	new File (context.getRealPath("/Imagess/")).mkdir();
		    	System.out.println("mk dir Images.............");
		    }
		    System.out.println("Save Product  22222.............");
		    String filename = file.getOriginalFilename();
		    String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		    File serverFile = new File (context.getRealPath("/Imagess/"+File.separator+newFileName));
		    try
		    {
		    	System.out.println("Image");
		    	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
		    	 
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    System.out.println("Save Product 333333.............");
		    prod.setFileName(newFileName);
		    return iProductServices.addProduct(prod);
		 }
		 @PostMapping("/addProductToCategory/{idCategory}")
			Product addProductToCategory(@PathVariable long idCategory , @RequestParam("file") MultipartFile file,
					 @RequestParam("product") String product) throws JsonParseException , JsonMappingException , Exception
			 {
				 System.out.println("addProductToCategory.............");
	          Product prod = new ObjectMapper().readValue(product, Product.class);
			    boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
			    if (!isExit)
			    {
			    	new File (context.getRealPath("/Imagess/")).mkdir();
			    	System.out.println("mk dir Images.............");
			    }
			    System.out.println("Save Product  22222.............");
			    String filename = file.getOriginalFilename();
			    String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
			    File serverFile = new File (context.getRealPath("/Imagess/"+File.separator+newFileName));
			    try
			    {
			    	System.out.println("Image");
			    	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
			    	 
			    }catch(Exception e) {
			    	e.printStackTrace();
			    }
			    System.out.println("Save Product 333333.............");
			    prod.setFileName(newFileName);
			   
			 
				return iProductServices.addProductToCategory(prod, idCategory);
			}
		 
		 @GetMapping(path="/Imgproduct/{productId}")
		 public byte[] getPhoto(@PathVariable("productId") Long productId) throws Exception{
			 Product Prodct =iProductServices.GetProduct(productId);
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+Prodct.getFileName()));
		 }

			
	
	/*@GetMapping(path="/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long productId) throws Exception{
		Set<ImageModel> imageModels=new HashSet<>();
		
		 Product product =iProductServices.GetProduct(productId);
		 
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+product.getProductImages()));
	 }*/
	
	
	
	@GetMapping("/GetAll")
	public List<Product> GetAllPro(){
		 System.out.println("Get all Products...");
		return iProductServices.GetAllPro();
	}
	@DeleteMapping("/DelPro/{productId}")
	public void DeleteProDetail(@PathVariable("productId") Long productId) {
		iProductServices.DeleteProDetail(productId);
	}
	
	@GetMapping("/GetOne/{productId}")
	public Product GetProduct(@PathVariable ("productId") Long productId) {
		return iProductServices.GetProduct(productId);
	}
	
	
	@PutMapping("/UpdatePro")
    public Product update( @RequestBody Product product) {
		return iProductServices.updatePro(product);
     
    }
	 @GetMapping("/products/export/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	    	System.out.println("Export to Excel ...");
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        List<Product> listArticles = iProductServices.GetAllPro();
	        ProductExcel excel = new ProductExcel(listArticles);
	        excel.export(response);    
	    }  
	 
	 @GetMapping("/Jasper/report")
	    public String generateReport() throws FileNotFoundException, JRException {
		 System.out.println("bak marche");
	        return iProductServices.exportReport();
	    }
	
	 
	 
	 
		

		
		@PutMapping("/editProduct/{productId}")
		Product editProduct(@RequestBody Product product, @PathVariable long productId) {
			 return iProductServices.editProduct(product, productId);
		}

		@GetMapping("/findProductById/{id}")
		Product findProductById(@PathVariable Long id) {
			return iProductServices.findProductById(id);
		}

		@DeleteMapping("/deleteProduct/{id}")
		void deleteProduct(@PathVariable long id) {
			iProductServices.deleteProduct(id);
		}

		@GetMapping("/findAllProducts")
		List<Product> findAllProducts() {
			return iProductServices.findAllProducts();
		}

		@GetMapping("/findProductsForCategory/{idCategory}")
		List<Product> findProductsForCategory(@PathVariable long idCategory) {
			return iProductServices.findProductsForCategory(idCategory);
		}

		@GetMapping("/findByName/{name}")
		List<Product> findByName(@PathVariable String name) {
			return productRepository.findByProductName1("%" + name + "%");
		}

}
