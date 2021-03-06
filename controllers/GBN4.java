import interfaces.Controller;
import interfaces.AbstractRobot;


/**
* This class is a 3-1 net (plus bias) whos input comes from
* preprocessing 3 sensor inputs.
* The net was trained on 3 training patterns and generalises
* across 8 possible sets of binary inputs.
* see GBN3.training file for details.
* This robot tends to go wrong if it starts pointing the wrong
* way. This is because there is no training pattern to indicate
* how to deal with this circumstance.
* See GBN3 for an implementation that turns itself around, using 
* 4 training patterns and generalising across 16 possible sets 
* of binary inputs.
*
* @author Graeme Bell
* @see GBN3
*/

public class GBN4 extends Controller {

AbstractRobot r;
boolean initcalled;
boolean running;
private boolean paused=false;

private int[] sensors={Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT};

// this looks rubbish - but it will compile to run extremely fast 
// this is because the weights will be inlined and the inputs/outputs
// won't need dereferencing or array overhead.

float sens0;
float sens1;
float sens2;

float input0;
float input1;
float input2;

// bias input is assumed to be +1

float output0;

final float weight00 = -0.896f;
final float weight10 = 0f;
final float weight20 = 0.896f;
final float weightb0 = 0f;

	public void run(){

		running=true;
			
		while (running) {

                // get input
                                                        
                sens0=r.getSensor1()/100.0f;
                sens1=r.getSensor2()/100.0f;
                sens2=r.getSensor3()/100.0f;

				// preprocessing
				
				if (sens0>sens1 && sens1>sens2) { input0=1.0f; } else {input0=0.0f;}
				if (sens0<sens1 && sens1>sens2) { input1=1.0f; } else {input2=0.0f;}
				if (sens0<sens1 && sens1<sens2) { input2=1.0f; } else {input2=0.0f;}

				// this could be optimised by just adding weights according to value of inputs.
				// want to keep it looking like an FF neural net though to avoid worried looks.

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
	
	public GBN4() {	}
}	
