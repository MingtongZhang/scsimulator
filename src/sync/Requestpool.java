package sync;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.*;

class Requestpool{
	BlockingQueue<Request> requests = new LinkedBlockingQueue<Request>(100);
	public void push(Request rq) {
		requests.offer(rq);
		System.out.println(rq.from.name+ " has made a"+ rq.content +" request");
		}
	public Request pull() {
		Request toreturn = requests.poll();
		System.out.println(toreturn.content+" from"+toreturn.from.name+" has been approved"+requests.size());
		return toreturn;
	}		
}
