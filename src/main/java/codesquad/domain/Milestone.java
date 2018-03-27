package codesquad.domain;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import support.domain.AbstractEntity;

@Entity
public class Milestone extends AbstractEntity {

	@Size(min = 6, max = 20)
	@Column(nullable = false, length = 20)
	private String title;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startDate;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endDate;

	@OneToMany(mappedBy="milestone", cascade = CascadeType.ALL)
	@OrderBy("id ASC")
	@JsonIgnore
	private List<Issue> issues = new ArrayList<Issue>();;
	
	public Milestone() {
	}

	public Milestone(String title, Date startDate, Date endDate) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Milestone(long id, String title, Date startDate, Date endDate) {
		super(id);
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Issue issue) {
		issues.add(issue);
	}

	public URI createUri() {
		return URI.create("/api/milestones/" + getId());
	}

	@Override
	public String toString() {
		return "Milestone [title=" + title + ", startDate=" + startDate + ", endDate=" + endDate 
				+ "]";
	}

	



}
