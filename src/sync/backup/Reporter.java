package sync;

import java.awt.*;

import javax.swing.*;

import sync.Reporter.lineblock;


public class Reporter extends Thread{
	omoteworld wd;
	public Reporter(Environment ev) {
		this.ev=ev;
		wd = new omoteworld();
		lineblock[] blocks = new lineblock[Constant.WL];
		for(int n=0;n<Constant.WL;n++) {
			blocks[n]=new lineblock(n*1);
		}	
		bar thebar = new bar(0,500,blocks);
		wd.thebar=thebar;
		wd.add(thebar);
		
	}

	Environment ev;
	@Override
	public void run() {
		while(true) {//-----
			for(lineblock lb : wd.thebar.blocks) {
				lb.c=Color.WHITE;
				int n = lb.where/1;
				if(ev.line[n].occuppied) {
					lb.c=ev.line[n].occuppier.thecolor;
				}
			}
			//System.out.println("painting");
			wd.repaint();
			
			for(Agent a : ev.agents)
			{System.out.println(a.name+" "+a.movement + a.position+" F:"+a.force+" +"+a.increase);}
			try {
				sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" a world state report complete");
		//----
			}
	}
	class lineblock extends JPanel{
		Color c;
		int where;
		public lineblock(int where) {
			this.where=where;
			setBounds(where,0,1,1);
			setVisible(true);
		}
		@Override
		public void paint(Graphics g) {
			//System.out.println("block painting");
			g.setColor(c);
			g.fillRect(0, 0, 1, 1);
		}
	}
	
	class bar extends JPanel{
		int x;int y;
		lineblock[] blocks = new lineblock[Constant.WL];
		public bar(int x, int y,lineblock[] blcs) {
			this.x=x;
			this.y=y;
			setBounds(x,y,Constant.WL*1,1);
			setVisible(true);
			for(int n=0;n<Constant.WL;n++) {
				lineblock alb = new lineblock(n*1);
				this.blocks[n]= alb;
				this.add(alb);
			}		
		}
		@Override
		public void paint(Graphics g) {			
			//System.out.println("bar painting");
			for(lineblock lb : blocks) {
				lb.repaint();
			}
		}
	}

	class omoteworld extends JFrame{

		bar thebar;
		public omoteworld() {
			setVisible(true);
			this.setResizable(false);
			this.setSize(1200, 1200);
			//this.setLocation(200, 200);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//thebar = new bar(200,2000);
		}
		@Override
		public void paint(Graphics g) {
			
			
			this.thebar.repaint();
			System.out.println("world painting");
		}
	}
}


