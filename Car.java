class Car {
    private IEngine engine;
    private int speed = 0;
    private boolean isRunning = false;

    public Car(IEngine eng) {
        engine = eng;
    }

    public void replaceEngine(IEngine newEngine) {
        if (isRunning) {
            System.out.println("Stop the car first before replacing engine");
            return;
        }
        engine = newEngine;
        System.out.println("Engine replaced");
    }

    public void start() {
        if (isRunning) {
            System.out.println("Car is already running");
            return;
        }
        speed = 0;
        isRunning = true;
        engine.start();
        engine.notifySpeed(speed);
        System.out.println("Car started");
    }

    public void stop() {
        if (!isRunning) {
            System.out.println("Car is not running");
            return;
        }

        if (speed != 0) {
            System.out.println("Cant stop, speed is " + speed + " must be 0 first");
            return;
        }

        isRunning = false;
        engine.stop();
        System.out.println("Car stopped");
    }

    public void accelerate() {
        if (!isRunning) {
            System.out.println("Start the car first");
            return;
        }

        if (speed >= 200) {
            System.out.println("Already at max speed");
            return;
        }

        speed += 20;

        for (int i = 0; i < 20; i++)
            engine.increase();

        engine.notifySpeed(speed);
        System.out.println("Speed is now " + speed);
    }

    public void brake() {
        if (!isRunning) {
            System.out.println("Car is not running");
            return;
        }

        if (speed == 0) {
            System.out.println("Already stopped");
            return;
        }

        speed -= 20;

        if (speed < 0)
            speed = 0;

        for (int i = 0; i < 20; i++)
            engine.decrease();

        engine.notifySpeed(speed);
        System.out.println("Speed is now " + speed);
    }
}
