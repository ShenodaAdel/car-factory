// Engine interface
interface IEngine {
    int getSpeed();
    void increase();
    void decrease();
    void start();
    void stop();
    void notifySpeed(int carSpeed);
}

class GasolineEngine implements IEngine {
    private int speed = 0;

    public int getSpeed() { return speed; }

    public void increase() { speed++; }

    public void decrease() {
        if (speed > 0)
            speed--;
    }

    public void start() {
        System.out.println("Gas engine started");
    }

    public void stop() {
        System.out.println("Gas engine stopped");
    }

    public void notifySpeed(int carSpeed) {
        System.out.println("Gas engine speed: " + carSpeed);
    }
}

class ElectronicEngine implements IEngine {
    private int speed = 0;

    public int getSpeed() { return speed; }

    public void increase() { speed++; }

    public void decrease() {
        if (speed > 0)
            speed--;
    }

    public void start() {
        System.out.println("Electric engine started");
    }

    public void stop() {
        System.out.println("Electric engine stopped");
    }

    public void notifySpeed(int carSpeed) {
        System.out.println("Electric engine speed: " + carSpeed);
    }
}

class MixedHybridEngine implements IEngine {
    private ElectronicEngine electric = new ElectronicEngine();
    private GasolineEngine gas = new GasolineEngine();
    private int currentSpeed = 0;

    public int getSpeed() {
        if (currentSpeed < 50)
            return electric.getSpeed();
        else
            return gas.getSpeed();
    }

    public void increase() {
        if (currentSpeed < 50)
            electric.increase();
        else
            gas.increase();
    }

    public void decrease() {
        if (currentSpeed < 50)
            electric.decrease();
        else
            gas.decrease();
    }

    public void start() {
        System.out.println("Hybrid engine started: electric mode");
        electric.start();
    }

    public void stop() {
        System.out.println("Hybrid engine stopped");
        electric.stop();
        gas.stop();
    }

    public void notifySpeed(int carSpeed) {
        boolean IsElectric = currentSpeed < 50;
        currentSpeed = carSpeed;
        boolean nowElectric = currentSpeed < 50;

        // check if we need to switch engines
        if (IsElectric && !nowElectric) {
            System.out.println("Switching to gas engine");
            electric.stop();
            gas.start();
        } else if (!IsElectric && nowElectric) {
            System.out.println("Switching to electric engine");
            gas.stop();
            electric.start();
        }

        if (nowElectric)
            electric.notifySpeed(carSpeed);
        else
            gas.notifySpeed(carSpeed);
    }
}

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

enum EngineType {
    Gasoline,
    Electronic,
    Hybrid
}

class CarFactory {
    public Car createCar(EngineType type) {
        IEngine eng = makeEngine(type);
        System.out.println("Car created with " + type + " engine");
        return new Car(eng);
    }

    public void replaceEngine(Car car, EngineType type) {
        IEngine eng = makeEngine(type);
        car.replaceEngine(eng);
    }

    private IEngine makeEngine(EngineType type) {
        if (type == EngineType.Gasoline)
            return new GasolineEngine();
        else if (type == EngineType.Electronic)
            return new ElectronicEngine();
        else
            return new MixedHybridEngine();
    }
}

public class Main {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();

        System.out.println("Gas Car");
        Car gasCar = factory.createCar(EngineType.Gasoline);
        gasCar.start();
        gasCar.accelerate();
        gasCar.accelerate();
        gasCar.brake();
        gasCar.brake();
        gasCar.stop();

  
        System.out.println("\n Electric Car");
        Car electricCar = factory.createCar(EngineType.Electronic);
        electricCar.start();
        electricCar.accelerate();
        electricCar.accelerate();
        electricCar.brake();
        electricCar.brake();
        electricCar.stop();


        System.out.println("\n Hybrid Car ");
        Car hybridCar = factory.createCar(EngineType.Hybrid);
        hybridCar.start();
        hybridCar.accelerate(); 
        hybridCar.accelerate(); 
        hybridCar.accelerate(); 
        hybridCar.accelerate(); 
        hybridCar.brake();      
        hybridCar.brake();      
        hybridCar.brake();
        hybridCar.brake();
        hybridCar.stop();

        // test engine replace
        System.out.println("\n Replace Engine");
        Car myCar = factory.createCar(EngineType.Gasoline);
        myCar.start();
        myCar.accelerate();
        factory.replaceEngine(myCar, EngineType.Hybrid); 
        myCar.brake();
        myCar.stop();
        factory.replaceEngine(myCar, EngineType.Hybrid); 
        myCar.accelerate();
        myCar.accelerate();
        myCar.accelerate();
        myCar.brake();
        myCar.brake();
        myCar.brake();
        myCar.stop();
    }
}
