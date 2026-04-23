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
