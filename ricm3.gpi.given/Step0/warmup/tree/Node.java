package warmup.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a node of a tree.
 * Each node has a name.
 * Therefore, each node is reachable through a path.
 * Each node may be attached an object.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */

public class Node {
  Node m_parent;
  String m_name;
  List<Node> m_children;
  Object m_attachment;

  /**
   * @param name
   * @throws IllegalArgumentException if the name contains 
   *         the character Tree.pathSeparator
   */
  protected Node(String name) {
    if(name.contains(Tree.pathSeparatorString)) {
    	throw new IllegalArgumentException();
    }
    m_name = name;
    m_parent= null;
    this.m_children = new LinkedList<Node>();
    this.m_attachment = null;
    
    
  }

  /**
   * @param name
   * @throws IllegalArgumentException if the name contains 
   *         the character Tree.pathSeparator
   */
  public Node(Node parent, String name) {
	  if(name.contains(Tree.pathSeparatorString)) {
	    	throw new IllegalArgumentException();
	    }
	  if(parent.child(name) != null) throw new IllegalStateException();
	  if(parent instanceof Leaf) throw new IllegalStateException();
	  	m_name = name;
	    m_parent= parent;
	    this.m_children = new LinkedList<Node>();
	    this.m_attachment = null;
	    parent.m_children.add(this);
  }

  public String toString() {
    if (m_name == null)
      return "";
    return m_name;
  }

  public Node parent() {
    return m_parent;
  }

  public void attach(Object e) {
    m_attachment = e;
  }

  public Object attachment() {
    return m_attachment;
  }

  public void name(String name) {
    m_name = name;
  }

  public String name() {
    return m_name;
  }

  public String path() {
    Node n = this;
    String path = m_name;
    while(n.m_parent != null) {
    	path = n.m_parent.m_name +Tree.pathSeparator+path;
    	n = n.m_parent;
    }
    return path;
  }

  public void remove() {
    Node n = this;
    n = n.m_parent;
    n.m_children.remove(this);
    this.m_parent = null;
    
  }

  public Iterator<Node> children() {
    Iterator<Node> iter = this.m_children.listIterator();
    return iter;
    
  }
  
  public Node child(String name) {
	  Node current;
	  Iterator<Node> iter = children();
	  while(iter.hasNext()) {
		  current = iter.next();
		  if(current.m_name.equals(name) || current.m_name == name) {
			  return current;
		  }
	  }
	  return null;
  }

}
