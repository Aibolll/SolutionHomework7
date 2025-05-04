package AirportTowerSimulator.mediator;

import AirportTowerSimulator.aircraft.Aircraft;

public interface TowerMediator {
    void broadcast(String msg, Aircraft sender);
    boolean requestRunway(Aircraft aircraft, String action); // "LAND" or "TAKEOFF"
    void registerAircraft(Aircraft aircraft);
}

