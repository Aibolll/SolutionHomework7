package AirportTowerSimulator.strategy;

import java.util.Queue;
import AirportTowerSimulator.aircraft.Aircraft;

public class FuelPriorityScheduler implements RunwayScheduler {
    @Override
    public Aircraft selectNextAircraft(Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue) {
        if (!landingQueue.isEmpty()) {
            Aircraft lowestFuel = null;
            for (Aircraft a : landingQueue) {
                if (lowestFuel == null || a.getFuelLevel() < lowestFuel.getFuelLevel()) {
                    lowestFuel = a;
                }
            }
            landingQueue.remove(lowestFuel);
            return lowestFuel;
        } else if (!takeoffQueue.isEmpty()) {
            return takeoffQueue.poll();
        }
        return null;
    }
}

