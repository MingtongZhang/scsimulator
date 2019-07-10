package sync;

public abstract class Soul extends Thread {
	public Soul(Agent ag) {
		thebody = ag;
	}
	Agent thebody;
	public abstract void think();
	@Override
	public void run() {
		think();
	}
}
class Soul1 extends Soul {
public Soul1(Agent ag) {
		super(ag);		
	}
@Override
public void think() {
	try {
		thebody.rp.put(new Request("gou",thebody));
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	while(true) {
		if(thebody.force<0) {
			try {
				thebody.rp.put(new Request("left",thebody));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(this.name+" thinks he is at "+this.movement + this.position);
		if(thebody.position<50 && thebody.movement.equals("left")) {
			//System.out.println(thebody.name+" signaled right");
			try {
				thebody.rp.put(new Request("right",thebody));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(thebody.position>450 && thebody.movement.equals("right")) {
			try {
				thebody.rp.put(new Request("left",thebody));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(thebody.name+" signaled left");
		}
//		try {
//			sleep(0);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		//
	}
}
}

class Soul2 extends Soul {
public Soul2(Agent ag) {
		super(ag);		
	}
@Override
public void think() {
	try {
		thebody.rp.put(new Request("gou",thebody));
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	while(true) {
		if(thebody.force<0) {
			try {
				thebody.rp.put(new Request("right",thebody));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(this.name+" thinks he is at "+this.movement + this.position);
		if(thebody.position<50 && thebody.movement.equals("left")) {
			//System.out.println(thebody.name+" signaled right");
			try {
				thebody.rp.put(new Request("right",thebody));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(thebody.position>450 && thebody.movement.equals("right")) {
			try {
				thebody.rp.put(new Request("left",thebody));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(thebody.name+" signaled left");
		}
//		try {
//			sleep(0);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		//
	}
}
}