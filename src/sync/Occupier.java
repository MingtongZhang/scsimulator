package sync;

import java.awt.Color;

public class Occupier {
	volatile int speed = 1;
	volatile int position;
	volatile int extrasize=4;
	volatile Color thecolor;
}

class Eye{
	Agent owner;
	public Eye(Agent a) {
		this.owner=a;
	}
}

class Mine {
	Agent owner;
	double progress;
}