package AirportTowerSimulator.simulation;

import AirportTowerSimulator.aircraft.*;
import AirportTowerSimulator.mediator.ControlTower;
import AirportTowerSimulator.strategy.RunwayScheduler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class AirportSimulator {

    public static void main(String[] args) {
        ControlTower tower = new ControlTower();

        List<Aircraft> aircraftList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String id = "AC" + (i + 1);
            int fuel = 10 + random.nextInt(50);
            Aircraft plane;

            switch (random.nextInt(3)) {
                case 0 -> plane = new PassengerPlane(id, fuel, tower);
                case 1 -> plane = new CargoPlane(id, fuel, tower);
                default -> plane = new Helicopter(id, fuel, tower);
            }

            aircraftList.add(plane);
            tower.registerAircraft(plane);
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            Aircraft a = aircraftList.get(random.nextInt(aircraftList.size()));

            a.reduceFuel(random.nextInt(5));

            if (a.getFuelLevel() < 5 && random.nextBoolean()) {
                a.send("MAYDAY");
            } else if (random.nextBoolean()) {
                a.send("REQUEST_LANDING");
            } else {
                a.send("REQUEST_TAKEOFF");
            }
        }, 0, 2, TimeUnit.SECONDS);

        // Остановка через 30 секунд
        scheduler.schedule(() -> {
            System.out.println("Simulation finished.");
            scheduler.shutdown();
        }, 30, TimeUnit.SECONDS);
    }
}

