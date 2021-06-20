package dev.mendoza.models;

import java.io.File;
import java.util.Arrays;

public class PresentationUpload {
	private Integer id;
	private byte[] presUp;
	
	public PresentationUpload() {
		super();
	}

	public PresentationUpload(byte[] presUp) {
		super();
		this.presUp = presUp;
	}

	public PresentationUpload(Integer id, byte[] presUp) {
		super();
		this.id = id;
		this.presUp = presUp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getPresUp() {
		return presUp;
	}

	public void setPresUp(byte[] presUp) {
		this.presUp = presUp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(presUp);
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(presUp, other.presUp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PresentationUpload [id=" + id + ", presUp=" + Arrays.toString(presUp) + "]";
	}
	
	
	
}
