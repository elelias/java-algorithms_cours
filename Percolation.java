

//StdRandom

public class Percolation {
   private boolean site [][];
	private int N;
	private boolean isLeftSide [][];
	private boolean isRightSide [][];
	private boolean isUpSide [][];
	private boolean isDownSide [][];
	private WeightedQuickUnionUF union;
	private StdRandom rndm;
	private int geti;
	private int getj;
	private int getNFromij;


	//
	private int geti(int M) {
		int i = (int) M/N+1;
		return i;
	}
	private int getj(int M) {
		int j = M % N;
		if (j == 0) {
			j = 12;
		}
		return j;
	}
	private int getNFromij(int i, int j) {
		int M = (i-1)*N+j;
		return M;
	}

	private boolean isFinished() {
		return union.connected(0,N*N*1);
	}
   	public Percolation(int M) {              // create N-by-N grid, with all sites blocked
   	//this is the constructor
   		N = M;
   		//System.out.println(N);
   		union = new WeightedQuickUnionUF(N*N+2);   		
   		site  =  new boolean[N+1][N+1];
   		isUpSide = new boolean[N+1][N+1];
   		isDownSide = new boolean[N+1][N+1];
   		isLeftSide = new boolean[N+1][N+1];
   		isRightSide = new boolean[N+1][N+1];   		   		   		
   		for (int i = 1; i<N+1; i++) {
   			for (int j = 1;j<N+1;j++) {
   				site[i][j] = false;
				isUpSide[1][i] = false;
				isDownSide[N][i] = false;
				isLeftSide[i][1] = false;
				isRightSide[i][N] = false;

   			}
   			isUpSide[1][i] = true;
   			isDownSide[N][i] = true;
   			isLeftSide[i][1] = true;
   			isRightSide[i][N] = true;
   		}


   }
   	public void open(int i, int j) {
   		if (i <=  0 || i > N)throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
   		if (j <=  0 || j > N)throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");   		
   		//throw new IndexOutOfBoundsException("row index i out of bounds");   		
   		site[i][j] = true;
   		if (!isLeftSide[i][j]) {
   			if (isOpen(i,j-1)) {
   				union.union(getNFromij(i,j),getNFromij(i,j-1));
   			}
   		}
   		if (!isRightSide[i][j]) {
   			if (isOpen(i,j+1)) {
   				union.union(getNFromij(i,j),getNFromij(i,j+1));  				
   				//System.out.println("unioning"+getNFromij(i,j)+" "+getNFromij(i,j+1));   				
   			}
   		}
   		if (!isUpSide[i][j]) {
   			if (isOpen(i-1,j)) {
   				union.union(getNFromij(i,j),getNFromij(i-1,j));

   			}
   		}
   		else{
   			union.union(0,getNFromij(i,j));

   		}
   		if (!isDownSide[i][j]) {
   			if (isOpen(i+1,j)) {
   				union.union(getNFromij(i,j),getNFromij(i+1,j));  				
   			}
   		}
   		else{
   			//System.out.println("linking "+i+" and "+j+" which is "+getNFromij(i,j));   			
   			//System.out.println("with "+(N*N)+2);
   			//System.out.println("and N is worth "+N);
   			union.union((N*N)+1, getNFromij(i,j));
   		}

   	}         // open site (row i, column j) if it is not already
   	public boolean isOpen(int i, int j) {
   		if (i <=  0 || i > N) throw new java.lang.IndexOutOfBoundsException("row index "+i+" out of bounds");
   		if (j <=  0 || j > N) throw new java.lang.IndexOutOfBoundsException("row index "+i+" out of bounds");   		   		
   		return site[i][j];

   	}    // is site (row i, column j) open?
   	public boolean isFull(int i, int j) {
   		if (i <=  0 || i > N) throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
   		if (j <=  0 || j > N) throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");   		   	
   		if (!isOpen(i,j)){
            return false;
         }
         else{
            return union.connected(0,getNFromij(i,j));
         }

   	}   // is site (row i, column j) full?
   	public boolean percolates() {
         return union.connected(0,N*N+1);
   	}            
   	public static void main (String[] args) {
		//System.out.println("Hello");
		int N = 20;
		Percolation myPerc = new Percolation(N);
		boolean notDone = true;

		int nIterations = 0;
		while(notDone) {
			int left = StdRandom.uniform(N)+1;
			int right = StdRandom.uniform(N)+1;
			if (! myPerc.isOpen(left,right)) {
				myPerc.open(left,right);
				//notDone=!myPerc.union.connected(0,N*N+1);
				notDone = !myPerc.isFinished();
				//System.out.println(notDone);
				nIterations = nIterations+1;
			}
		}

		//System.out.println((double)nIterations/(N*N));


	}
}



