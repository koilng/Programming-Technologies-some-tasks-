package TP9;

public class Deadlock {
    public static void main(String[] args) {
        final Car lambo = new Car("Lambo");
        final Car maserati = new Car("Maserati");

        new Thread(new Runnable() {
            @Override
            public void run() {
                lambo.Race(maserati);
                System.out.println(maserati.getCarName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                maserati.Race(lambo);
                System.out.println(lambo.getCarName());
            }
        }).start();
    }

    public static class Car {
        private final String carName;

        public Car(String carName) {
            this.carName = carName;
        }

        public synchronized void Race(Car car) {
            System.out.println(this.carName + " is racing with " + car.getCarName());
            car.EndRace(this);
        }

        public synchronized void EndRace(Car car) {
            System.out.println("\nrace ended " + car.getCarName());
        }

        public String getCarName() {
            return carName;
        }
    }
}
