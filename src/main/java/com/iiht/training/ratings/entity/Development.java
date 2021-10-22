package com.iiht.training.ratings.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="development")
public class Development {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long developmentId;
    private Long politicalLeaderId;
    @NotNull
    @Length(min = 3, max = 100)
    private String title;
    @NotNull
    @Length(min = 3, max = 100)
    private String activity;
    @NotNull
    @Length(min = 3, max = 100)
    private String budget;
    @NotNull
    @Length(min = 3, max = 100)
    private String state;
    @NotNull
    @Range(min = 1, max = 12)
    private Integer activityMonth;
    @NotNull
    @Range(min = 2021, max = 2040)
	private Integer activityYear;

	public Long getDevelopmentId() {
		return developmentId;
	}

	public void setDevelopmentId(Long developmentId) {
		this.developmentId = developmentId;
	}

	public Long getPoliticalLeaderId() {
		return politicalLeaderId;
	}

	public void setPoliticalLeaderId(Long candidateId) {
		this.politicalLeaderId = candidateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getActivityMonth() {
		return activityMonth;
	}

	public void setActivityMonth(Integer activityMonth) {
		this.activityMonth = activityMonth;
	}

	public Integer getActivityYear() {
		return activityYear;
	}

	public void setActivityYear(Integer activityYear) {
		this.activityYear = activityYear;
	}

}
