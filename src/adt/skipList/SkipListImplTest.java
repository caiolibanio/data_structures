package adt.skipList;

import org.junit.Assert;
import org.junit.Test;

public class SkipListImplTest {

	SkipListImpl<Integer> list = new SkipListImpl<Integer>(5);

	@Test
	public void testInsertIntV() {
		SkipNode<Integer> node1 = new SkipNode<Integer>(0, 1, 0);
		SkipNode<Integer> node2 = new SkipNode<Integer>(1, 2, 1);
		SkipNode<Integer> node3 = new SkipNode<Integer>(2, 3, 2);
		SkipNode<Integer> node4 = new SkipNode<Integer>(3, 2, 3);
		SkipNode<Integer> node5 = new SkipNode<Integer>(4, 1, 4);

		Assert.assertEquals(null, list.search(0));

		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);

		Assert.assertTrue(node1.satteliteData.equals(list.search(0).satteliteData));
		Assert.assertTrue(node2.satteliteData.equals(list.search(1).satteliteData));
		Assert.assertTrue(node3.satteliteData.equals(list.search(2).satteliteData));
		Assert.assertTrue(node4.satteliteData.equals(list.search(3).satteliteData));
		Assert.assertTrue(node5.satteliteData.equals(list.search(4).satteliteData));
	}

	@Test
	public void testInsertIntVInt() {
		SkipNode<Integer> node1 = new SkipNode<Integer>(0, 1, 0);
		SkipNode<Integer> node2 = new SkipNode<Integer>(1, 2, 1);
		SkipNode<Integer> node3 = new SkipNode<Integer>(2, 3, 2);
		SkipNode<Integer> node4 = new SkipNode<Integer>(3, 2, 3);
		SkipNode<Integer> node5 = new SkipNode<Integer>(4, 1, 4);

		Assert.assertEquals(null, list.search(0));

		list.insert(0, 0, 1);
		list.insert(1, 1, 2);
		list.insert(2, 2, 3);
		list.insert(3, 3, 2);
		list.insert(4, 4, 1);

		Assert.assertTrue(node1.satteliteData.equals(list.search(0).satteliteData));
		Assert.assertTrue(node2.satteliteData.equals(list.search(1).satteliteData));
		Assert.assertTrue(node3.satteliteData.equals(list.search(2).satteliteData));
		Assert.assertTrue(node4.satteliteData.equals(list.search(3).satteliteData));
		Assert.assertTrue(node5.satteliteData.equals(list.search(4).satteliteData));
	}

	@Test
	public void testRemove() {
		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);
				
		list.remove(0);	
		Assert.assertTrue(null == list.search(0));
		
		list.remove(1);
		Assert.assertTrue(null == list.search(1));
		
		list.remove(2);
		Assert.assertTrue(null == list.search(2));
		
		list.remove(3);
		Assert.assertTrue(null == list.search(3));
		
		list.remove(4);
		Assert.assertTrue(null == list.search(4));
	}

	@Test
	public void testHeight() {
		Assert.assertEquals(list.maxLevel, list.height());
		Assert.assertEquals(5, list.height());
	}

	@Test
	public void testSearch() {
		SkipNode<Integer> node1 = new SkipNode<Integer>(0, 1, 0);
		SkipNode<Integer> node2 = new SkipNode<Integer>(1, 2, 1);
		SkipNode<Integer> node3 = new SkipNode<Integer>(2, 3, 2);
		SkipNode<Integer> node4 = new SkipNode<Integer>(3, 2, 3);
		SkipNode<Integer> node5 = new SkipNode<Integer>(4, 1, 4);

		Assert.assertEquals(null, list.search(0));

		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);

		Assert.assertTrue(node1.satteliteData.equals(list.search(0).satteliteData));
		Assert.assertTrue(node2.satteliteData.equals(list.search(1).satteliteData));
		Assert.assertTrue(node3.satteliteData.equals(list.search(2).satteliteData));
		Assert.assertTrue(node4.satteliteData.equals(list.search(3).satteliteData));
		Assert.assertTrue(node5.satteliteData.equals(list.search(4).satteliteData));

		Assert.assertTrue(null == (list.search(7)));
		Assert.assertTrue(null == (list.search(8)));
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void testToArray() {
		SkipNode<Integer> node1 = new SkipNode<Integer>(0, 1, 0);
		SkipNode<Integer> node2 = new SkipNode<Integer>(1, 2, 1);
		SkipNode<Integer> node3 = new SkipNode<Integer>(2, 3, 2);
		SkipNode<Integer> node4 = new SkipNode<Integer>(3, 2, 3);
		SkipNode<Integer> node5 = new SkipNode<Integer>(4, 1, 4);

		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);

		SkipNode[] array = { node1, node2, node3, node4, node5 };
		SkipNode[] listArray = list.toArray();

		Assert.assertEquals(array.length, listArray.length);

		for (int i = 0; i < listArray.length; i++) {
			Assert.assertTrue(array[i].satteliteData
					.equals(listArray[i].satteliteData));
		}
	}

}
