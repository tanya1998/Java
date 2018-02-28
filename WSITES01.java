package may;
import java.io.IOException;
import java.io.*;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//This is a Reader Class
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




class TrieNode 
{
    char content; 
    boolean isEnd; 
    int count;  
    LinkedList<TrieNode> childList; 
 
    /* Constructor */
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
        content = c;
        count = 0;
    }  
    public TrieNode subNode(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }
}
 
class Trie
{
    private TrieNode root;
 
     /* Constructor */
    public Trie()
    {
        root = new TrieNode(' '); 
    }
     /* Function to insert word */
    public void insert(String word)
    {
        if (search(word) == true) 
            return;        
        TrieNode current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.subNode(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.childList.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
            current.count++;
            //System.out.println("count"+current.count);
        }
        current.isEnd = true;
    }
    /* Function to search for word */
    public boolean search(String word)
    {
        TrieNode current = root;  
        //int counti =0;
        for (char ch : word.toCharArray() )
        { 
        	//System.out.print(ch);
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
           
            	
        } 
        
        if (current.isEnd == true) 
            return true;
        return false;
    }
    public String search1(String word)
    {
        TrieNode current = root; 
        char Character;
        int i=0;
        char arrc[] = new char[word.length()];
        
        ArrayList<Character> list = new ArrayList<Character>();
        for (char ch : word.toCharArray() )
        { //System.out.print(ch);
         arrc[i]=ch;
         i++;
            if (current.subNode(ch) == null)
            	{//System.out.println(list.toString());
            	char arrct[] = new char[i];
            	for(int j =0;j<i;j++)
            		arrct[j]=arrc[j];
                return String.valueOf(arrct) ;
            	}
            if(i==word.length())
            	return "true";
            else
                current = current.subNode(ch);
            
        }      
        if (current.isEnd == true) 
            return "true";
        //System.out.println(list.toString());
        char arrct[] = new char[i];
    	for(int j =0;j<i;j++)
    		arrct[j]=arrc[j];
        return String.valueOf(arrct);
    }
   
}





public class WSITES01 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader.init(System.in);
		int N = Reader.nextInt();
		Trie t = new Trie();
		String arr[] = new String[N];
		String s1;
		String s2;
		int j=0;
		for(int i=0;i<N;i++)
		{
			s1=Reader.next();
			s2=Reader.next();
			if(s1.compareTo("-")==0)
				{
				arr[j]=s2;
				j++;
				}
			else
			t.insert(s2);
			
			
		}
		if(j!=0)
		{String arrs[]=new String[j];
		int k =0;
		Arrays.sort(arr,0,j);
		String s= null;
		for(int i=0;i<j;i++)
		{
			
			s=t.search1(arr[i]);
			arrs[k]=s;
			if(s.compareTo("true")==0)
				break;
				
			k++;
			System.out.println();
		}
		Arrays.sort(arrs, 0, k);
		int ta=k;
		for( int i=k-1;i>0;i--)
		{
			if(arrs[i].compareTo(arrs[i-1])==0)
				{
				arrs[i]=null;
				ta--;
				}
		}
		if(s.compareTo("true")==0)
			System.out.println(-1);
		
		else
		{
			System.out.println(ta);
		
		for(int i=0;i<k;i++)
		{  if(arrs[i]!=null)
			{
			System.out.println(arrs[i]);
			}
		}
		
		}
		}
		else
			System.out.println(-1);
	}

}
