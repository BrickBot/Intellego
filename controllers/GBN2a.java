import interfaces.Controller;
import interfaces.AbstractRobot;


/**
* This controller is based on the simulator's measured input values
* It is a 3-1 net plus bias.
*
* @author Graeme Bell
*/

public class GBN2a extends Controller {

AbstractRobot r;
boolean initcalled;
boolean running;
boolean paused;

private int[] sensors={Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT};

// this looks rubbish - but it will compile to run extremely fast 
// this is because the weights will be inlined and the inputs/outputs
// won't need dereferencing or array overhead.

float input0;
float input1;
float input2;

// bias input is assumed to be +1

float output0;


final float weight00=8.191f;
final float weight10=-4.495f;
final float weight20=34.566f;
final float weightb0=-25.26f;

	public void run(){

		running=true;
			
		while (running) {
			
                // get input
                                                        
                input0=r.getSensor1()/100.0f;
                input1=r.getSensor2()/100.0f;
                input2=r.getSensor3()/100.0f;

				//Sigmoidal activation function 1.0/1.0+exp(excitation)

				output0 = 1.0f/(1.0f+(float)(Math.exp(	weight00*input0+
					 		weight10*input1+
							weight20*input2+
							weightb0)));

				//decide what to do.

				if (output0<0.4) {r.right();}
				if (output0<0.6 && output0>=0.4) {r.forward();}
				if (output0>=0.6) {r.left();}
				
                try{Thread.sleep(500);}catch(Exception e){}
		}
	}

	
	public void initController(AbstractRobot r) {

		this.r=r;
		initcalled=true;
	}
	
	public int[] getSensors(){
	
		return sensors;
	}
	
	public void halt(){
	
		running=false;
		r.stopMoving();
	}
	
	public AbstractRobot getRobot(){
	
		return r;
	}
	
	public GBN2a() {	}
}	
