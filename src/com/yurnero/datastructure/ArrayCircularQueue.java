package com.yurnero.datastructure;

import java.util.Scanner;

/**
 * TODO 有问题
 * 用数组实现一个循环队列
 * 
 * @author yunlong.wen
 *
 */
public class ArrayCircularQueue {
	public static void main(String[] args) {

		// 以字符串类型为例测试
		CircularQueue<String> array = new CircularQueue<String>(3);
		System.out.println("array = " + array.length());
		char input = ' ';
		try (Scanner scanner = new Scanner(System.in)) {
			boolean loop = true;
			while (loop) {
				System.out.println("a:	add");
				System.out.println("e:	exit");
				System.out.println("h:	head");
				System.out.println("l:	length");
				System.out.println("n:	next");
				System.out.println("p:	printQueue");
				input = scanner.next().charAt(0);
				switch (input) {
				case 'a': {
					try {
						System.out.println("please input String");
						String value = scanner.next();
						array.add(value);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 'e': {
					loop = false;
					break;
				}
				case 'h': {
					try {
						System.out.println(array.head());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 'l': {
					try {
						System.out.println("length is " + array.length());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 'n': {
					try {
						System.out.println(array.next());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}

				case 'p': {
					try {
						array.printQueue();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				default:
					break;
				}
			}
			scanner.close();
		}
	}

	public static class CircularQueue<T> {
		private int maxSize = 0;
		private int front = 0;// 队列第一个值的下标
		private int rear = -1;// 队列最后一个值的下标，默认是-1
		private T[] array = null;
		// 此处的思考（参考前包后不包的思路）：
		//循环链表，  |front-rear| < 2*maxSizes
		// 如果front = rear 说明队列只有一个值
		// 如果front > rear 说明队列是空的
		// 如果front < rear,则有效数据个数是rear - front + 1;
		private int mold = 0;

		@SuppressWarnings("unchecked")
		public CircularQueue(int maxSize) {
			array = (T[]) new Object[maxSize];
			this.maxSize = maxSize;
			this.mold = maxSize * 2;
		}

		public int length() {
			if (isEmpty()) {
				return 0;
			}
			return rear - front + 1;
		}

		public T next() {
			if (isEmpty()) {
				throw new RuntimeException("Queue is empty");
			}
			T value = array[front % maxSize];
			front = (front + 1) % mold;
			return value;
		}

		public void add(T data) {
			if (isFull()) {
				throw new RuntimeException("Queue is full");
			}
			rear = (rear + 1) % mold;
			array[rear % maxSize] = data;
		}

		public void printQueue() {
			if (isEmpty()) {
				System.out.printf("empty");
				return;
			}
			for (int i = front; i < front + length(); i++) {
				System.out.printf("index:%d, value:%s \n", i % maxSize, array[i % maxSize].toString());
			}

		}

		public T head() {
			if (isEmpty()) {
				throw new RuntimeException("Queue is empty");
			}
			return array[front];
		}

		private boolean isEmpty() {
			return front > rear;
		}

		private boolean isFull() {
			return !isEmpty() && (length() == maxSize);
		}
	}
}
