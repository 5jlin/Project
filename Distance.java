

public class Distance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle C1 = new Circle();
		Circle C2 = new Circle();
		Circle C3 = new Circle();
		
		C1.heartX = 0;
		C1.heartY = 0;
		C1.radius = 2;
		C2.heartX = 3;
		C2.heartY = 0;
		C2.radius = 3;
		C3.heartX = 1;
		C3.heartY = 4;
		C3.radius = 4;
		
		double temx = 0;
		double temy = 0;
		double tem2x = 0;
		double tem2y = 0;
		double finalx = 0;
		double finaly = 0;

		double[][] back = new double [2][2];
		double[][] back2 = new double [2][2];
		double[][] back3 = new double [2][2];
		
		back = findpoint(C1.heartX,C1.heartY,C1.radius,C2.heartX,C2.heartY,C2.radius);
		temx = temx + back[0][0] + back[1][0];
		temy = temy+ back[0][1] + back[1][1];
		back2 = findpoint(C1.heartX,C1.heartY,C1.radius,C3.heartX,C3.heartY,C3.radius);
		temx = temx + back2[0][0] + back2[1][0];
		temy = temy+ back2[0][1] + back2[1][1];
		back3 = findpoint(C2.heartX,C2.heartY,C2.radius,C3.heartX,C3.heartY,C3.radius);
		temx = temx + back3[0][0] + back3[1][0];
		temy = temy+ back3[0][1] + back3[1][1];
/*		System.out.println(back[0][0]);
		System.out.println(back[0][1]);
		System.out.println(back[1][0]);
		System.out.println(back[1][1]);*/
//		System.out.println(temx + " "+temy);
		
		temx = temx/6;
		temy = temy/6;
		
		
		if(p2pdistance(temx,temy,back[0][0],back[0][1]) > p2pdistance(temx,temy,back[1][0],back[1][1])){
			tem2x = tem2x + back[1][0];
			tem2y = tem2y + back[1][1];
		}
		else{
			tem2x = tem2x + back[0][0];
			tem2y = tem2y + back[0][1];
		}
		
		if(p2pdistance(temx,temy,back2[0][0],back2[0][1]) > p2pdistance(temx,temy,back2[1][0],back2[1][1])){
			tem2x = tem2x + back2[1][0];
			tem2y = tem2y + back2[1][1];
		}
		else{
			tem2x = tem2x + back2[0][0];
			tem2y = tem2y + back2[0][1];
		}
		
		if(p2pdistance(temx,temy,back3[0][0],back3[0][1]) > p2pdistance(temx,temy,back3[1][0],back3[1][1])){
			tem2x = tem2x + back3[1][0];
			tem2y = tem2y + back3[1][1];
		}
		else{
			tem2x = tem2x + back3[0][0];
			tem2y = tem2y + back3[0][1];
		}
			
		finalx = tem2x/3;
		finaly = tem2y/3;
		
		temx = 0;
		temy = 0;
		tem2x = 0;
		tem2y = 0;
		
		System.out.println(distance(-59,-73));
//		System.out.println(p2pdistance(0,0,3,4));
		System.out.println(finalx+","+finaly);

	}
	
	
	public static double[][] findpoint(double C1_X,double C1_Y,double r1,double C2_X,double C2_Y,double r2){
		double x1,x2,y1,y2;
		double back[][] = new double [2][2];
		
		if( C1_Y != C2_Y){
			double m = (C1_X - C2_X) / (C2_Y - C1_Y);
			double k = ((r1*r1) - (r2*r2) + Math.pow(C2_X,2) - Math.pow(C1_X,2) + Math.pow(C2_Y,2) - Math.pow(C1_Y,2))/(2*(C2_Y-C1_Y));
	        double a = 1 + (m*m);
	        double b = 2 * (k*m - C2_X-m * C2_Y);
	        double c = Math.pow(C2_X,2) + Math.pow(C2_Y,2) + (k*k) - 2*k*C2_Y - (r2*r2);
	        
	        if(b*b-4*a*c>=0)
	        {
	            x1 = ((-b)+Math.sqrt(b*b-4*a*c))/(2*a); 
	            y1 = m*x1+k;
	            x2 = ((-b)-Math.sqrt(b*b-4*a*c))/(2*a);
	            y2 = m*x2+k;
	            if(b*b-4*a*c>0){
	            	System.out.println("The cross points are (" + x1 +","+ y1+") and (" + x2 + "," + y2 +")" );  
	            	back[0][0] = x1;
	            	back[0][1] = y1;
	            	back[1][0] = x2;
	            	back[1][1] = y2;
	            }
	            else{
	            	System.out.println("The cross points are "+x1 + "," + y1);
	            	back[0][0] = x1;
	            	back[0][1] = y1;
	            	back[1][0] = x1;
	            	back[1][1] = y1;
	            }
	        }
	        else{
	        	System.out.println("No cross points.");
	        }	        	
		}
		
		else if((C1_Y == C2_Y))
	    {
			x1 = -(Math.pow(C1_X,2) - Math.pow(C2_X,2) - (r1*r1)+(r2*r2)) / (2 * C2_X-2*C1_X);
	        double a = 1;
	        double b = -2 * C1_Y;
	        double c = Math.pow(x1,2)+Math.pow(C1_X,2)-2*C1_X*x1+Math.pow(C1_Y,2)- (r1*r1);
	        if(b*b-4*a*c>=0)
	        {
	            y1 = ((-b)+Math.sqrt(b*b-4*a*c))/(2*a);
	            y2 = ((-b)-Math.sqrt(b*b-4*a*c))/(2*a);
	            if(b*b-4*a*c>0){
	            	System.out.println("The cross points are (" + x1 +","+ y1+") and (" + x1 + "," + y2 +")" );
	            	back[0][0] = x1;
	            	back[0][1] = y1;
	            	back[1][0] = x1;
	            	back[1][1] = y2;
	            }
	            	
	            else{
	            	System.out.println("The cross points are "+x1 + "," + y1);
	            	back[0][0] = x1;
	            	back[0][1] = y1;
	            	back[1][0] = x1;
	            	back[1][1] = y1;
	            }
	        }
	        else
	        	System.out.println("No cross points.");
	    }
		return back;
	}

	public static double distance(int txPower, double rssi){
		if (rssi == 0) {
		    return -1.0; // if we cannot determine accuracy, return -1.
		  }

		  double ratio = rssi*1.0/txPower;
		  if (ratio < 1.0) {
		    return Math.pow(ratio,10);
		  }
		  else {
		    double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;    
		    return accuracy;
		  }
		
	}

	public static double p2pdistance(double X1,double Y1,double X2,double Y2){
		double dis = 0;
		dis = Math.sqrt((X1-X2)*(X1-X2) + (Y1-Y2)*(Y1-Y2));
		return dis;
		
	}


}
