package demo;

/**
 * Date: 2021/7/14 0:06
 */
public class Test {

    public static void main(String[] args) {
      Test.turnLight("红");
      Test.directLight("左");
      Test.turnAndDirectLight("普通","绿");
      Test.turnAndDirectLight("导向","右");
    }

    public static void turnAndDirectLight(String type, String signal ) {
        if ("普通".equals(type)) {
            turnLight(signal);
        } else {
            directLight(signal);
        }
    }

    public static void turnLight(String light) {
        switch (light) {
            case "红" :
                System.out.println("红灯亮时，禁止车辆通行，准许右转弯。");
                break;
            case "黄" :
                System.out.println("黄灯亮时，禁止车辆通行，如已过停车线需继续前进。");
                break;
            case "绿" :
                System.out.println("绿灯亮时，允许车辆通行。");
                break;
        }
    }

    public static void directLight(String direct) {
        switch (direct) {
            case "上" :
                System.out.println("往前车辆可以通行，其他方向禁止通行。");
                break;
            case "左" :
                System.out.println("往左车辆可以通行，其他方向禁止通行。");
                break;
            case "右" :
                System.out.println("往右车辆可以通行，其他方向禁止通行。");
                break;
        }
    }

}
