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
