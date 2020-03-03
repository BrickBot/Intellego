import interfaces.Controller;
import interfaces.AbstractRobot;

/**
* this class implements a neural net with a 3-1 topology plus bias.
* the three inputs are light sensors 0->2.
* output of <0.4 corresponds to turn left, 0.4<x<0.6 go forwards, >0.6 turn right.
* This was the first neural net that was written, and it was generated using real world
* values (similar to GBN2b). The training regime was a bit lax though - the threshhold was set to 0.1,
* so it would not be surprising if this net performs a little unexpectedly in some conditions.
*
* @author Graeme Bell
* @see GBN2b
*/

public class GBNN2 extends Controller {

AbstractRobot r;
boolean initcalled;
boolean running=false;

private int[] sensors={Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT};

// this looks rubbish - but it will compile to run extremely fast 
// this is because the weights will be inlined and the inputs/outputs
// won't need dereferencing or array overhead.

float input0;
float input1;
float input2;

// bias input is assumed to be +1

float output0;

// weights
final float weight00 = -4.1f;
final float weight10 = 1.16f;
final float weight20 = 3.69f;
final float weightb0 = -0.94f;

	public void run(){

		running=true;
			
		while (running){
		
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
}	
