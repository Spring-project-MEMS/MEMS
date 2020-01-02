package com.fixit.areas.ward.models.service;

public class WardServiceModel {

    private Long id;
    private String wardName;
    private String roomNumber;

    // TODO:
    // will add these sets once the service models are done
    /*
    private Set<UserServiceModel> doctors;
    private Set<AppointmentServiceModel> appointments;
    private Set<ExaminationServiceModel> examinations;
    private Set<ResultServiceModel> results;
    */

    public WardServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
