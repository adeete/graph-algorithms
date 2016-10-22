/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskalsalgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 1405093
 */
public class Kruskalsalgo {
    int V;
     Kruskalsalgo(int v){
    V=v;
    }
    class Node{
    int data,rank;
    Node parent;
    
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
   public static HashMap<Integer,Node>ms=new HashMap();
void makeset(int v){
Node node=new Node();
node.data=v;
node.parent=node;
node.rank=0;
ms.put(v, node);
}
Node findset(Node node){
Node parent=node.parent;
if(parent==node)
{
return parent;
}
node.parent=findset(node.parent);
return node.parent;
}
void union(int data1,int data2){
Node node1=ms.get(data1);
Node node2=ms.get(data2);
Node parent1=findset(node1);
Node parent2=findset(node2);
if(parent1.rank>=parent2.rank)
{
 parent1.rank=(parent1.rank==parent2.rank)?parent1.rank+1:parent1.rank;
parent2.parent=parent1;
}
else
{
parent1.parent=parent2;
}
}
public static HashMap<Pair<Integer,Integer>,Integer>graph=new HashMap();
void insert(int start,int end,int weight)
{
Pair<Integer,Integer>p = new Pair<Integer,Integer>(start,end);
p.setFirst(start);
p.setSecond(end);
graph.put(p,weight);
}
void kruskal(HashMap graph,int r){
graph=sortByValues(graph);
for(int i=0;i<V;i++){
makeset(i);
}
HashMap<Pair<Integer,Integer>,Integer>graph2=new HashMap();
          Set set2 = graph.entrySet();
      Iterator iterator2 = set2.iterator();
      while(iterator2.hasNext()) {
           Map.Entry me2 = (Map.Entry)iterator2.next();
          Pair<Integer,Integer>p=(Pair<Integer,Integer>) me2.getKey();
          if(findset(ms.get(p.first))==findset(ms.get(p.second))){
          
          }
          else
          {
          union(p.first,p.second);
          graph2.put(p, (Integer) me2.getValue());
          }
}
      int count=0;
Set set3 = graph2.entrySet();
      Iterator iterator3 = set3.iterator();
      while(iterator3.hasNext()) {
           Map.Entry me2 = (Map.Entry)iterator3.next();
      Pair<Integer,Integer>p=(Pair<Integer,Integer>) me2.getKey();
    count+=(int)me2.getValue();
      }
      System.out.println(2*count);
}
 private static HashMap sortByValues(HashMap map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
  }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int start,end,weight;
       
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t,p,n,m,a,b,c;
        String[] t1;
        t=Integer.parseInt(br.readLine());
       
        for(int i=0;i<t;i++){
         p=Integer.parseInt(br.readLine());
        n=Integer.parseInt(br.readLine());
        m=Integer.parseInt(br.readLine());
       
        
         Kruskalsalgo k=new Kruskalsalgo(n+1);
         for(int j=0;j<m;j++){
              t1=br.readLine().split(" ");
             a=Integer.parseInt(t1[0]);
        b=Integer.parseInt(t1[1]);
        c=Integer.parseInt(t1[2]);
        k.insert(a,b,c);}
        k.kruskal(graph,p);
        graph.clear();
        ms.clear();
        }
        
    }
}
