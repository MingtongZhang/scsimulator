package sync;
//from desktop to mac try
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Environment extends Thread {
	Unit[] line = new Unit[Constant.WL];
	List<Agent> agents = new ArrayList<Agent>();
	List<Occupier> occupiers = new ArrayList<Occupier>();
	BlockingQueue<Request> rp;
	Updater up = new Updater();
	RequestProcessor processor = new RequestProcessor();

	public Environment() {
		for (Agent a : agents) {
			occupiers.add(a);
		}
		for (int n = 0; n < Constant.WL; n++) {
			line[n] = new Unit();
		}
	}
	
	@Override
	public void run() {
		up.start();
		processor.start();
	}

	public class Updater extends Thread {
		public void leftmove(Occupier o) {
			boolean cango = true;
			if ((o.position - o.speed) - o.extrasize < 0) {
				cango = false;
			}
			for (Agent a : agents) {
				if (a != o) {
					for (int n = (o.position - o.speed) - o.extrasize; n <= (o.position - o.speed) + o.extrasize; n++) {
						for (int m = a.position - a.extrasize; m <= a.position + a.extrasize; m++) {
							if (n == m) {
								cango = false;
							}
						}
					}
				}
			}
			if (cango == true) {
				o.position -= o.speed;
			}
		}

		public void rightmove(Occupier o) {
			boolean cango = true;
			if ((o.position + o.speed) + o.extrasize > Constant.WL - 1) {
				cango = false;
			}
			for (Agent a : agents) {
				if (a != o) {
					for (int n = (o.position + o.speed) - o.extrasize; n <= (o.position + o.speed) + o.extrasize; n++) {
						for (int m = a.position - a.extrasize; m <= a.position + a.extrasize; m++) {
							if (n == m) {
								cango = false;
							}
						}
					}
				}
			}
			if (cango == true) {
				o.position += o.speed;
			}
		}

		@Override
		public void run() {
			{
				while (true) {
					for (int n = 0; n < Constant.WL; n++) {
						line[n] = new Unit();
					}
					for (Agent a : agents) {
						a.collision = false;
						a.force += a.increase;
						if (a.movement.equals("left")) {
							leftmove(a);
						}
						if (a.movement.equals("right")) {
							rightmove(a);
						}
					}
					// write 1d world
					for (Agent a : agents) {
						for (int n = 0; n <= a.extrasize; n++) {
							line[a.position + n].occuppied = true;
							line[a.position + n].occuppier = a;
							line[a.position - n].occuppied = true;
							line[a.position - n].occuppier = a;
						}
					}
					for (Agent a : agents) {
						line[a.position].eyes.add(new Eye(a));
					}
					//agent action complete, status checking and modification start
					for (Agent a : agents) {
						for (int n = 1; n < 10; n++) {
							if ((a.position + a.extrasize) + 1 >= 0 && (a.position - a.extrasize) - 1 >= 0
									&& (a.position - a.extrasize) - 1 <= Constant.WL - 1
									&& (a.position + a.extrasize) + 1 <= Constant.WL - 1) {
								if (line[(a.position + a.extrasize) + 1].occuppied
										|| line[(a.position - a.extrasize) - 1].occuppied) {
									a.collision = true;
									break;
								}
							}
						}
					}
					for(Unit u : line) {
						if(u.mine!=null&&u.occuppied) {
							//kaikuang
						}
					}
					for (Agent a : agents) {
						if (a.collision == true) {
							a.force -= 0.1;
						}
					}

					try {
						sleep(10);
					} catch (Exception e) {
					}
					;
				}
			}
		}
	}

	public class RequestProcessor extends Thread {
		class gouprocessor extends Thread {
			Agent who;

			public gouprocessor(Agent ag) {
				this.who = ag;
			}

			@Override
			public void run() {
				System.out.println(who.name + "starts gou");
				who.increase += 0.03;
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				who.increase -= 0.09;
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				who.increase += 0.06;
				who.gou = false;
				System.out.println(who.name + "completes gou");
			}
		}

		@Override
		public void run() {
			while (true) {
				Request therequest;
				try {
					therequest = rp.take();
					if (therequest.content.equals("left")) {
						therequest.from.movement = "left";
					}
					if (therequest.content.equals("right")) {
						therequest.from.movement = "right";
					}
					if (therequest.content.equals("scout")) {
						therequest.from.rwl.writeLock().lock();
						therequest.to.rwl.readLock().lock();
						therequest.from.opinfo.movement = therequest.to.movement;
						therequest.from.opinfo.position = therequest.to.position;
						therequest.from.rwl.writeLock().unlock();
						therequest.to.rwl.readLock().unlock();
					}
					if (therequest.content.equals("gou")) {
						if (therequest.from.gou == false) {
							new gouprocessor(therequest.from).start();
							therequest.from.gou = true;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

}

class Unit {
	volatile int coordinate;
	volatile boolean occuppied = false;
	volatile Occupier occuppier;
	volatile ArrayList<Eye> eyes = new ArrayList<Eye>();
	volatile Mine mine = null;
}
