package InterfaceAndAbstract;

public abstract class Benz implements ICar {

    private String name = "Benz";

    @Override
    public void printModel(){
        System.out.println(name);
    }

    @Override
    public abstract void run();
}
