package org.music.app.codes.account.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login") 
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "login_id", nullable = false, unique = true)
	private Integer loginId;
	
	@Column(name = "login_username", nullable = false, length = 50)
	private String loginUsername;
	
	
	@Column(name = "login_password", nullable = false, length = 255)
	private String loginPassword;
	
	@Column(name = "login_sec_question", length = 150)
	private String loginSecQuestion;
	
	@Column(name = "login_sec_answer", length = 150)
	private String loginSecAnswer;
	
	@OneToOne
	@JoinColumn(name = "login_user_id", referencedColumnName = "user_id")
	@JsonIgnore
	private Users users;
	
	@OneToOne
	@JoinColumn(name = "login_role_id", referencedColumnName = "role_id")
	@JsonIgnore
	private Role role;

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLoginSecQuestion() {
		return loginSecQuestion;
	}

	public void setLoginSecQuestion(String loginSecQuestion) {
		this.loginSecQuestion = loginSecQuestion;
	}

	public String getLoginSecAnswer() {
		return loginSecAnswer;
	}

	public void setLoginSecAnswer(String loginSecAnswer) {
		this.loginSecAnswer = loginSecAnswer;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	



}
