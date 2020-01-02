package com.fixit.areas.ward.models.view;

public class WardViewModel {

    private Long id;
    private String wardName;
    private String roomNumber;


    // TODO:
    // can add these sets once the view models are done
    // if we are not gonna use them we can get rid of the sets
    /*
    private Set<UserViewModel> doctors;
    private Set<AppointmentViewModel> appointments;
    private Set<ExaminationViewModel> examinations;
    private Set<ResultViewModel> results;
    */

    public WardViewModel() {
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
