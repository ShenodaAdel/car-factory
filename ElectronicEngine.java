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
