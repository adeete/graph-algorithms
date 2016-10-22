/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijikstra_algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

/**
 *
 * @author 1405093
 */
public class Dijikstra_algo {

   int V;
 private HashMap<Integer,LinkedList<Pair<Integer,Integer>>>graph=new HashMap();  //hashmap used to store the edge and their weight
  Dijikstra_algo(int vertices)   //initializing the graph
   {
        V=vertices;
        for(int i=0;i<vertices;i++)
        {
          graph.put(i,new LinkedList());
         }

   }          
  public class Pair<F, S> {
    private F first; //first member of pair
    private S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
void insert(int start,int end,int weight)
{
 LinkedList<Pair<Integer,Integer>>li=graph.get(start);
 li.add(new Pair(end,weight));
 LinkedList<Pair<Integer,Integer>>li1=graph.get(end);
 li1.add(new Pair(start,weight));
}
void dijikstra(int start)
{
    
    int[] key=new int[V];            //array to store the the weights
    PriorityQueue<Pair<Integer,Integer>>pq=new PriorityQueue(V,new Comparator<Pair<Integer,Integer>>() {

        @Override
        public int compare(Pair<Integer, Integer> t, Pair<Integer, Integer> t1) {
           return t.first.compareTo(t1.first);
        }
    });      //prioroty queue to store weights of the corresponding vertices
    
    boolean[] visited=new boolean[V];
    for(int i=0;i<V;i++)
    {
      if(i!=start)
      {
          visited[i]=false;
          pq.add(new Pair(Integer.MAX_VALUE,i));
          key[i]=Integer.MAX_VALUE;
      }
    }
    pq.add(new Pair(0,start));       //initialising the weight of the start vertex as 0
    visited[start]=false;
    key[start]=0;
    Pair pi;
    while(!pq.isEmpty())
    {
     pi=pq.poll();                  //deleting the vertex with the least weight
     int vertex=(int) pi.second;
     int uweight=(int)pi.first;
     if(!visited[vertex])
     {
         ListIterator<Pair<Integer, Integer>>i=graph.get(vertex).listIterator();  //getting the adjacent vertices of the vertex de;eted
      while(i.hasNext())
      {
        Pair pw=i.next();
        int n=(int) pw.first;
        int w1=key[n];
        int w=(int)pw.second;
        if(!visited[n] && uweight+w<=w1)   //if weight of the adjacent vertex in the graph is less than the weight of that vertex in the priority queue
        {
          key[n]=w+uweight;
          pq.add(new Pair(w+uweight,n));
        }
     }
    }
  visited[vertex]=true; 
    }
    for(int j=0;j<V;j++)
    {
        System.out.println(j+" "+key[j]);
    }
}

    public static void main(String[] args) {
        // TODO code application logic here
Dijikstra_algo p=new Dijikstra_algo(9);
    p.insert(0,1,4);
    p.insert(0,7,8);
    p.insert(1,2,8);
    p.insert(1,7,11);
    p.insert(2,3,7);
     p.insert(2,8,2);
    p.insert(2,5,4);
    p.insert(3,4,9);
    p.insert(3,5,14);
    p.insert(4,5,10);
    p.insert(5,6,2);
    p.insert(6,7,1);
    p.insert(6,8,6);
    p.insert(7,8,7);
  
    p.dijikstra(0);
    }
}
