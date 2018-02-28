import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Reader {
	  static BufferedReader reader;
	  static StringTokenizer tokenizer;

	  /** call this method to initialize reader for InputStream */
	  static void init(InputStream input) {
	      reader = new BufferedReader(new InputStreamReader(input));
	      tokenizer = new StringTokenizer("");
	  }
	  static String next() throws IOException {
	      while ( ! tokenizer.hasMoreTokens() ) {
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

public class FoldFulkerson {
	int V;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FoldFulkerson obj = new FoldFulkerson();
		Reader.init(System.in);
		obj.V = Reader.nextInt();
		 int Flow[][] =new int[][] { {0, 10, 0, 8, 0, 0},
             {0, 0, 5, 2, 0, 0},
             {0, 0, 0, 0, 0, 7},
             {0, 0, 0, 0, 10,0},
             {0, 0, 8, 0, 0, 10},
             {0, 0, 0, 0, 0, 0}
		 };
		 System.out.println(obj.FF(Flow, 0, 5));

	}
	boolean bfs(int Flow[][] , int src, int dest, int pr[])
	{
		//Reference: GeeksForGeeks
		boolean visit[] = new boolean[V];
		for(int i=0;i<V;i++)
		{
			visit[i] = false;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		visit[src] = true;
		pr[src] = -10;
		while (q.size()!=0)
	    {
			int u = q.poll();
			for (int j=0; j<V; j++)
	        {
	          if (visit[j]==false && Flow[u][j] > 0)
	            {
	                    q.add(j);
	                    pr[j] = u;
	                    visit[j] = true;
	            }
	        }
	    }
		return (visit[dest] == true);
	}
	int FF(int Flow[][], int src, int dest)
	{
		 
		 int pr[] = new int[V];
		 int max = 0;
		 int path_flow ;
		 while (bfs(Flow, src, dest, pr))
	        {
	           path_flow = Integer.MAX_VALUE;
	            for (int v=dest; v!=src; v=pr[v])
	            {
	                int u = pr[v];
	                path_flow = Math.min(path_flow, Flow[u][v]);
	            }
	
	            for (int j=dest; j!= src; j=pr[j])
	            {
	                int u=pr[j];
	                Flow[u][j] -= path_flow;
	                Flow[j][u] += path_flow;
	            }
	            max += path_flow;
	        }
		 return max;
		 
	}

}
