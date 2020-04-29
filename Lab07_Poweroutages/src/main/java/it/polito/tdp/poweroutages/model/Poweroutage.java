package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Poweroutage {
	
	private int customersAffected;
	private LocalDate dateEventBegan;
	private LocalDate dateEventFinished;
	private LocalTime timeEventBegan;
	private LocalTime timeEventFinished;
	
	private LocalDateTime eventBegan;
	private LocalDateTime eventFinished;
	
	private Duration duration;
	

	public Poweroutage(int customersAffected, LocalDate localDate, LocalTime time, LocalDate localDate2, LocalTime time2) {
		this.customersAffected = customersAffected;
		this.dateEventBegan = localDate;
		this.dateEventFinished = localDate2;
		this.timeEventBegan = time;
		this.timeEventFinished = time2;
		this.eventBegan = this.timeEventBegan.atDate(dateEventBegan);
		this.eventFinished = this.timeEventFinished.atDate(dateEventFinished);
		
		this.duration = Duration.between(eventBegan, eventFinished);
	}

	/*
	 *  days between from and to
        System.out.println(duration.toDays() + " days");

        hours between from and to
        System.out.println(duration.toHours() + " hours");

        minutes between from and to
        System.out.println(duration.toMinutes() + " minutes");
	 */
	
	
	
	public LocalTime getTimeEventBegan() {
		return timeEventBegan;
	}

	public LocalDateTime getEventBegan() {
		return eventBegan;
	}

	public void setEventBegan(LocalDateTime eventBegan) {
		this.eventBegan = eventBegan;
	}

	public LocalDateTime getEventFinished() {
		return eventFinished;
	}

	public void setEventFinished(LocalDateTime eventFinished) {
		this.eventFinished = eventFinished;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public void setTimeEventBegan(LocalTime timeEventBegan) {
		this.timeEventBegan = timeEventBegan;
	}

	public LocalTime getTimeEventFinished() {
		return timeEventFinished;
	}

	public void setTimeEventFinished(LocalTime timeEventFinished) {
		this.timeEventFinished = timeEventFinished;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDate getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDate dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDate getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDate dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customersAffected;
		result = prime * result + ((dateEventBegan == null) ? 0 : dateEventBegan.hashCode());
		result = prime * result + ((dateEventFinished == null) ? 0 : dateEventFinished.hashCode());
		result = prime * result + ((timeEventBegan == null) ? 0 : timeEventBegan.hashCode());
		result = prime * result + ((timeEventFinished == null) ? 0 : timeEventFinished.hashCode());
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
		Poweroutage other = (Poweroutage) obj;
		if (customersAffected != other.customersAffected)
			return false;
		if (dateEventBegan == null) {
			if (other.dateEventBegan != null)
				return false;
		} else if (!dateEventBegan.equals(other.dateEventBegan))
			return false;
		if (dateEventFinished == null) {
			if (other.dateEventFinished != null)
				return false;
		} else if (!dateEventFinished.equals(other.dateEventFinished))
			return false;
		if (timeEventBegan == null) {
			if (other.timeEventBegan != null)
				return false;
		} else if (!timeEventBegan.equals(other.timeEventBegan))
			return false;
		if (timeEventFinished == null) {
			if (other.timeEventFinished != null)
				return false;
		} else if (!timeEventFinished.equals(other.timeEventFinished))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dateEventBegan + " " + timeEventBegan
				+ ", " + dateEventFinished + " " + timeEventFinished + ", " + customersAffected;
	}

}
