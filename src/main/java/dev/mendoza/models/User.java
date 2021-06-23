package dev.mendoza.models;

import java.sql.Timestamp;

public class User {
	private Integer id;
	private Department department;
	private String name;
	private String username;
	private String password;
	private Float reAmount;
	private Boolean dsAdmin;
	private Boolean dhAdmin;
	private Boolean bcAdmin;
	
	public User() {
		super();
	}

	public User(Integer id, Department department, String name, String username, String password, Float reAmount) {
		super();
		this.id = id;
		this.department = department;
		this.name = name;
		this.username = username;
		this.password = password;
		this.reAmount = reAmount;
	}

	public User(Department department, String name, String username, String password, Float reAmount) {
		super();
		this.department = department;
		this.name = name;
		this.username = username;
		this.password = password;
		this.reAmount = reAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getReAmount() {
		return reAmount;
	}

	public void setReAmount(Float reAmount) {
		this.reAmount = reAmount;
	}

	public Boolean getDsAdmin() {
		return dsAdmin;
	}

	public void setDsAdmin(Boolean dsAdmin) {
		this.dsAdmin = dsAdmin;
	}

	public Boolean getDhAdmin() {
		return dhAdmin;
	}

	public void setDhAdmin(Boolean dhAdmin) {
		this.dhAdmin = dhAdmin;
	}

	public Boolean getBcAdmin() {
		return bcAdmin;
	}

	public void setBcAdmin(Boolean bcAdmin) {
		this.bcAdmin = bcAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bcAdmin == null) ? 0 : bcAdmin.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((dhAdmin == null) ? 0 : dhAdmin.hashCode());
		result = prime * result + ((dsAdmin == null) ? 0 : dsAdmin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((reAmount == null) ? 0 : reAmount.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (bcAdmin == null) {
			if (other.bcAdmin != null)
				return false;
		} else if (!bcAdmin.equals(other.bcAdmin))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (dhAdmin == null) {
			if (other.dhAdmin != null)
				return false;
		} else if (!dhAdmin.equals(other.dhAdmin))
			return false;
		if (dsAdmin == null) {
			if (other.dsAdmin != null)
				return false;
		} else if (!dsAdmin.equals(other.dsAdmin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (reAmount == null) {
			if (other.reAmount != null)
				return false;
		} else if (!reAmount.equals(other.reAmount))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", department=" + department + ", name=" + name + ", username=" + username
				+ ", password=" + password + ", reAmount=" + reAmount + ", dsAdmin=" + dsAdmin
				+ ", dhAdmin=" + dhAdmin + ", bcAdmin=" + bcAdmin + "]";
	}
	
	
	
	
}
