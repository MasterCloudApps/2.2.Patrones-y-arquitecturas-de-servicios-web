package es.codeurjc.books.infrastructure.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = "comments")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nick;

	@Column(nullable = false)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Collection<CommentEntity> comments = Collections.emptyList();

}
