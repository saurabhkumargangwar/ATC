import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;

public class ATC1{
    
   
     public static Timer timer;
     public static Double speedrad=0.61283;//( rad/sec)
     public static Double speed=124.12;// (unit/sec)
    public static Double perpibyeight=2*0.27556;//sec
    public static double a;
    public static double b;
    public static double place;
    public static JTextArea conversation;
   public static JTextField xcoordinate;
    public static JTextField name;
    public static JTextField ycoordinate;
    public static anime drawPanel;
    public static int abc;
    public static String flightname="name";
    
    ArrayList<Thread> threadList = null;
    ArrayList<String> position = null;
    ArrayList<Double> joinpos = null;
    ArrayList<Double> places = null;
    ArrayList<Double> placed = null;
    ArrayList<Double> dist = null;
    ArrayList<Integer> M = null;
    ArrayList<String> names = null;   
    
    
    
    public int range[]=new int[16];
       public static void main (String[] args) {
    	   abc=-1;
    	  place=0;
	      ATC1 gui = new ATC1();
	      gui.threadList = new  ArrayList<Thread>();
	      gui.position = new  ArrayList<String>();
	      gui.names = new  ArrayList<String>();
	      gui.dist = new  ArrayList<Double>();
	      gui.places = new  ArrayList<Double>();
	      gui.M = new  ArrayList<Integer>();
	      gui.placed = new  ArrayList<Double>();
	      gui.joinpos = new  ArrayList<Double>();
          gui.d_frame();
       }
    
       
  public void d_frame() {
	  JFrame frame;
	  
	
	     
	JPanel textpanel=new JPanel();
	
    
     frame = new JFrame();
     JPanel panel = new JPanel();
   
	 
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
     drawPanel = new anime(0.0);
     drawPanel.addMouseListener(new mouselistener());
   
	 frame.getContentPane().add(drawPanel);
         frame.setTitle("Air Traffic Controle.");
	 frame.setSize(650,650);
	 frame.setVisible(true);
	
  }
  void makePlane(double theta,double distance)
  {

		 joinpos.add(theta);
	  places.add(theta);
	  double time=(distance-200)/speed;
	  dist.add(distance);
	 
	  Runnable r1=new anime(time);
		 Thread t1=new Thread(r1);
		 placed.add(0.0);
		 M.add(0);
		threadList.add(t1);
		 t1.start();
		
  }
  
  
  class anime extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	final int f=abc;
	
	 
	  int Black=0;
	  Double time=0.0;
	  anime(Double t){
		   time =t;
	  }
	  void sleep(long time){
		  try{
			   Thread.sleep(time);
		   }catch(Exception ex){}
	  }

	 Color startColor= new Color(Black);
	  public void run(){
	      String j="0";
			 position.add(j); 
			 
			 Double dfs=(places.get(f)*8/Math.PI);
			 int yss=dfs.intValue();
			 if(places.get(f)==2*Math.PI){
				 yss=15;
			 }
			 double angle=speedrad*time;
			 Double dfsr=(angle*8/Math.PI);
			 int yssr=dfsr.intValue();
			 yss=yss-yssr;
			 while(range[yss]>0){
				 M.set(f, 1);
				 for(double i=0.0;i<2*Math.PI;i=i+0.02){
					 placed.set(f, i);
					 drawPanel.repaint();
					 try{
						   Thread.sleep(1);
						   
					   }catch(Exception ex){}
				 }
				 M.set(f, 0);
			 }
          for(double t=dist.get(f);t>=199;t=t-1){
			  dist.set(f, t);
			  drawPanel.repaint();
			  
			  try{
		        	  Thread.sleep(7);
		          
			   }catch(Exception ex){}
			  
		  }  
			 if(places.size()!=0){
				  for(double i=places.get(f);i<2*Math.PI;i=i+0.01){
						 if(i==places.get(f)){
							 double trg=(((2*Math.PI)-places.get(f))/speedrad)+2;
							
							  Double df=(i*8/Math.PI);
							 int y=df.intValue();
							 range[y]++;
						 }else{
							 Double df=(i*8/Math.PI);
							 int y=df.intValue();
							 Double dfd=((i-0.01)*8/Math.PI);
							 int ys=dfd.intValue();
							 if(ys!=y){
								 range[ys]--;
								 range[y]++;
							 }
						 }
						 
						 String gh=String.valueOf(i);
						 position.set(f,gh);
						 a=i;
						 drawPanel.repaint();
						   try{
							   Thread.sleep(14);
							   
						   }catch(Exception ex){}
					 }
			 }
		
		  range[15]--;
			 for(int i=500;i>=310;i=i-2){
				 if(i==310){
					 try{
						   Thread.sleep(80);
						   
					   }catch(Exception ex){}
					
				 }
				 a=i; 
				 String gh=String.valueOf(i);
				 position.set(f,gh);
				 drawPanel.repaint();
				 try{
					   Thread.sleep(16);
					   
				   }catch(Exception ex){}
			 }
	  }
	  
	  public void paintComponent(Graphics g) {
			 g.setColor(Color.gray);
			  g.fillRect(0, 0, this.getWidth(), this.getHeight());
		  Graphics2D g2d=(Graphics2D) g;
		  Graphics2D g3d=(Graphics2D) g;
			g2d.setColor(Color.green);
			g2d.fillOval(100,100, 400, 400);
                        g2d.setColor(Color.blue);
                        //g2d.fillOval(105,105,360,360);
                        //g2d.setColor(Color.white);
			g2d.fillRect(290, 295, 40, 20);
			//g2d.drawLine(310, 300, 500, 300);
			g3d.setColor(startColor);
			//System.out.println(startColor);
			for(int yj=0;yj<M.size();yj++){
				if(M.get(yj)==1){
					if(placed.get(yj)<2*Math.PI){
						double ha=(joinpos.get(yj));
						double df=dist.get(yj);
						int d=(int)(df*Math.cos(ha))-10;
						int f=(int)(df*Math.sin(ha));
						g3d.fillOval(300+d+(int)(10*Math.cos(placed.get(yj))), 300+f+(int)(10*Math.sin(placed.get(yj))),15,15);
					
					}
					}
			}
			for(int h=0;h<dist.size();h++){
				if(M.get(h)==0){
					double df=dist.get(h);
					if(df>200){
						double ha=(joinpos.get(h));
						int d=(int)(df*Math.cos(ha));
						int f=(int)(df*Math.sin(ha));
						g3d.fillOval(300+d, 300+f,15,15);
					}
				}
				
			}
			for(int y=0;y<position.size();y++){
				a=Double.parseDouble(position.get(y));
				if(ATC1.a<300 && ATC1.a!=0 ){
					int d=(int)(200*Math.cos(ATC1.a));
					int f=(int)(200*Math.sin(ATC1.a));
					g3d.fillOval(300+d, 300+f,15,15);
					}else if(ATC1.a>310 && ATC1.a!=0){
						int o=(int)ATC1.a;
						g3d.fillOval(o,300,15,15);
					}
			}
			
		}
  }
  class mouselistener implements MouseListener{
	  public void mouseClicked(MouseEvent e)
      {
        
		  Double xco=Double.valueOf(e.getX());
		  Double yco=Double.valueOf(e.getY());
		 xco=xco-300;
		 yco=300-yco;
		 double distance=Math.sqrt(((xco)*(xco))+((yco)*(yco)));
		  if(distance<200){
			
		  }else{
			  if(yco<=0 && yco>-5){
				  if(xco==200){
					  place=2*Math.PI;
				  }
				  else if(xco>0){
					  place=Math.PI/16;
				  }else{
					  place=(Math.PI);
				  }
			  }else if(xco==0){
				  if(yco>0){
					  place=3*(Math.PI/2);
				  }else{
					  place=(Math.PI/2);
				  }
			  }
			  if(xco==200 && yco==0){
				 place=2*Math.PI;
			  }
			  else if(xco>0 && yco<-5){
				  yco=yco-(2*yco);
			  place=Math.atan(yco/xco);
			  }else if(xco<0 && yco<-5){
				  yco=yco-(2*yco);
				  xco=xco-(2*xco);
			  place=(Math.PI)-Math.atan(yco/xco);
			  }else if(xco<0 && yco>0){
				  xco=xco-(2*xco);
			  place=(Math.PI)+Math.atan(yco/xco);
			  }else if(xco>0 && yco>0){
			  place=(2*Math.PI)-Math.atan(yco/xco);
			  }
			  abc++;

				 //names.add(flightname);
			  makePlane(place,distance);
		  }
      }

	public void mouseEntered( MouseEvent arg0)
	{
		// for entering mouse
		
	}
	public void mouseExited(MouseEvent arg0)
	{
		// to exit mouse
	}
	public void mousePressed(MouseEvent arg0) 
	{//to press
	}
	public void mouseReleased(MouseEvent arg0)
	{//for releasing
	}
  }
  
 
}
