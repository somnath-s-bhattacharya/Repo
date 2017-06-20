package com.somnath.lru_cache;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {

	private DoublyLinkedList elementList;
	private Map<Integer, Node> elementMap;
	private final int cacheSize;

	public LRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		elementList = new DoublyLinkedList(cacheSize);
		elementMap = new ConcurrentHashMap<Integer, Node>();
	}

	public void get_element(int elementNumber) {
		Node elementNode = null;
		if(elementMap.containsKey(elementNumber)) {
			//move element to the start of list
			elementNode = elementMap.get(elementNumber);
			elementList.moveelementToHead(elementNode);
		}
	}

	public void put_element(int elementNumber) {
		Node elementNode = null;
		if(elementMap.containsKey(elementNumber)) {
			//move element to the start of list
			elementNode = elementMap.get(elementNumber);
			elementList.moveelementToHead(elementNode);
		} else {
			//add element to the cache
			if(elementList.getCurrSize() == elementList.getSize()) {
				//remove  tail from the cache elementList
				//remove it from map too
				elementMap.remove(elementList.getTail().getelementNumber());
			}
			elementNode = elementList.addelementToList(elementNumber);
			elementMap.put(elementNumber, elementNode);
		}
	}

	public void printCacheState() {
		StringBuilder resp=new StringBuilder();
		resp.append(elementMap.keySet().toString());
		System.out.println(resp.toString());
		System.out.println();
	}

	public String returnCacheState() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<elementList.printList().size();i++){
			if (elementList.printList().get(i)==null)
			{
				sb.append(" ");
			}
			else{
				sb.append(elementList.printList().get(i).toString());
			}
		}
		return sb.toString();
	}

	public void emptycache()
	{
		elementMap.clear();
		elementList.reset();
	}
}

class DoublyLinkedList {

	private final int size;
	private int currSize;
	private Node head;
	private Node tail;
	
	public void reset(){ head = null;}
	public static void reset(DoublyLinkedList l){l.reset();}

	public DoublyLinkedList(int size) {
		this.size = size;
		currSize = 0;
	}

	public Node getTail() {
		return tail;
	}

	public ArrayList<Node> printList() {
		ArrayList<Node> list = new ArrayList<Node>();
		Node current_state=null;
		if(head == null) {
			list.add(current_state);
		}
		Node tmp = head;
		while(tmp != null) {
			current_state = tmp;
			list.add(current_state);
			tmp = tmp.getNext();
		}
		return list;
	}

	public Node addelementToList(int elementNumber) {
		Node elementNode = new Node(elementNumber);       
		if(head == null) {
			head = elementNode;
			tail = elementNode; 
			currSize = 1;
			return elementNode;
		} else if(currSize < size) {
			currSize++;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		elementNode.setNext(head);
		head.setPrev(elementNode);
		head = elementNode;
		return elementNode;
	}

	public void moveelementToHead(Node elementNode) {
		if(elementNode == null || elementNode == head) {
			return;
		}

		if(elementNode == tail) {
			tail = tail.getPrev();
			tail.setNext(null);
		}

		Node prev = elementNode.getPrev();
		Node next = elementNode.getNext();
		prev.setNext(next);

		if(next != null) {
			next.setPrev(prev);
		}

		elementNode.setPrev(null);
		elementNode.setNext(head);
		head.setPrev(elementNode);
		head = elementNode;    
	}

	public int getCurrSize() {
		return currSize;
	}

	public void setCurrSize(int currSize) {
		this.currSize = currSize;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}  
}

class Node {

	private int elementNumber;
	private Node prev;
	private Node next;

	public Node(int elementNumber) {
		this.elementNumber = elementNumber;
	}

	public int getelementNumber() {
		return elementNumber;
	}

	public void setelementNumber(int data) {
		this.elementNumber = data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public String toString() {
		return elementNumber + "  ";
	}
}