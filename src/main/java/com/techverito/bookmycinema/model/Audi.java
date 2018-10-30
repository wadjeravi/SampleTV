package com.techverito.bookmycinema.model;

import java.util.List;
import java.util.Set;

public class Audi {
    Integer id;
    String audiName;
    String showName;
    Set<String> selectedSeatsList;

    public Audi(Integer id, String audiName, String showName, Set<String> selectedSeatsList){
        this.id = id;
        this.audiName = audiName;
        this.showName = showName;
        this.selectedSeatsList = selectedSeatsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Set<String> getSelectedSeatsList() {
        return selectedSeatsList;
    }

    public void setSelectedSeatsList(Set<String> selectedSeatsList) {
        this.selectedSeatsList = selectedSeatsList;
    }
    public String getAudiName() {
        return audiName;
    }

    public void setAudiName(String audiName) {
        this.audiName = audiName;
    }
}
