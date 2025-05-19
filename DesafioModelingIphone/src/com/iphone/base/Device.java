package com.iphone.base;

public abstract class Device {
    protected String model;
    protected String serialNumber;

    public Device(String model, String serialNumber) {
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public abstract void turnOnDevice();
    public abstract void turnOffDevice();
}
