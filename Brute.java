
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Brute 
{
	


    public static void main(String[] args) 
    {
        /* YOUR CODE HERE */        

  		StdDraw.setYscale(0, 32768);        
		StdDraw.setXscale(0, 32768);	

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
				int x0=Integer.parseInt(words[0]);
				int x1=Integer.parseInt(words[1]);				
				myArray.add(new Point(x0,x1));
			}else if(words.length ==3){
				int x0=Integer.parseInt(words[1]);
				int x1=Integer.parseInt(words[2]);				
				myArray.add(new Point(x0,x1));
			}
			else{
				System.out.println("bad input!!!");
				System.out.println(line);
			}			
		}
        
        Collections.sort(myArray);
        //nlines=6 
        //System.out.println("nlines is "+nLines);
        //System.out.println("and the size of the arrray "+myArray.size());
        for (int i = 0; i < nLines-3;++i){
        	for (int j = i+1; j < nLines-2;++j){
        		for (int k = j+1; k < nLines-1;++k){
        			for (int l = k+1; l< nLines;++l){
  

				        double slopep1p2 = myArray.get(i).slopeTo(myArray.get(j));				        
				        double slopep1p3 = myArray.get(i).slopeTo(myArray.get(k));

				        if (slopep1p2 != slopep1p3)continue;

				        double slopep1p4 = myArray.get(i).slopeTo(myArray.get(l));

				        if (slopep1p2 != slopep1p4)continue;        
				 
				        System.out.println(myArray.get(i).toString()+" -> "+myArray.get(j).toString()
				        	+" -> "+myArray.get(k).toString()+" -> "+myArray.get(l).toString());

						myArray.get(i).draw();
						myArray.get(j).draw();
						myArray.get(k).draw();
						myArray.get(l).draw();																		
						myArray.get(i).drawTo(myArray.get(l));

        			}
        		}
        	}
        }
    }

}

