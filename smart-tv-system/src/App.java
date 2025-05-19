public class App {
    public static void main(String[] args) throws Exception {

        SmartTv tv = new SmartTv();
        tv.printStatus();
        System.out.println("-----------------");
        tv.turnOn();
        tv.setVolume(10);
        tv.setChannel(5);
        tv.printStatus();
        System.out.println("-----------------");
        tv.increaseVolume();
        tv.increaseChannel();
        tv.printStatus();
        System.out.println("-----------------");
        tv.decreaseVolume();
        tv.decreaseChannel();
        tv.printStatus();
        System.out.println("-----------------");
        tv.turnOff();
        tv.printStatus();
        System.out.println("-----------------");

    }
}
