package AirportTowerSimulator.mediator;

import AirportTowerSimulator.aircraft.Aircraft;

import java.util.*;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new LinkedList<>();
    private Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private List<Aircraft> aircraftList = new ArrayList<>();
    private boolean isRunwayFree = true;

    public void registerAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
    }

    @Override
    public void broadcast(String msg, Aircraft sender) {
        System.out.println("Tower receives from " + sender.getId() + ": " + msg);

        if (msg.equalsIgnoreCase("MAYDAY")) {
            System.out.println("⚠️ EMERGENCY: " + sender.getId() + " requesting immediate landing!");
            clearRunwayForEmergency(sender);
        } else {
            for (Aircraft a : aircraftList) {
                if (a != sender) {
                    a.receive("From Tower to " + a.getId() + ": " + msg);
                }
            }
        }
    }

    private void clearRunwayForEmergency(Aircraft emergencyAircraft) {
        landingQueue.remove(emergencyAircraft); // in case it's already in queue
        landingQueue.addFirst(emergencyAircraft); // front of the queue
        System.out.println("✅ Emergency aircraft " + emergencyAircraft.getId() + " prioritized for landing.");

        for (Aircraft a : aircraftList) {
            if (!a.equals(emergencyAircraft)) {
                a.receive("HOLD POSITION. Emergency landing in progress.");
            }
        }

        isRunwayFree = true; // force release runway
        processQueues();
    }

    @Override
    public boolean requestRunway(Aircraft aircraft, String action) {
        if (action.equalsIgnoreCase("LAND")) {
            landingQueue.add(aircraft);
        } else if (action.equalsIgnoreCase("TAKEOFF")) {
            takeoffQueue.add(aircraft);
        } else {
            System.out.println("Unknown action from " + aircraft.getId());
            return false;
        }

        processQueues();
        return true;
    }

    private void processQueues() {
        if (!isRunwayFree) return;

        if (!landingQueue.isEmpty()) {
            Aircraft next = landingQueue.poll();
            grantRunway(next, "LANDING");
        } else if (!takeoffQueue.isEmpty()) {
            Aircraft next = takeoffQueue.poll();
            grantRunway(next, "TAKEOFF");
        }
    }

    private void grantRunway(Aircraft aircraft, String action) {
        isRunwayFree = false;
        System.out.println("🛬 Runway granted to " + aircraft.getId() + " for " + action);
        aircraft.receive("Runway granted for " + action);

        // Simulate usage time (you can improve this in simulator)
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isRunwayFree = true;
                processQueues();
            }
        }, 2000);
    }
}
