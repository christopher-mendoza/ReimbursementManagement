package dev.mendoza.models;

public class GradeUpload {
	private Integer id;
	private String gradeFormat;
	private String gradeUp;
	
	public GradeUpload() {
		super();
	}

	public GradeUpload(String gradeFormat, String gradeUp) {
		super();
		this.gradeFormat = gradeFormat;
		this.gradeUp = gradeUp;
	}
	
	public GradeUpload(Integer id, String gradeFormat, String gradeUp) {
		super();
		this.id = id;
		this.gradeFormat = gradeFormat;
		this.gradeUp = gradeUp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeFormat() {
		return gradeFormat;
	}


	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public String getGradeUp() {
		return gradeUp;
	}

	public void setGradeUp(String gradeUp) {
		this.gradeUp = gradeUp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradeFormat == null) ? 0 : gradeFormat.hashCode());
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
		if (gradeFormat == null) {
			if (other.gradeFormat != null)
				return false;
		} else if (!gradeFormat.equals(other.gradeFormat))
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
		return "GradeUpload [id=" + id + ", gradeFormat=" + gradeFormat + ", gradeUp=" + gradeUp + "]";
	}
	
	
	
}
