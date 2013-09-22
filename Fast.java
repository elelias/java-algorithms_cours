
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Fast 
{
    public static void main(String[] args) 
    {
  	/* YOUR CODE HERE */        

  		StdDraw.setYscale(0, 32768);        
		StdDraw.setXscale(0, 32768);	

  	//  READ THE FILE
        int nLines=0;
        ArrayList<Point> myArray=new ArrayList<Point>();
		String fileName =  args[0];
		Scanner sc=null;
		try{
			sc = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();  
		}
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] words = line.split("\\s+");
			if (words.length==1){
				nLines=Integer.parseInt(words[0]);
			}else if(words.length==2){
				//System.out.println("yeeeeeeah");
				int x0=Integer.parseInt(words[0]);
				int x1=Integer.parseInt(words[1]);				
				myArray.add(new Point(x0,x1));
			}else if(words.length ==3){
				int x0=Integer.parseInt(words[1]);
				int x1=Integer.parseInt(words[2]);				
				myArray.add(new Point(x0,x1));
			}
			else{
				System.out.println("bad input!");
			}
		}

		//INITIALIZE        
        int firstPoint=0;
        int endPoint=0;
        ArrayList<Point> auxArray=null;
        int i=-1;
        int nIter=0;

        //LOOPER OVER ALL THE POINTS
	    //System.out.println("going to iterate over "+nLines);
        while(nIter<=nLines){
        	nIter++;
        	//System.out.println("currently on "+nIter);
        	i++;
        	//
        	//
        	//System.out.println("myArray size "+myArray.size());
        	if (myArray.size()==0)return;
        	if (myArray.size()<4)return;
        	if (i>(myArray.size()-4))return;
        	//
        	Collections.sort(myArray);	
        	auxArray=new ArrayList<Point>(myArray);
        	Collections.sort(auxArray,auxArray.get(i).SLOPE_ORDER);
        	//System.out.println("sorted it!");
        	//
        	endPoint=0;
        	firstPoint=0;
        	boolean isLine=false;

        	for (int m=1; m<auxArray.size()-2; ++m){
       		
        		if (auxArray.get(0).slopeTo(auxArray.get(m)) == auxArray.get(0).slopeTo(auxArray.get(m+2))){
        			isLine=true;
        			firstPoint=m;
        			endPoint=m+2;   //0, m,m+1 and m+2 are in a line
        		}
        	}

        	if (!isLine)continue;

        	double slopeToFirst=auxArray.get(0).slopeTo(auxArray.get(firstPoint));
        	boolean extendLine=isLine;
        	int tryPoint=endPoint+1;
        	if (auxArray.size()<endPoint+2)extendLine=false;
        	//
        	while(extendLine){
        		double slope=auxArray.get(0).slopeTo(auxArray.get(tryPoint));
        		if (slope==slopeToFirst){
        			endPoint=tryPoint;
        			tryPoint++;
        		}
        		else{
        			extendLine=false;
        		}
        	}
        	String lineToPrint=auxArray.get(0).toString()+" -> ";

        	for (int k=firstPoint;k<=endPoint;++k){
        		if (k==endPoint){
        			lineToPrint+=" "+auxArray.get(k).toString();
        		}
        		else{
        			lineToPrint+=auxArray.get(k).toString()+" -> ";
        		}

        	}
        	System.out.println(lineToPrint);
        	auxArray.get(0).draw();
        	for (int j=firstPoint;j<=endPoint;++j)auxArray.get(j).draw();
        	auxArray.get(0).drawTo(auxArray.get(endPoint));


        	myArray = new ArrayList<Point>();
        	for (int k=1; k<firstPoint; ++k){
				myArray.add(auxArray.get(k));        		
        	}
        	for (int k=endPoint+1;k<auxArray.size();++k){
        		myArray.add(auxArray.get(k));
        	}
        	i=-1; //so that it starts again

        }
    }
}










