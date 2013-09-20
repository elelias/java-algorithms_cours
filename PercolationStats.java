



public class PercolationStats {
	private int N;
	private int T;
	private Percolation myPerc;
	//	private double[] iterArray;
	private double sumIter;
	private double sumSq;
   	public PercolationStats(int M, int Q) { 

		if (M <=  0 || Q <=  0) { 
			throw new java.lang.IllegalArgumentException("bad");
		}
				 //if either N ≤ 0 or T ≤ 0.
   		T =  Q;
   		N =  M;
   		//iterArray =  new double[T];

   		sumIter=0;
   		sumSq=0;
   		for (int i =  0;i<T;i++) { 
			myPerc =  new Percolation(N);
			boolean notDone =  true;
			int nIterations =  0;
			while(notDone) { 
				int left  =   StdRandom.uniform(N)+1;
				int right =  StdRandom.uniform(N)+1;
				if (! myPerc.isOpen(left,right)) { 
					myPerc.open(left,right);
					//notDone =  !myPerc.union.connected(0,N*N+1);
					notDone = !myPerc.percolates();
					//System.out.println(notDone);
					nIterations =  nIterations+1;
				}
			//myPerc=null;	
			}
			sumIter += (double)nIterations/(N*N);
			sumSq+=(double)nIterations/(N*N)*nIterations/(N*N);
			//sumFrac=sumIter/(N*N);				
			//System.out.println(nIterations);
		}
		
   	}
   	public double mean() { 
   		double myMean =  (double)sumIter/T;
   		return myMean;
   	}                  // sample mean of percolation threshold
   	public double stddev() { 

   		double sigsq =  0.0;
   		double myMean=this.mean();
   		sigsq=(double)sumSq-T*myMean*myMean;
	//   		for (int i =  0;i<T;++i) { 
   	//		sigsq =  sigsq+(iterArray[i]-myMean)*(iterArray[i]-myMean);
   	//	}
   		//
   		double myStddev =  Math.pow(sigsq/(T-1),0.5);
   		return myStddev;

   	}
   	public double confidenceLo() {  
   		double myMean=this.mean();
   		double myStddev=this.stddev();
   		double myconfLo =  myMean-(1.96*myStddev)/Math.pow(T,0.5);
   		return myconfLo;
   	}
   	public double confidenceHi() {
   		double myMean=this.mean();
   		double myStddev=this.stddev();
   		double myconfHi =  myMean+(1.96*myStddev)/Math.pow(T,0.5);
   		return myconfHi;
   	}          // returns upper bound of the 95% confidence interval


   	public static void main(String[] args) { 	

   		int size =  Integer.parseInt(args[0]);
   		int repeats =  Integer.parseInt(args[1]);
   		//int size =  20;
   		//int repeats =  200;
		PercolationStats myStat =  new PercolationStats(size,repeats);
		double theMean =  myStat.mean();
		double theStddev =  myStat.stddev();
		double theLO =  myStat.confidenceLo();
		double theHi =  myStat.confidenceHi();
		System.out.println("mean                     =   "+theMean);
		System.out.println("stddev                   =   "+theStddev);	
		System.out.println("95% confidence interval  =   "+theLO+", "+theHi);



		//   		
	}
}