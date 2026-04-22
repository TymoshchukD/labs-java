package creational.builder;

public class House {
    private String walls;
    private String roof;

    public void setWalls(String walls) { this.walls = walls; }
    public void setRoof(String roof) { this.roof = roof; }

    public void show() {
        System.out.println("House with " + walls + " walls and " + roof + " roof");
    }
}