package structural.bridge;

public class BasicRemote extends Remote {

    public BasicRemote(Device device) {
        super(device);
    }

    public void pressPower() {
        device.turnOn();
    }
}