package dev.mendoza.models;

public class Department {
	private Integer id;
	private String depName;
	private String supervisor;
	
	public Department() {
		super();
	}

	public Department(String depName, String supervisor) {
		super();
		this.depName = depName;
		this.supervisor = supervisor;
	}

	public Department(Integer id, String depName, String supervisor) {
		super();
		this.id = id;
		this.depName = depName;
		this.supervisor = supervisor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depName == null) ? 0 : depName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((supervisor == null) ? 0 : supervisor.hashCode());
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
		Department other = (Department) obj;
		if (depName == null) {
			if (other.depName != null)
				return false;
		} else if (!depName.equals(other.depName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (supervisor == null) {
			if (other.supervisor != null)
				return false;
		} else if (!supervisor.equals(other.supervisor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", depName=" + depName + ", supervisor=" + supervisor + "]";
	}
	
	
}
