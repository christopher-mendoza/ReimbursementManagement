package dev.mendoza.models;

public class GradingFormat {
	private Integer id;
	private String gFormatName;
	private String grade;
	private Boolean pass;
	
	public GradingFormat() {
		super();
	}

	public GradingFormat(String gFormatName, String grade, Boolean pass) {
		super();
		this.gFormatName = gFormatName;
		this.grade = grade;
		this.pass = pass;
	}

	public GradingFormat(Integer id, String gFormatName, String grade, Boolean pass) {
		super();
		this.id = id;
		this.gFormatName = gFormatName;
		this.grade = grade;
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getgFormatName() {
		return gFormatName;
	}

	public void setgFormatName(String gFormatName) {
		this.gFormatName = gFormatName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gFormatName == null) ? 0 : gFormatName.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
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
		GradingFormat other = (GradingFormat) obj;
		if (gFormatName == null) {
			if (other.gFormatName != null)
				return false;
		} else if (!gFormatName.equals(other.gFormatName))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GradingFormat [id=" + id + ", gFormatName=" + gFormatName + ", grade=" + grade + ", pass=" + pass + "]";
	}
	
	
}
