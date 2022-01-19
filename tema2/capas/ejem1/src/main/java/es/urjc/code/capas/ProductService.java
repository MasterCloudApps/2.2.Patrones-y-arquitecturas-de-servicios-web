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
	
	public List<ProductSummaryDTO> findAll() {
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.map(this::convertToSummaryDto)
				.collect(Collectors.toList());
	}

	public ProductDTO findOne(long id) {
		Product product = productRepository.findById(id).get();

		return convertToDto(product);
	}

	private ProductDTO convertToDto(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}

	private ProductSummaryDTO convertToSummaryDto(Product product) {
		return modelMapper.map(product, ProductSummaryDTO.class);
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
