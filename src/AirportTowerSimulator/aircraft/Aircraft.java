package AirportTowerSimulator.aircraft;

import AirportTowerSimulator.mediator.TowerMediator;

public abstract class Aircraft {
    protected String id;
    protected int fuelLevel; // emergency if fuelLevel is very low
    protected TowerMediator tower;

    public Aircraft(String id, int fuelLevel, TowerMediator tower) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.tower = tower;
        this.tower.registerAircraft(this);
    }

    public void send(String msg) {
        tower.broadcast(msg, this);
    }

    public abstract void receive(String msg);
    public abstract String getType();

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }
}

