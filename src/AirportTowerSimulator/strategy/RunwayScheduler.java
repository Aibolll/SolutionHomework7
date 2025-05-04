package AirportTowerSimulator.strategy;

import java.util.Queue;
import AirportTowerSimulator.aircraft.Aircraft;

public interface RunwayScheduler {
    Aircraft selectNextAircraft(Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue);
}
