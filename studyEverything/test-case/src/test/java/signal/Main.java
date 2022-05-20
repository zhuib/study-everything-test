package signal;

import org.junit.Test;

/**
 * Date: 2021/7/28 22:58
 */
public class Main {

    public static void main(String[] args) {
        Lamp red = new Lamp("RED");
        Lamp green = new Lamp("GREEN");
        Lamp yellow = new Lamp("YELLOW");
        Vehicle vehicle = new Vehicle("LEFT",true);
        Vehicle vehicle1 = new Vehicle("LEFT");

        vehicle.turn(red,vehicle);
        vehicle.turn(green,vehicle);
        vehicle.turn(yellow,vehicle);

        vehicle.turn(red,vehicle1);
        vehicle.turn(green,vehicle1);
        vehicle.turn(yellow,vehicle1);
    }

    @Test
    public void pass() {
        Lamp red = new Lamp("RED");
        Lamp green = new Lamp("GREEN");
        Lamp yellow = new Lamp("YELLOW");
        Vehicle vehicle = new Vehicle("LEFT",true);
        Vehicle vehicle1 = new Vehicle("LEFT");

//        vehicle.turn(red,vehicle);
//        vehicle.turn(green,vehicle);
        vehicle.turn(yellow,vehicle); //

//        vehicle.turn(red,vehicle1);
//        vehicle.turn(green,vehicle1);
//        vehicle.turn(yellow,vehicle1);
    }

    @Test
    public void eachEvery() {
        String[] color = new String[]{"RED","GREEN","YELLOW"};
        String[] direct = new String[]{"FORWARD","LEFT","RIGHT"};
        Boolean[] line = new Boolean[]{true,false};
        int count = 0;
        for(int i = 0; i < color.length; i++) {
            for (int j = 0; j <direct.length; j++) {
                for (int t = 0; t < line.length; t++) {
                    Lamp lamp = new Lamp(color[i]);
                    Vehicle vehicle = new Vehicle(direct[j],line[t]);
                    vehicle.turn(lamp,vehicle);
                    System.out.println(count++);
                }
            }
        }
    }

    @Test
    public void pass1() {
        Lamp red = new Lamp("RED");
        Lamp green = new Lamp("GREEN");
        Lamp yellow = new Lamp("YELLOW");
        Arrow left = new Arrow("LEFT");
        Arrow right = new Arrow("RIGHT");
        Arrow forward = new Arrow("FORWARD");
        Vehicle vehicle = new Vehicle("LEFT",true);
        Vehicle vehicle1 = new Vehicle("FORWARD",false);
        Vehicle vehicle2 = new Vehicle("LEFT");


        vehicle.arrowTurn(red,forward,vehicle1); //
        vehicle.arrowTurn(green,forward,vehicle1); //

    }

    @Test
    public void eachEvery1() {
        String[] color = new String[]{"RED","GREEN","YELLOW"};
        String[] direct = new String[]{"FORWARD","LEFT","RIGHT"};
        String[] arrows = new String[]{"FORWARD","LEFT","RIGHT"};
        Boolean[] line = new Boolean[]{true,false};
        int count = 0;
        for(int i = 0; i < color.length; i++) {
            for (int h =0; h < arrows.length; h++) {
                for (int j = 0; j <direct.length; j++) {
                    for (int t = 0; t < line.length; t++) {
                        Lamp lamp = new Lamp(color[i]);
                        Arrow arrow = new Arrow(arrows[h]);
                        Vehicle vehicle = new Vehicle(direct[j],line[t]);
                        vehicle.arrowTurn(lamp,arrow,vehicle);
                        System.out.println(count++);
                    }
                }
            }
        }
    }

    @Test
    public void test() {
        Lamp red = new Lamp("RED");
        Lamp green = new Lamp("GREEN");
        Lamp yellow = new Lamp("YELLOW");
        Arrow left = new Arrow("LEFT");
        Arrow right = new Arrow("RIGHT");
        Arrow forward = new Arrow("FORWARD");
        Vehicle vehicle = new Vehicle("LEFT",true);
        Vehicle vehicle1 = new Vehicle("FORWARD",false);
        Vehicle vehicle2 = new Vehicle("LEFT");

        vehicle.simpleAndDirect(true,green,forward,vehicle1);
    }
}
