package warmup.layout;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is a container within a tree of containers and components. A container
 * is a component that has children components. The children are managed as an
 * ordered set. Children components are painted on top of their parent
 * container.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Container extends Component {

	LinkedList<Component> Children;

	Container() {
		super();
		this.Children = new LinkedList<Component>();
	}

	public Container(Container parent) {
		super(parent);
		this.Children = new LinkedList<Component>();
	}

	/**
	 * @return the number of components that are children to this container
	 */
	public int childrenCount() {
		return this.Children.size();
	}

	/**
	 * @return the component indexed by the given index.
	 */
	public Component childrenAt(int i) {
		Object[] retour = this.Children.toArray();
		return (Component) retour[i];
	}

	/**
	 * Select the component, on top, at the given location. The location is given in
	 * local coordinates. Reminder: children are above their parent.
	 * 
	 * @param x
	 * @param y
	 * @return this selected component
	 */
	public Component select(int x, int y) {
		Component tmp;
		if(inside(x,y)) {
			Component compRes = this;
			Iterator<Component> iter =this.Children.listIterator();
			while(iter.hasNext()) {
				tmp = iter.next();
				if(tmp.inside(x, y)) {
					compRes = tmp.select(x, y);
				}
			}
			return compRes;
		}
		else return null;
	}
}
