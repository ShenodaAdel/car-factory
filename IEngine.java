interface IEngine {
    int getSpeed();
    void increase();
    void decrease();
    void start();
    void stop();
    void notifySpeed(int carSpeed);
}
