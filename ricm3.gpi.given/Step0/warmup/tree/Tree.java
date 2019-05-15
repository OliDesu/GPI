package warmup.tree;

import java.util.Iterator;

/**
 * This is a tree of named node.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */
public class Tree extends Node {

  public static final char pathSeparator = '/';
  public static final String pathSeparatorString = "/";

  public Tree() {
    super("");
  }

  /**
   * Finds a node corresponding to a path in the tree.
   * If the path does not exists, throws NotFoundException
   *  
   * @param path
   *          of the searched node
   * @return the node named by the given path
   * @throws NotFoundException
   *           if the path does not exist
   */
  public Node find(String path) throws NotFoundException {
	if(path.charAt(0) != '/') throw new IllegalArgumentException();
	String[] S =path.split(pathSeparatorString);
	
	Node current = this;
	Object proof = null;
	for(int i =1; i < S.length;i++) {
		 proof = current.child(S[i]);
		 if (proof == null) {
			 throw new NotFoundException("NFE");
		 }
		 
		 current = current.child(S[i]);
		
		 
	}
	return current;
    
  }

  /**
   * Make a path in the tree, leading either to a leaf or to a node.
   * @throws IllegalStateException
   *         if the path should be to a leaf but it already exists
   *         to a node, of if the path should be to a node but it already
   *         exists to a leaf. 
   */
  public Node makePath(String path, boolean isLeaf) {
	  String[] S =path.split(pathSeparatorString);
	  Node current = this;
	  int i =1;
	  int j;
	  Object proof=current.child(S[i]);
	  
	 while(i < S.length && proof != null ) {
		  current = current.child(S[i]);
		  i++;
		  if(i < S.length)
		  proof = current.child(S[i]);  
		  
		  
	  }
		 
	  if(i == S.length) {
		  if(current instanceof Leaf && isLeaf) return current;
		  else if (current instanceof Node && isLeaf) throw new IllegalStateException();
	  }
	  if(proof == null && i < S.length && !isLeaf) {
		  for( j = i ; j < S.length ;j++) {
			  Node tmp = new Node(current,S[j]);
			  current.m_children.add(tmp);
			  current = current.child(S[j]);
			
		  }
		  	
		  return current;
	  }
	  else if(proof == null && i < S.length && isLeaf) {
		  for( j = i ; j < S.length-1 ;j++) {
			  Node tmp = new Node(current,S[j]);
			  current.m_children.add(tmp);
			  current = current.child(S[j]);
		  }
		  Leaf retour = new Leaf(current, S[j]);
		  return retour;
	  }
	 
	return null;
	  
	  
  }


  public String toString() {
    TreePrinter p = new TreePrinter(this);
    return p.toString();
  }

}
