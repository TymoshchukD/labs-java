package creational.builder;

public class HouseBuilder {
    private House house = new House();

    public HouseBuilder buildWalls() {
        house.setWalls("brick");
        return this;
    }

    public HouseBuilder buildRoof() {
        house.setRoof("tile");
        return this;
    }

    public House getResult() {
        return house;
    }
}