package AirportTowerSimulator.aircraft;

import AirportTowerSimulator.mediator.TowerMediator;

public class CargoPlane extends Aircraft {
    public CargoPlane(String id, int fuelLevel, TowerMediator tower) {
        super(id, fuelLevel, tower);
    }

    @Override
    public void receive(String msg) {
        System.out.println("🚛 CargoPlane " + id + " received: " + msg);
    }

    @Override
    public String getType() {
        return "CargoPlane";
    }
}

