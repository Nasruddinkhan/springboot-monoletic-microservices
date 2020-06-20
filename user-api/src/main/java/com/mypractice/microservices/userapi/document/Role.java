package com.mypractice.microservices.userapi.document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * nasru - Jun 20, 2020
 *  Role.java 
 */
import com.mypractice.microservices.userapi.enmus.UserRole;
@Document(collection = "roles")
public class Role {
	@Id
	private String id;
	private UserRole name;
	public Role(String id, UserRole name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserRole getName() {
		return name;
	}
	public void setName(UserRole name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
