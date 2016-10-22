/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmatrix;
    import java.util.Stack;
/**
 *
 * @author 1405093
 */
public class Graphmatrix {



    Stack<Integer> st;
      int vFirst;

     private static int[][] adjMatrix;
      int[] isVisited;;

    
    private Graphmatrix(int i) {
        adjMatrix=new int[i][i];
isVisited = new int[i];}

void insert(int start,int end){
adjMatrix[start][end]=1;
}
    /**
     * @param args
     */
    public static void main(String[] args) {
     Graphmatrix g=new Graphmatrix(4);
       g.insert(0,1);
       g.insert(0, 2);
       g.insert(1,2);
       g.insert(2,0);
       g.insert(2,3);
       g.insert(3,3);


      g.DFS(adjMatrix);

    }

    public void DFS(int[][] Matrix) {

         this.adjMatrix = Matrix;
         st = new Stack<Integer>();
         int i;
         int[] node = {0, 1, 2, 3};
         int firstNode = node[2];
         depthFirst(firstNode, 4);



          }

          public void depthFirst(int vFirst,int n)
          {
          int v,i;

          st.push(vFirst);

          while(!st.isEmpty())
          {
              v = st.pop();
            
              for ( i=0;i<n;i++)
              {
                
                  if((adjMatrix[v][i] == 1) && (isVisited[i] == 0))
                  {
                    
                      st.push(v);
                      isVisited[i]=1;
                      System.out.print(" " + (i));
                      v = i;
                  }
              }
          }
}}

