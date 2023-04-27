package com.writeup.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
 @Setter
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false ,length=100)
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String about;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
	
	

	

}
