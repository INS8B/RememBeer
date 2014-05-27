package com.isnb.remembeer.DatabaseRemembeer;

import java.util.Date;

public class Drink {
	private long DrinkID;
    private Date StartTime;
    private Date EndTime;
    private Double BloodAlcohol;
    private Date EveningDate;


    public Drink(long drinkID, Date startTime, Date endTime, Double bloodAlcohol, Date eveningDate) {
    	DrinkID = drinkID;
        StartTime = startTime;
        EndTime = endTime;
        BloodAlcohol = bloodAlcohol;
        EveningDate = eveningDate;
    }

    public Drink() {
    }

    public long getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(long drinkID) {
    	DrinkID = drinkID;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
    	StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
    	EndTime = endTime;
    }

    public Double getBloodAlcoholde() {
        return BloodAlcohol;
    }

    public void setBloodAlcohol(Double bloodAlcohol) {
    	BloodAlcohol = bloodAlcohol;
    }

    public Date getEveningDate() {
        return EveningDate;
    }

    public void setEveningDate(Date eveningDate) {
    	EveningDate = eveningDate;
    }

}
