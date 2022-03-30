import java.util.*;

public class MyClass {
    public enum Op { LT, GTE; }
    
    public static class Node {
        Node parent;
        Node left;
        Node right;
        float val;
        String feat;
        Node( String feat, float val, Node parent){
            this.feat = feat;
            this.val = val;
            this.parent = parent;
        }
        public boolean isleaf(){ return null == left && null == right; }
        public String toString(){ return feat; }
    }
    
    public static class Rule {
        List<Node> chain = new ArrayList<Node>();
    }
    
    public static List<Rule> ALL_RULES = new ArrayList<>();
        
    public static void main(String args[]) {
      Node root = createTree();
      traverse( root );
      print();
      
    }
    
    public static void print(){
        for( Rule rule : ALL_RULES ){
            for( Node n : rule.chain ){
                System.out.print( " -> "+ n );
            }
            System.out.println("");
        }
    }
    
    public static void traverse( Node root ){
        
        if( root.isleaf() ){
            Rule rule = new Rule();
            rule.chain.add( root );
            Node parent = root.parent;
            while( null != parent ){
                rule.chain.add(0, parent);
                parent = parent.parent;
            }
            ALL_RULES.add( rule );
            return;
        }
        
        if( null != root.left ){
            traverse( root.left );
        }
        if( null != root.right ){
            traverse( root.right );
        }
        
    }
    
    //             A
    //            / \
    //           B   C
    //          / \   \
    //         D  E    F
    //        /  / \  / \
    //       G  H  I J   K
    static Node createTree(){
      Node a = new Node("A", 0.1f, null);
      
      Node b = new Node("B", 0.1f, a);
      Node c = new Node("C", 0.1f, a);
      
      Node d = new Node("D", 0.1f, b);
      Node e = new Node("E", 0.1f, b);
      Node f = new Node("F", 0.1f, c);
      
      Node g = new Node("G", 0.1f, d);
      Node h = new Node("H", 0.1f, e);
      Node i = new Node("I", 0.1f, e);
      Node j = new Node("J", 0.1f, f);
      Node k = new Node("K", 0.1f, f);
      
      a.left = b;
      a.right = c;
      b.left = d;
      b.right = e;
      c.right = f;
      d.left = g;
      e.left = h;
      e.right = i;
      f.left = j;
      f.right = k;
      
      return a;
    }
    
    
}


