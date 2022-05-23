package com.example.valentinesgarage;

public class Model {

    private String client, client_id, vehicleModel, vehicleCode, noPlate, noReg, description, task, assign, id, date, status;

    // Model(mclient_text, mclient_id_text, vehicleM, vehicleC, no_plate_text, mreg_no_text,mdesc_text, mtask_text, massign_text, id, date);

    public Model() {
    }

    public Model(String client, String client_id, String vehicleModel, String vehicleCode, String noPlate, String noReg, String description, String task, String assign, String id, String date, String status) {
        this.client = client;
        this.client_id = client_id;
        this.vehicleModel = vehicleModel;
        this.vehicleCode = vehicleCode;
        this.noPlate = noPlate;
        this.noReg = noReg;
        this.description = description;
        this.task = task;
        this.assign = assign;
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getNoPlate() {
        return noPlate;
    }

    public void setNoPlate(String noPlate) {
        this.noPlate = noPlate;
    }

    public String getNoReg() {
        return noReg;
    }

    public void setNoReg(String noReg) {
        this.noReg = noReg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
