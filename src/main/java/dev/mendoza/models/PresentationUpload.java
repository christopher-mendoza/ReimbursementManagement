package dev.mendoza.models;

import java.io.File;

public class PresentationUpload {
	private Integer id;
	private File presUp;
	private Boolean approve;
	
	public PresentationUpload() {
		super();
	}

	public PresentationUpload(File presUp, Boolean approve) {
		super();
		this.presUp = presUp;
		this.approve = approve;
	}

	public PresentationUpload(Integer id, File presUp, Boolean approve) {
		super();
		this.id = id;
		this.presUp = presUp;
		this.approve = approve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public File getPresUp() {
		return presUp;
	}

	public void setPresUp(File presUp) {
		this.presUp = presUp;
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
		PresentationUpload other = (PresentationUpload) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "PresentationUpload [id=" + id + ", approve=" + approve + "]";
	}
	
	
}
