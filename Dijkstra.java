package refresher;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Reader{
	  static BufferedReader reader;
	  static StringTokenizer tokenizer;

	  /** call this method to initialize reader for InputStream */
	  static void init(InputStream input) {
	      reader = new BufferedReader(
	                   new InputStreamReader(input) );
	      tokenizer = new StringTokenizer("");
	  }

	  /** get next word */
	  static String next() throws IOException {
	      while ( ! tokenizer.hasMoreTokens() ) {
	          //TODO add check for eof if necessary
	          tokenizer = new StringTokenizer(
	                 reader.readLine() );
	      }
	      return tokenizer.nextToken();
	  }

	  static int nextInt() throws IOException {
	      return Integer.parseInt( next() );
	  }
		
	  static double nextDouble() throws IOException {
	      return Double.parseDouble( next() );
	  }
	  static long nextLong() throws IOException {
	      return Long.parseLong( next() );
	  }
	 
	}
class heaps
{
	
	int heapsize;
	public int [][] heapify(int[][] array,int i)
	{	
		heapsize =array.length-1;
		boolean flag =true;
		while( i<= (heapsize-1)/2 && flag )
		{	int min = i;
			int l =(2*i)+1;
			int r =(2*i)+2;
		if(l<=heapsize && array[l][1]<array[i][1])
		{
			min =l;
		}
		if(r<=heapsize && array[r][1]<array[min][1])
			min = r;
		if(min!=i)
			{
				int temp = array[i][0];
				int data = array[i][1];
				array[i][0]= array[min][0];
				array[i][1]=array[min][1];
				array[min][0]=temp;
				array[min][1]=data;
				i=min;
				//array=heapify(array,min);
			}
		else 
			flag = false;
		}
		return array;
	}
	
	public int[][] extract_min(int[][] arr)
	{
		
		arr[0][0]=arr[(arr.length)-1][0];
		arr[0][1]=arr[arr.length-1][1];
		int[][] arrnew = new int[arr.length-1][2];
		for(int j =0; j<arrnew.length;j++)
		{
			arrnew[j][0]=arr[j][0];
			arrnew[j][1]=arr[j][1];
			
		}
		arrnew=heapify(arrnew,0);
		return(arrnew);
		
		
	}
	public void build(int[][] a)
	{
		for(int i=(a.length-2)/2;i>=0;i--)
		{
			a=heapify(a,i);
			
		}
	}
}
public class Dijkstra {
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		Dijkstra obj1 = new Dijkstra();
		
		// TODO Auto-generated method stub
		int C = Reader.nextInt();
		int R = Reader.nextInt();
		int[][] distmat = new int[C][C];
		for(int i=0;i<C;i++)
		{
			for(int j =0;j<C;j++)
				distmat[i][j]=10000;
		}
			for(int i=0;i<R;i++)
			{
			int j =Reader.nextInt();
			int k =Reader.nextInt();
			int dist =Reader.nextInt();
			distmat[j-1][k-1]=dist;
			distmat[k-1][j-1]=dist;
		
			
		}
			int ib = Reader.nextInt();
			int arr[][] = new int[C-1][2];
		 for(int i =0;i<C-1;i++)
	        {  	if(i==0)
	        	arr[i][0]=i+1;
	        else
	        	arr[i][0]=arr[i-1][0]+1;
	        	arr[i][1]=10000;
	        if(arr[i][0] == ib)
	        		arr[i][0]=ib+1;
	        	
	        }
		 for(int i = 0;i<C-1;i++)
		 {
			 System.out.print(arr[i][0]+" ");
		 }
	       int[][] arr3 = obj1.Dijks(arr,distmat,C,ib-1);
	       for (int i =0;i<C;i++)
	       {
	       System.out.println(arr3[i][0]+" "+arr3[i][1]+" "+arr3[i][2]);
	       }
}

public int[][] Dijks (int[][] arr1,int[][] distmat, int C,int ib)
{	
     int[][] arr2 = new int[C][3];
     for(int i =0;i<C;i++)
     {  	if(i==0)
     	arr2[i][0]=i+1;
     else
     	arr2[i][0]=arr2[i-1][0]+1;
     	arr2[i][1]=10000;
     	
     	
     }
     arr2[ib][2]=ib+1;//ib is the source vertex
     arr2[ib][1]=0;
	   heaps obj = new heaps();
	 
	   while(arr1.length!=0)
	   {  
			   for(int i=0;i<arr1.length;i++)
		   
		   {
			   
			
		   int t =arr2[ib][1]+distmat[ib][arr1[i][0]-1];
			   System.out.println("t"+t);
			   System.out.println("ib"+ib);
			   System.out.println("i");
		   if(t<arr1[i][1])
		   {
			   arr1[i][1]=t;
			   arr2[i+1][1]=t;
			   arr2[i+1][2]=ib+1;
		   }
		   }
		   obj.build(arr1);
		  
		   ib = arr1[0][0]-1;
		   arr1 =obj.extract_min(arr1);
		   
	   }
	  

	   return arr2;
}
}
