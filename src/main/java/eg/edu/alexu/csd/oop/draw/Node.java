package eg.edu.alexu.csd.oop.draw;

public class Node {
    /**
     * element is the element which will be stored in the node
     * 
     * 
     * 
     * 
     *
     */
    private Object element;
    /**
     * next is the next node which this node points to
     */
    private Node next;
    /**
     * Empty Constructor of the node class
     */
    public Node() {
        this(null,null);
    }
    /**
     * Constructor of the node class which takes 2 parameters
     * @param element
     * The element which will be stored in the node
     * @param next
     * The next Node that this node will point to
     */
    public Node(Object element,Node next) {
        this.element=element;
        this.next=next;
    }
    /**
     * 
     * @return The element stored in the node
     */
    public Object getElement() {
        return element;
    }
    
    /**
     * 
     * @return The next node which this node points to
     */
    public Node getNext() {
        return next;
    }
    /**
     * 
     * @param element
     * The element which will be stored in the node
     */
    public void setElement(Object element) {
        this.element=element;
    }
    /***
     * 
     * @param next 
     * The next node which this node points to 
     */
    public void setNext(Node next) {
        this.next=next;
    }
    
    
    }