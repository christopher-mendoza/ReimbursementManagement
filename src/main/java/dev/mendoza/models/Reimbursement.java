package dev.mendoza.models;

import java.io.File;

/*
 * TO ADD:
 * -File dsEmail
 * -File dhEmail
 * -File bcEmail
 * 
 */
public class Reimbursement {
	private Integer id;
	private String name;
	private String username;
	private Event event;
	private String workJust;
	private GradingFormat gradingFormat;
	private DSApproval dsApproval;
	private DHApproval dhApproval;
	private BCApproval bcApproval;
	private Boolean fullApprove;
	private Float missedWork;
	private GradeUpload gUp;
	private PresentationUpload pUp;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(String name, String username, Event event, String workJust, Float missedWork) {
		super();
		this.name = name;
		this.username = username;
		this.event = event;
		this.workJust = workJust;
		this.missedWork = missedWork;
	}

	public Reimbursement(Integer id, String name, String username, Event event, String workJust, Float missedWork) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.event = event;
		this.workJust = workJust;
		this.missedWork = missedWork;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getWorkJust() {
		return workJust;
	}

	public void setWorkJust(String workJust) {
		this.workJust = workJust;
	}
	
	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public DSApproval getDsApproval() {
		return dsApproval;
	}

	public void setDsApproval(DSApproval dsApproval) {
		this.dsApproval = dsApproval;
	}

	public DHApproval getDhApproval() {
		return dhApproval;
	}

	public void setDhApproval(DHApproval dhApproval) {
		this.dhApproval = dhApproval;
	}

	public BCApproval getBcApproval() {
		return bcApproval;
	}

	public void setBcApproval(BCApproval bcApproval) {
		this.bcApproval = bcApproval;
	}

	public Boolean getFullApprove() {
		return fullApprove;
	}

	public void setFullApprove(Boolean fullApprove) {
		this.fullApprove = fullApprove;
	}

	public Float getMissedWork() {
		return missedWork;
	}

	public void setMissedWork(Float missedWork) {
		this.missedWork = missedWork;
	}

	public GradeUpload getgUp() {
		return gUp;
	}

	public void setgUp(GradeUpload gUp) {
		this.gUp = gUp;
	}

	public PresentationUpload getpUp() {
		return pUp;
	}

	public void setpUp(PresentationUpload pUp) {
		this.pUp = pUp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bcApproval == null) ? 0 : bcApproval.hashCode());
		result = prime * result + ((dhApproval == null) ? 0 : dhApproval.hashCode());
		result = prime * result + ((dsApproval == null) ? 0 : dsApproval.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((fullApprove == null) ? 0 : fullApprove.hashCode());
		result = prime * result + ((gUp == null) ? 0 : gUp.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((missedWork == null) ? 0 : missedWork.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pUp == null) ? 0 : pUp.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((workJust == null) ? 0 : workJust.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (bcApproval == null) {
			if (other.bcApproval != null)
				return false;
		} else if (!bcApproval.equals(other.bcApproval))
			return false;
		if (dhApproval == null) {
			if (other.dhApproval != null)
				return false;
		} else if (!dhApproval.equals(other.dhApproval))
			return false;
		if (dsApproval == null) {
			if (other.dsApproval != null)
				return false;
		} else if (!dsApproval.equals(other.dsApproval))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (fullApprove == null) {
			if (other.fullApprove != null)
				return false;
		} else if (!fullApprove.equals(other.fullApprove))
			return false;
		if (gUp == null) {
			if (other.gUp != null)
				return false;
		} else if (!gUp.equals(other.gUp))
			return false;
		if (gradingFormat == null) {
			if (other.gradingFormat != null)
				return false;
		} else if (!gradingFormat.equals(other.gradingFormat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (missedWork == null) {
			if (other.missedWork != null)
				return false;
		} else if (!missedWork.equals(other.missedWork))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pUp == null) {
			if (other.pUp != null)
				return false;
		} else if (!pUp.equals(other.pUp))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (workJust == null) {
			if (other.workJust != null)
				return false;
		} else if (!workJust.equals(other.workJust))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", name=" + name + ", username=" + username + ", event=" + event
				+ ", workJust=" + workJust + ", gradingFormat=" + gradingFormat + ", dsApproval=" + dsApproval
				+ ", dhApproval=" + dhApproval + ", bcApproval=" + bcApproval + ", fullApprove=" + fullApprove
				+ ", missedWork=" + missedWork + ", gUp=" + gUp + ", pUp=" + pUp + "]";
	}

	
	
}
