package demo.light;

/**
 * Date: 2021/7/28 21:40
 */
public enum Lamp {
    S2N("N2S","S2W",false),S2W("N2E","E2W",false),E2W("W2E","E2S",false),E2S("W2N","S2N",false),
    N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
    S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);

    //private String name;
    private boolean lighted;
    private String oppsite;
    private String next;

    //��ʼ���źŵ�
    private Lamp(String oppsite,String next,boolean lighted){
        this.oppsite = oppsite;
        this.next = next;
        this.lighted = lighted;
    }
    //���صƵ�ǰ״̬
    public boolean isLighted() {
        return lighted;
    }

    //���źŵ�(���̵�)
    public void turnOn(){
        this.lighted = true;
        if(this.oppsite != null){
            Lamp.valueOf(oppsite).turnOn();
        }
        System.out.println(name() +" lamp is green:Ӧ���ܿ���6������ĳ���ͨ����");
    }
    //�ر��źŵ�(����),��������һ�����̵ĵ�
    public Lamp turnOff(){
        //Lamp.valueOf(lamp).lighted = false;
        this.lighted = false;
        if(this.oppsite != null){
            Lamp.valueOf(oppsite).lighted = false;
        }

        Lamp nextLamp = null;

        if(next != null){
            nextLamp = Lamp.valueOf(next);
            System.out.println("�̵ƴ�"+name()+"---->�л�Ϊ"+next);
            nextLamp.turnOn();

        }

        return nextLamp;
    }
}
