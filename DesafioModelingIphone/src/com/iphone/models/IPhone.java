package com.iphone.models;

import com.iphone.interfaces.TelephoneDevice;
import com.iphone.interfaces.InternetNavigator;
import com.iphone.interfaces.MusicReproduct;
import com.iphone.base.Device;

public class IPhone extends Device implements MusicReproduct, InternetNavigator, TelephoneDevice {

    public IPhone(String model, String serialNumber) {
        super(model, serialNumber);
    }

    public void callDevice(String num) {
    }

    public void answerDevice() {
    }

    public void startVoicemail() {
    }

    public void showPage(String url) {
    }

    public void addNewTab() {
    }

    public void refreshPage() {
    }

    public void playMusic() {
    }

    public void stopMusic() {
    }

    public void selectMusic(String music) {
    }

    public void turnOnDevice() {
    }

    public void turnOffDevice() {
    }

}
