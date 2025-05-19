public class SmartTv{

    boolean turnedOn;
    int volume;
    int channel;

    public SmartTv(){
        this.turnedOn = false;
        this.volume = 0;
        this.channel = 0;
    }

    public void turnOn(){
        this.turnedOn = true;
    }

    public void turnOff(){
        this.turnedOn = false;
    }

    public boolean isTurnedOn(){
        return this.turnedOn;
    }

    public void setVolume(int volume){
        this.volume = volume;
    }

    public int getVolume(){
        return this.volume;
    }

    public void increaseVolume(){
        this.volume++;
    }

    public void decreaseVolume(){
        this.volume--;
    }

    public void setChannel(int channel){
        this.channel = channel;
    }

    public void increaseChannel(){
        this.channel++;
    }

    public void decreaseChannel(){
        this.channel--;
    }

    public int getChannel(){
        return this.channel;
    }

    public void printStatus(){
        System.out.println("Smart TV is turned on: " + this.turnedOn);
        System.out.println("Volume: " + this.volume);
        System.out.println("Channel: " + this.channel);
    }

}