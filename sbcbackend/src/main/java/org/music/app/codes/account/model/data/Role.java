package org.music.app.codes.account.model.data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "role_id", nullable = false, unique = true)
	private Integer roleId;
	
	@Column(name = "role_name", nullable = false, length = 50)
	private String roleName;
	
	@Column(name = "role_desc", nullable = false, length = 150)
	private String roleDescription;

	
	@OneToOne(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Login login;
	
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Permission> permissions;


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getRoleDescription() {
		return roleDescription;
	}


	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	


	
}
