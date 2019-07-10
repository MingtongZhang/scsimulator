package sync;

import java.awt.*;

import javax.swing.*;

import sync.Reporter.lineblock;
//aaaaa

public class Reporter extends Thread{
	omoteworld wd;
	public Reporter(Environment ev) {
		this.ev=ev;
		wd = new omoteworld();
		for(int n=0;n<Constant.WL+100;n++) {
			wd.add(new bg(n,0));
		}
		lineblock[] blocks = new lineblock[Constant.WL];
		for(int n=0;n<Constant.WL;n++) {
			lineblock lb = new lineblock(n*1);
			blocks[n]=lb;
			wd.add(lb);		
		}	
		wd.blocks=blocks;

		
	}

	Environment ev;
	@Override
	public void run() {
		while(true) {//-----
			for(lineblock lb : wd.blocks) {
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
			setBounds(where,0,20,20);
			setVisible(true);
		}
		@Override
		public void paint(Graphics g) {
			//System.out.println("block painting");
			g.setColor(c);
			g.fillRect(0, 0, 20, 20);
		}
	}
	
//	class bar extends JPanel{
//		int x;int y;
//		lineblock[] blocks = new lineblock[Constant.WL];
//		public bar(int x, int y,lineblock[] blcs) {
//			this.x=x;
//			this.y=y;
//			setBounds(x,y,Constant.WL*1,1);
//			setVisible(true);
//			for(int n=0;n<Constant.WL;n++) {
//				lineblock alb = new lineblock(n*1);
//				this.blocks[n]= alb;
//				this.add(alb);
//			}		
//		}
//		@Override
//		public void paint(Graphics g) {			
//			//System.out.println("bar painting");
//			for(lineblock lb : blocks) {
//				lb.repaint();
//			}
//		}
//	}

	class omoteworld extends JFrame{
		lineblock[] blocks = new lineblock[Constant.WL];
		//bar thebar;
		public omoteworld() {
			setVisible(true);
			this.setResizable(false);
			this.setSize(1200, 1200);
			//this.setLocation(200, 200);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//thebar = new bar(200,2000);
		}

	}
	class bg extends JPanel{
		int x,y;
		public bg(int x,int y) {
			this.x=x;
			this.y=y;
			this.setBounds(x,y,1,20);
			//this.setVisible(false);
		}
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1, 20);
		}
	}
}


