/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author 1405093
 */
public class Graph {
    private int V;
    int[] inorder;
private HashMap<Integer,LinkedList> hmap=new HashMap<Integer,LinkedList>();
Graph(int vertices)
{
    V=vertices;
    for(int i=0;i<vertices;i++)
    {
     hmap.put(i,new LinkedList());
    }
   inorder=new int[V];
}
void insert(int start,int end){
    if(start>hmap.size())
    {
    
    }
    else
    {
     LinkedList<Integer>li=hmap.get(start);
     li.add(end);
     inorder[end]++;}
    }
Boolean iscyclicutil(Boolean[] visited,int p,Stack s){   //recursive helper function for iscyclic()
    s.push(p);
    visited[p]=true;
    Iterator<Integer>i=hmap.get(p).listIterator();
    while(i.hasNext())
    {
       int n=i.next();
             if (!visited[n])
                iscyclicutil(visited,n,s);
             else if(s.contains(n))
             {
              return true;
             }
    }
    s.pop();
    return false;
}
Boolean iscyclic(){        //to check whether a graph contains a cycle or not
Boolean[] visited=new Boolean[V];
Stack<Integer> s=new Stack();
for(int k=0;k<V;k++)
{
 visited[k]=false;}
for(int h=0;h<V;h++)
{
 if(iscyclicutil(visited,h,s))
 {
  return true;
 }
}
return false;
}
   void DFSUtil(int v,boolean visited[])   //recursive helper function for dfs
    {
        // Mark the current node as visited and print it
        visited[v] = true;
       System.out.print(v+" ");
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = hmap.get(v).listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        } 
        
    }
  void DFS()
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 for(int j=0;j<V;j++)
 {
  visited[j]=false;
 }
        // Call the recursive helper function to print DFS traversal
        for(int i=V-1;i>=0;i--)
        {
            if(visited[i]==false)
            {
               DFSUtil(i, visited);
            }
        }
      
    }
 void printalltopologicalsorts(LinkedList<Integer> res,Boolean[] visited){
int i;
 		int j;
 		boolean flag = false;
 		for(i=0;i<V;i++)
 		{
 			if(inorder[i]==0 && !visited[i])
 			{
 				visited[i] = true;
 				//remove this edge
 				Iterator<Integer> itr =hmap.get(i).listIterator();
 				while(itr.hasNext())
 			        {
 				     j = itr.next();
 				    inorder[j]--;  	
 				}
 				res.addLast(i);
 				printalltopologicalsorts(res,visited);
//backtracking
                                visited[i] = false;
 				itr = hmap.get(i).listIterator();
 				res.removeLast();
 				while(itr.hasNext())
 				{
 				     j = itr.next();
 				     inorder[j]++;  	
				}
 				flag = true;
 				
 			}
 		}
 		
 		if(!flag)
 		{
 			ListIterator<Integer> it = res.listIterator();
 			while(it.hasNext())
 			{
 				System.out.print(it.next()+" ");
 			}
 			System.out.println("");
 		}
 	
 }
 void all(){ //print all the possible toplogicalsorts of a graph
 Boolean[] visited=new Boolean[V];
 for(int i=0;i<V;i++){
 visited[i]=false;
 }
  LinkedList<Integer> res = new LinkedList<Integer>();
 printalltopologicalsorts(res,visited);
 }
    /**
     * @param args the command line arguments
     */

 void BFSutil(int s,boolean[] visited)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = hmap.get(s).listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
 void bfs(){
 boolean[] visited =new boolean[V];
 for(int j=0;j<V;j++)
 {
 visited[j]=false;
 }
 for(int i=V-1;i>=0;i--)
 {
  if(!visited[i]){
  BFSutil(i,visited);
 }
 }
 }
 Graph reverse(){  //to reverse a given graph's edges directions
 Graph k=new Graph(V);
 for(int v=0;v<V;v++){
 Iterator<Integer>i=hmap.get(v).listIterator();
 while(i.hasNext()){
 k.hmap.get(i.next()).add(v);
 }
 }
 return k;
 }
 void fillorder(int v,boolean[] visited,Stack s){  //to push vetices in stack through dfs traversal
 visited[v]=true;
 Iterator<Integer>i=hmap.get(v).listIterator();
 while(i.hasNext()){
 int node=i.next();
 if(!visited[node]){
 fillorder(node,visited,s);
 }
 }
 s.push(new Integer(v));
 }
 void printscc(){         //print all the strongly connected components of a graph
 boolean[] visited=new boolean[V];
 for(int i=0;i<V;i++)
 {
   visited[i]=false;
 }
 Stack<Integer> s=new Stack();
 for(int j=0;j<V;j++)
 {
   if(visited[j]==false)
   {
     fillorder(j,visited,s);
   }
 }
 Graph k=reverse();
 
 for(int h=0;h<V;h++)
 {
   visited[h]=false;
 }
 while(!s.isEmpty())
 {
   int v=s.pop();
   if(visited[v]==false)
   {
     k.DFSUtil(v,visited);
     System.out.println();
   }
 }
 }
 boolean isbipartite(int s)
 {
   int[] color=new int[V];
   for(int i=0;i<V;i++)
   {
     color[i]=-1; 
   } 
   Queue<Integer>q=new LinkedList<>();
   q.add(s);
   color[s]=1;
   while(!q.isEmpty())
   {
     int u=q.poll();
     Iterator<Integer>w=hmap.get(u).listIterator();
     while(w.hasNext())
     { int v=w.next();
       if(color[v]==-1)
       {
       color[v]=1-color[u];
       }
       else if(color[v]==color[u]){
       return false;
       }
     }
   }
     return true;
 }
    public static void main(String[] args) {
        // TODO code application logic here
      Graph g=new Graph(6);
       g.insert(5,2);
       g.insert(5,0);
       g.insert(4,0);
       g.insert(4,1);
       g.insert(2,3);
       g.insert(3,1);
       System.out.println("BFS");
       g.bfs();
   System.out.println();
   System.out.println("DFS");
   g.DFS();
   System.out.println();
   System.out.println("Topologicalsort");
   g.all();
   if(g.iscyclic()){
   System.out.println("cyclic");
   }
   else
   {
   System.out.println("acyclic");
   }
   g.printscc();
   if(g.isbipartite(0))
   {
   System.out.println("bipartite");
   } 
    else
   {
   System.out.println("No");
   }
    }
}
