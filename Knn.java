package Position;

public class Knn {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] RSSI = new double[totaltest][totalbeacon+2];
	//	double[] RSSI4 = {-74,-64,-89,-84,-72,-55,-88,-97,-96,-60,0.0,1.0};//get
		double[] RSSI4 = {-63,-62,-63,0.0,1.0};
		double temp[] = new double [totalbeacon+2];
		double[] coff = new double[totaltest];
		double[] tmpcoff = new double[totaltest];
		RSSI = init();
		Sort_fliter(RSSI4);
		for(int j = 0;j < totalbeacon+2 ; j++){
			System.out.print(RSSI4[j]+" ");
		}
		System.out.println();
		for(int i = 0;i < totaltest ; i++){
			for(int j = 0;j < totalbeacon+2 ; j++){
				temp[j] = RSSI[i][j];	
			}
			Sort_fliter(temp);
			for(int j = 0;j < totalbeacon+2 ; j++){
				System.out.print(temp[j]+" ");
			}
			if(likely(temp,RSSI4) > -1)
				coff[i] = likely(temp,RSSI4);
			else
				coff[i] = -10;
			
		}
		for(int i = 0;i < totaltest ; i++){
			System.out.println(coff[i]+" ");
			tmpcoff[i] = coff[i];
		}
		
		Sort(coff);
		double nowx = 0;
		double nowy = 0;
		for(int i = 0;i < totaltest ; i++){
			for(int k = 1;k <= top ; k++){
				if(coff[totaltest-k] == tmpcoff[i]){
					nowx += RSSI[i][totalbeacon] ;
					nowy += RSSI[i][totalbeacon+1] ;
					break;
				}
			}
		}
		
		nowx = nowx/top;
		nowy = nowy/top;
		System.out.println(nowx +" "+nowy);
//		System.out.println(likely(RSSI,RSSI2));

	}
	
	private static int totaltest = 6;
	
	private static int totalbeacon = 3;//number of beacon
	
	private static int top = 4;
	
	private static double [][] RSSI = new double [totaltest][totalbeacon+2];
	
//	private static double temp[] = new double [totalbeacon+2];

/*	public static double[] settemp(double[] RSSI){
		double[] coff = new double[totaltest];
		double temp[] = new double [totalbeacon+2];
		for(int i = 0;i < totaltest ; i++){
			for(int j = 0;j < totalbeacon+2 ; j++){
				temp[j] = RSSI[i][j];	
			}
			Sort_fliter(temp);
			if(likely(temp,RSSI4) > -1)
				coff[i] = likely(temp,RSSI4);
			else
				coff[i] = -10;
			
		}
		
		return coff;
	}*/
	
	public static double[][] init(){
		//if cannot read : -150
	/*	double[][] RSSI = {{-72,-64,-92,-84,-72,-62,-88,-94,-90,-77,4.0,7.0},
							{-73,-68,-88,-84,-66,-60,-78,-89,-84,-71,1.0,1.0},
							{-80,-80,-64,-84,-92,-88,-68,-64,-71,-71,1.0,0.0}}; //data base
		*/
		double[][] RSSI = {{-76,-63,-84,7.0,1.0},
				{-64,-66,-62,3.0,2.0},
				{-75,-61,-65,4.0,4.0},
				{-75,-69,-55,4.0,7.0},
				{-75,-52,-64,8.0,5.0},
				{-67,-55,-59,3.0,6.0},}; //data base
		
		
		return RSSI;
		
	}
	
	public static void Sort_fliter(double[] rSSI){ //sort and choose top 5
        int j;
        double n;
        //----------------------------------------
        int minsave = 1;//choose top 5
        double A[] = new double [rSSI.length-2];
        int flag[] = new int [rSSI.length-2];
        for (int i = 0; i < rSSI.length-2; i++){
        	A[i] = rSSI[i];
        	flag[i] =0;
        }
        
        for (int i = 1; i < A.length; ++i){
            n = A[i];
            for (j = i - 1; j >= 0 && A[j] > n; --j)
                A[j + 1] = A[j];
            A[j + 1] = n;
        }
        
        for (int i = 0; i < minsave; i++){
        	A[i] = 0;
        	
        }
        for (int i = 0; i < rSSI.length-2; i++){
        	for (int k = 0; k < rSSI.length-2; k++){
        		if(rSSI[i] == A[k]) //«O¯d
        			flag[i] = 1;
        	}
        }
        for (int i = 0; i < rSSI.length-2; i++){
        	if(flag[i] != 1)
        		rSSI[i] = 0;
        }
    }
	
	public static void Sort(double[] array)
    {
        int j;
        double n;
        for (int i = 1; i < array.length; ++i)
        {
            n = array[i];
            for (j = i - 1; j >= 0 && array[j] > n; --j)
                array[j + 1] = array[j];
            array[j + 1] = n;
        }
    } 
	
	public static double avg(double[] b){ //couculate average
		double average = 0;
		double total = 0.0;
		
		for (int i = 0; i < b.length; i++){
			total += b[i]; 
		}
		average = total / b.length ;
		
		return average;
	}
	
	public static double add(double[] array){ //add all
		double sum = 0;
		for (int i = 0; i < array.length; ++i){
			sum = sum + array[i];
		}
		return sum;
	}
	
	
	public static double likely(double[] RSSI,double[] RSSI2){ //calculate R1 and R2 correlation coefficient
		int enough = 0;
		double corr = -1; 
		int c = 0;
		double tempup = 0;
		for(int i=0;i<totalbeacon;i++){
				if(RSSI[i] != 0 && RSSI2[i] != 0)
					enough ++;
		}
		
	/*	for(int i=0;i<totalbeacon;i++){
			System.out.print(RSSI[i]+" ");
		}
		System.out.println();
		for(int i=0;i<totalbeacon;i++){
			System.out.print(RSSI[i]+" ");
		}
		System.out.println();*/
	//	System.out.println(enough);
		System.out.println();
		if(enough >= 1){//--------------
			double A[] = new double [enough];
			double B[] = new double [enough];
			
			for(int i=0;i<totalbeacon;i++){
				if(RSSI[i] != 0 && RSSI2[i] != 0){
					A[c] = RSSI[i];
					B[c] = RSSI2[i];
					c++;
				}
			}
				
			double a_avg = avg(A);
			double b_avg = avg(B);
//			System.out.println("A: "+a_avg+"B: "+b_avg);
			double []temX = new double[enough];
			double []temY = new double[enough];
			
			for(int j=0;j<enough;j++){
				tempup += (A[j] - a_avg)*(B[j] - b_avg);
				temX[j] = (A[j] - a_avg)*(A[j] - a_avg);
				temY[j] = (B[j] - b_avg)*(B[j] - b_avg);
			}
			double a = add(temX);
			double b = add(temY);
			a = Math.sqrt(a);
			b = Math.sqrt(b);
			
			corr = tempup / (a*b);
		}
		return corr;
	}
	
}
