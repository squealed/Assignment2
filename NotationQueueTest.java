import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotationQueueTest {
	public NotationQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	public ArrayList<Double> fillD = new ArrayList<Double>();
	// STUDENT: student tests will use the doubleQ
	public NotationQueue<Double> doubleQ;
	// STUDENT: add variables as needed for your student tests

	@Before
	public void setUp() throws Exception {
		stringQ = new NotationQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		//STUDENT: add setup for doubleQ for student tests
		doubleQ=new NotationQueue<Double>(6);
		doubleQ.enqueue(14.0);
		doubleQ.enqueue(56.0);
		doubleQ.enqueue(179.0);
	}

	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false,stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
		
		//STUDENT
		assertEquals(false,doubleQ.isEmpty());
		doubleQ.dequeue();
		doubleQ.dequeue();
		doubleQ.dequeue();
		assertEquals(true, doubleQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}
	
	@Test
	public void testDequeueStudent() {
		try {
			assertEquals(14.0, doubleQ.dequeue(),.0);
			assertEquals(56.0, doubleQ.dequeue(),.0);
			assertEquals(179.0, doubleQ.dequeue(),.0);
			
			doubleQ.dequeue(); //Queue is empty, next statement should cause QueueUnderFlowException
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}

	@Test
	public void testSize() throws QueueUnderflowException, QueueOverflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
		
		//STUDENT
		doubleQ.enqueue(200.0);
		assertEquals(4, doubleQ.size());
		doubleQ.dequeue();
		assertEquals(3, doubleQ.size());
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testEnqueueStudent() {
		try {
			assertEquals(3, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(48.0));
			assertEquals(4, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(20.0));
			assertEquals(5, doubleQ.size());
			
			doubleQ.enqueue(45.0); 
			doubleQ.enqueue(32.34); //Queue is full, next statement should cause QueueOverFlowException
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testIsFull() throws QueueOverflowException {
	
		//STUDENT
		assertEquals(false,doubleQ.isFull());
		doubleQ.enqueue(48.0);
		doubleQ.enqueue(20.0);
		doubleQ.enqueue(45.0);
		assertEquals(true, doubleQ.isFull());
	}

	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
		
		
	}
	
	@Test
	public void testToStringStudent() throws QueueOverflowException {
		String stackString=doubleQ.toString();
		assertEquals(stackString,"14.056.0179.0");
		doubleQ.enqueue(38.0);
		assertEquals(doubleQ.toString(),"14.056.0179.038.0");
	}

	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		
		//STUDENT
		assertEquals("14.0|56.0|179.0", doubleQ.toString("|"));
		doubleQ.enqueue(3.45);
		assertEquals("14.0|56.0|179.0|3.45", doubleQ.toString("|"));
	}

	@Test
	public void testFill() throws QueueUnderflowException {
		
		//STUDENT
		fillD.add(100.0);
		fillD.add(23.0);
		fillD.add(67.0);
		//start with an empty queue
		doubleQ = new NotationQueue<Double>(6);
		//fill with an ArrayList
		doubleQ.fill(fillD);
		assertEquals(3,doubleQ.size());
		assertEquals(100.0, doubleQ.dequeue(),.0);
		assertEquals(23.0, doubleQ.dequeue(),.0);
		assertEquals(67.0, doubleQ.dequeue(),.0);
	}

}