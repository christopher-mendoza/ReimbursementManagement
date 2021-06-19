package dev.mendoza.models;

public class BCApproval {
	private Integer id;
	private String name;
	private String reason;
	private Boolean approve;
	
	public BCApproval() {
		super();
	}

	public BCApproval(String name, Boolean approve) {
		super();
		this.name = name;
		this.approve = approve;
	}

	public BCApproval(String name, String reason, Boolean approve) {
		super();
		this.name = name;
		this.reason = reason;
		this.approve = approve;
	}

	public BCApproval(Integer id, String name, String reason, Boolean approve) {
		super();
		this.id = id;
		this.name = name;
		this.reason = reason;
		this.approve = approve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getApprove() {
		return approve;
	}

	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		BCApproval other = (BCApproval) obj;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
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
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BCApproval [id=" + id + ", name=" + name + ", reason=" + reason + ", approve=" + approve + "]";
	}

	
}
