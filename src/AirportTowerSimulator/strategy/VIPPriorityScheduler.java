package AirportTowerSimulator.strategy;

import java.util.Queue;
import AirportTowerSimulator.aircraft.Aircraft;


public class VIPPriorityScheduler implements RunwayScheduler {
    @Override
    public Aircraft selectNextAircraft(Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue) {
        for (Aircraft a : landingQueue) {
            if (a.getType().equalsIgnoreCase("PassengerPlane")) {
                landingQueue.remove(a);
                return a;
            }
        }

        if (!landingQueue.isEmpty()) {
            return landingQueue.poll();
        }

        if (!takeoffQueue.isEmpty()) {
            return takeoffQueue.poll();
        }

        return null;
    }
}

