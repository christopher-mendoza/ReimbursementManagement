package dev.mendoza.models;

public class GradeUpload {
	private Integer id;
	private String gradeUp;
	private Boolean approve;
	
	public GradeUpload() {
		super();
	}

	public GradeUpload(String gradeUp, Boolean approve) {
		super();
		this.gradeUp = gradeUp;
		this.approve = approve;
	}

	public GradeUpload(Integer id, String gradeUp, Boolean approve) {
		super();
		this.id = id;
		this.gradeUp = gradeUp;
		this.approve = approve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeUp() {
		return gradeUp;
	}

	public void setGradeUp(String gradeUp) {
		this.gradeUp = gradeUp;
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
		result = prime * result + ((gradeUp == null) ? 0 : gradeUp.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GradeUpload other = (GradeUpload) obj;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (gradeUp == null) {
			if (other.gradeUp != null)
				return false;
		} else if (!gradeUp.equals(other.gradeUp))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GradeUpload [id=" + id + ", gradeUp=" + gradeUp + ", approve=" + approve + "]";
	}
	
	
}
