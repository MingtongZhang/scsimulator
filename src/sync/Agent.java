package sync;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.awt.Color;
import java.util.concurrent.*;

public class Agent extends Occupier {
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	volatile boolean collision = false;
	volatile boolean gou = false;
	volatile boolean tou = false;
	volatile double force = 20;
	volatile double increase = 0.06;
	Agent opponent;
	String name;
	BlockingQueue<Request> rp;
	volatile String movement;
	Opponentinfo opinfo = new Opponentinfo();

	// Regularscout rgsc = new Regularscout(this);
	class Opponentinfo {
		volatile String name;
		volatile String movement;
		volatile int position;
	}
	// class Regularscout extends Thread {
	// Agent owner;
	// public Regularscout(Agent ag) {
	// this.owner=ag;
	// }
	// @Override
	// public void run() {
	// while(true) {
	// try {
	// sleep(3000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println(owner.name+" scouted======");
	// try {
	// rp.put(new Request("scout",this.owner,opponent));
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// }
	//
	// }

}
