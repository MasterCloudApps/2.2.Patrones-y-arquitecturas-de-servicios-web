package es.urjc.code.capas;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private ModelMapper modelMapper;

	public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	
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
