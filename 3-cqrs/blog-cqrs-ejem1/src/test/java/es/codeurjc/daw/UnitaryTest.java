package es.codeurjc.daw;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.codeurjc.daw.model.Comment;
import es.codeurjc.daw.model.Post;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Unitary tests")
public class UnitaryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Crear un post y verificar que se crea correctamente")
	public void createPostTest() throws Exception{

		// CREAMOS UN NUEVO POST

		Post post = new Post("Mi titulo", "Mi contenido");
		
		MvcResult result = mvc.perform(
			post("/api/post")
				.content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)
		  )
		  .andExpect(status().isCreated())
		  .andExpect(jsonPath("$.title", equalTo(post.getTitle())))
		  .andReturn();

		Post resultPost = objectMapper.readValue(
			result.getResponse().getContentAsString(), 
			Post.class
		);

		// COMPROBAMOS QUE EL POST SE HA CREADO CORRECTAMENTE
		  
		mvc.perform(
			get("/api/post/"+resultPost.getId())
				.content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)
		  )
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.title", equalTo(post.getTitle())));
		
	}

	@Test
	@DisplayName("Añadir un comentario a un post y verificar que se añade el comentario")
	public void createCommentTest() throws Exception {

		// CREAMOS UN NUEVO POST

		Post post = new Post("Mi titulo", "Mi contenido con comentarios");
		
		MvcResult createPostResult = mvc.perform(
			post("/api/post")
				.content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)
		  )
		  .andReturn();

		Post resultPost = objectMapper.readValue(
			createPostResult.getResponse().getContentAsString(), 
			Post.class
		);

		// CREAMOS UN NUEVO COMENTARIO

		Comment comment = new Comment("Juan", "Buen post");

		mvc.perform(
			post("/api/post/"+resultPost.getId()+"/comment")
				.content(objectMapper.writeValueAsString(comment))
				.contentType(MediaType.APPLICATION_JSON)
		  )
	      .andExpect(status().isCreated())
		  .andExpect(jsonPath("$.author", equalTo(comment.getAuthor())))
		  .andExpect(jsonPath("$.message", equalTo(comment.getMessage())));

		// COMPROBAMOS QUE EL COMENTARIO EXISTE

		mvc.perform(
			get("/api/post/"+resultPost.getId())
				.content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)
		  )
	      .andExpect(status().isOk())
		  .andExpect(jsonPath("$.comments[0].author", equalTo(comment.getAuthor())))
		  .andExpect(jsonPath("$.comments[0].message", equalTo(comment.getMessage())));
		  ;	
	}

	@Test
	@DisplayName("Borrar un comentario de un post y verificar que no aparece el comentario")
	public void deleteCommentTest() throws Exception {

		// CREAMOS UN NUEVO POST

		Post post = new Post("Mi titulo", "Mi contenido con comentarios");
		
		MvcResult createPostResult = mvc.perform(
			post("/api/post")
				.content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)
		  )
		  .andReturn();

		Post postCreated = objectMapper.readValue(
			createPostResult.getResponse().getContentAsString(), 
			Post.class
		);

		// CREAMOS UN NUEVO COMENTARIO

		Comment comment = new Comment("Juan", "Buen post");

		MvcResult createCommentResult = mvc.perform(
			post("/api/post/"+postCreated.getId()+"/comment")
				.content(objectMapper.writeValueAsString(comment))
				.contentType(MediaType.APPLICATION_JSON)
		).andReturn();

		Comment commentCreated = objectMapper.readValue(
			createCommentResult.getResponse().getContentAsString(), 
			Comment.class
		);

		// BORRAMOS EL COMENTARIO

		mvc.perform(
			delete("/api/post/"+postCreated.getId()+"/comment/"+commentCreated.getId())
				.content(objectMapper.writeValueAsString(comment))
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isNoContent());

		// COMPROBAMOS QUE EL COMENTARIO YA NO EXISTE

		mvc.perform(
			get("/api/post/"+postCreated.getId())
				.content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)
		  )
	      .andExpect(status().isOk())
		  .andExpect(jsonPath("$.comments",  IsEmptyCollection.empty()))
		  ;		

	}

}
