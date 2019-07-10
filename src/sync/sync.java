package sync;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class sync {
	public static void main(String args[]) {
		//Requestpool rp = new Requestpool();
		BlockingQueue<Request> rp = new LinkedBlockingQueue<Request>(100);
		Agent maru = new Agent();
		maru.name="maru";
		maru.position = 60;
		maru.movement = "left";
		maru.rp=rp;
		maru.thecolor = Color.RED;
		Agent time = new Agent();
		time.name="time";
		time.position = 460;
		time.movement = "right";
		time.rp=rp;
		time.thecolor = Color.BLUE;
		Environment environment = new Environment();
		environment.rp=rp;
		environment.agents.add(maru);
		environment.agents.add(time);
		maru.opponent=time;
		time.opponent=maru;
		Reporter rpt = new Reporter(environment);
		new Soul1(maru).start();
		new Soul2(time).start();
		environment.start();
		rpt.start();
//		while(true) {
//			System.out.println("main line" + maru.movement);	
//		}
	}
}

class Request{
	Agent from;
	String content;
	Agent to;
	public Request(String toinput1,Agent toinput2) {
		this.content=toinput1;
		this.from=toinput2;
	}
	public Request(String toinput1,Agent toinput2, Agent toinput3) {
		this.content=toinput1;
		this.from=toinput2;
		this.to=toinput3;
	}
}