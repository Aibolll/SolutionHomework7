package AirportTowerSimulator.aircraft;

import AirportTowerSimulator.mediator.TowerMediator;

public abstract class Aircraft {
    protected String id;
    protected int fuelLevel;
    protected TowerMediator tower;
    protected String status; // Добавляем поле status

    public Aircraft(String id, int fuelLevel, TowerMediator tower) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.tower = tower;
        this.status = fuelLevel < 20 ? "LANDING" : Math.random() > 0.5 ? "LANDING" : "TAKEOFF"; // Инициализация status
        this.tower.registerAircraft(this);
    }

    public void send(String msg) {
        tower.broadcast(msg, this);
    }

    public void reduceFuel(int i) {
        if (fuelLevel > 0) {
            fuelLevel -= 5;
            if (fuelLevel < 0) fuelLevel = 0;
        }
    }

    public abstract void receive(String msg);
    public abstract String getType();

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public String getStatus() { // Добавляем метод getStatus
        return status;
    }
}