
public class BinaryTree {
	
	public static Queue<BTNode<dataNode>> makeQueue(double[] a){

		double[] b =  new double[a.length];
		
		for (int x = 0; x < a.length; x++)
		{
			b[x] = a[x];
		}
		
		Queue<BTNode<dataNode>> list = new Queue<BTNode<dataNode>>(); 
	
		int i = 0;
		while (i < b.length)
		{
			dataNode current = new dataNode();
			current.value = b[i];
			current.min = b[i];
			current.max = b[i];
			current.count = 0;
			
			for (int j = i; j < b.length; j++)
			{
				if(b[j] == b[i])
				{
					
					current.count++;
					
				}
			}
			BTNode<dataNode> currBTNode = new BTNode<dataNode>(current, null, null, null);
			list.enqueue(currBTNode);
			i = i + current.count;
		}
		return list;
	}
	
	private static BTNode<dataNode> leftMax(BTNode<dataNode> root){
		while(root.getData().count == 0) {
			
			root = root.getRight();
		}
		return root;
		
	}
	
	private static BTNode<dataNode> rightMin(BTNode<dataNode> root){
		while(root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
		
	}

	
	public static Queue<BTNode<dataNode>> join(Queue<BTNode<dataNode>> myQueue){
		Queue<BTNode<dataNode>> tree = new Queue<BTNode<dataNode>>(); 
		
		//System.out.println();
		while (myQueue.size() >= 1)
		{
			if (myQueue.size() == 1)
			{
				
				BTNode<dataNode> curr = myQueue.dequeue();
				tree.enqueue(curr);
			}
			else
			{
				dataNode current = new dataNode(); 
				
				
				BTNode<dataNode> left = myQueue.dequeue();
				BTNode<dataNode> right =myQueue.dequeue();
			
			
				current.max = rightMin(right).getData().value;
				current.min = leftMax(left).getData().value;
				
				current.value = (current.max + current.min) / 2;
				current.count = 0;
				
				
				BTNode<dataNode> root = new BTNode<dataNode>(current, left, right, null);
				
				tree.enqueue(root);
			}
			
			
		}
		return tree;
	}
	
	
	public static int search(BTNode<dataNode> root,double target) {	
		if (target == root.getData().value && root.getData().count != 0)
		{
			System.out.println(root + " " + target + " " + root.getData().count);
			return (int)root.getData().count;
		}
		else {
			System.out.println(root + " " + target + " " + root.getData().count);
			if(target < root.getData().value)
			{
				return search(root.getLeft(), target);
				
			}
			
			else if(target > root.getData().value)
			{
				return search(root.getRight(), target);
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		//tester class with sample data
		// The expected output is:
//		(13.0,1) (12.5,1) (12.3,1) (12.1,1) (11.9,1) (10.3,2) (10.0,1) (9.2,1) (9.1,1) (8.0,1) (7.2,3) (5.8,2) (2.3,1) (1.0,1) 
//		(10.15,0)  7.2   0
//		(7.6,0)  7.2   0
//		(4.05,0)  7.2   0
//		(6.5,0)  7.2   0
//		(7.2,3)  7.2   3
//		3
		double[] a = {1,2.3,5.8,5.8,7.2,7.2,7.2,8,9.1,9.2,10,10.3,10.3,11.9,12.1,12.3,12.5,13};
		Queue<BTNode<dataNode>> myQueue=makeQueue(a);
		myQueue.traverse();
		System.out.println();
		while (myQueue.size()>1) {
			myQueue=join(myQueue);
		}
		BTNode<dataNode> root = myQueue.dequeue();
		System.out.println(search(root,7.2));
	}
}



