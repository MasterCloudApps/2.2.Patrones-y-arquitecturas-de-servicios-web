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
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public List<ProductDTO> findAll() {
		
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	private ProductDTO convertToDto(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	@PostConstruct
	public void init() {
		Product p = new Product();
		p.setName("Laptop");
		p.setDetails("16Gb RAM, 256 GB SSD");
		p.setProvider("MyLaptops, Inc");
		productRepository.save(p);
	}
	
}
