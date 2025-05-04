package AirportTowerSimulator.aircraft;

import AirportTowerSimulator.mediator.TowerMediator;

public class Helicopter extends Aircraft {
    public Helicopter(String id, int fuelLevel, TowerMediator tower) {
        super(id, fuelLevel, tower);
    }

    @Override
    public void receive(String msg) {
        System.out.println("🚁 Helicopter " + id + " received: " + msg);
    }

    @Override
    public String getType() {
        return "Helicopter";
    }
}
