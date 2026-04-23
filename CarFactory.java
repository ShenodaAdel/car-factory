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
