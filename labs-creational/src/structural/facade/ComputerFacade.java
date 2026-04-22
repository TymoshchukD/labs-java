package structural.facade;

public class ComputerFacade {
    private CPU cpu = new CPU();
    private Memory memory = new Memory();

    public void start() {
        cpu.start();
        memory.load();
    }
}