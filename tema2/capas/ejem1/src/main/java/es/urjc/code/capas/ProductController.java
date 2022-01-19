package es.urjc.code.capas;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public List<ProductSummaryDTO> findAll() {

		return productService.findAll();
		
	}

	@GetMapping("/{id}")
	public List<ProductDTO> find(long id) {

		return productService.findOne(id);
		
	}

}
