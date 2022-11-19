
import java.util.LinkedList;
import java.util.Scanner;
class Edge{
	String src;
	String dest;
	int weight;
	Edge(String src,String dest,int weight)
	{
		this.src=src;
		this.dest=dest;
		this.weight=weight;
	}
}
public class Graph{
	int V;
	LinkedList<Edge>[] adj;

	int noOfPaths;
	int totalDistance=0;

	Graph(int V)
	{
		this.V=V;
		adj=new LinkedList[V];
		for(int i=0;i<V;i++)
		{
			adj[i]=new LinkedList<>();
		}
	}
	public void addEdge(String src,String dest,int weight) // adjacency list
	{
		Edge e1=new Edge(src,dest,weight);
		Edge e2=new Edge(dest,src,weight);
		int index1=(int)(src.charAt(0))-65;
		int index2=(int)(dest.charAt(0))-65;
		adj[index1].addFirst(e1);
		adj[index2].addFirst(e2);
	}
	public double finalCalcAvg(String a,String b)
	{
		boolean visited[]=new boolean[V]; //creating visited array of size V

		noOfPaths=0;
		totalDistance=0;
		visited[(int)(a.charAt(0))-65]=true;
		calc(a,b,visited,0);
		return ((double)totalDistance/(double) noOfPaths);
	}
	public void calc(String a,String b,boolean visited[],int dis)
	{
		if(a.equals(b)) //if source and destination nodes are same
		{
			noOfPaths++;
			totalDistance+=dis;
			return;
		}
		int index=(int)(a.charAt(0))-65;
		for(int i=0;i<adj[index].size();i++)
		{
			String to=adj[index].get(i).dest;
			int indexOfdest=(int)(to.charAt(0))-65;
			if(visited[indexOfdest]==false)  //if the node is not visited
			{
				visited[indexOfdest]=true;
				calc(to,b, visited, dis+adj[index].get(i).weight);
				visited[indexOfdest]=false;  //Important to mark as false to support other branch traversal
			}
		}
		return;
	}

	public static void main(String[] args)
	{
		Graph g=new Graph(5);
		g.addEdge("A","B",12);
		g.addEdge("A","C",13);
		g.addEdge("A","D",11);
		g.addEdge("A","E",8);
		g.addEdge("B","C",3);
		g.addEdge("C","E",4);
		g.addEdge("D","E",7);

		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Source Node");
		String a=scan.next();
		System.out.println("Enter Destination Node");
		String b= scan.next();
		double avgDis=g.finalCalcAvg(a,b);
		System.out.println("Average Distance- "+avgDis);
	}
}
