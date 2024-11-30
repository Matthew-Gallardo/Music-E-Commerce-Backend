package org.music.app.codes.account.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "per_id", nullable = false, unique = true)
	private Integer permissionId;
	
	@Column(name = "per_name", nullable = false, length = 50)
	private String permissionName;
	
	@Column(name = "per_module", nullable = false, length = 150)
	private String permissionModule;
	
	@ManyToOne
	@JoinColumn(name = "per_role_id", referencedColumnName = "role_id", nullable = false)
	@JsonIgnore
	private Role role;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionModule() {
		return permissionModule;
	}

	public void setPermissionModule(String permissionModule) {
		this.permissionModule = permissionModule;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

	
}
