public class BST<T extends Comparable> 
{
	private BSTNode<T> root;

	public boolean find(T key){
		return find(root, key);
	}
	//protected so it can only be accessed within function package
	protected boolean find(BSTNode<T> node, T key){
		//base case, if not found
		if (node == null){
			return false;
		}
		
		if (key.compareTo(node.data) == 0) {
			return true;
		}
		else if (key.compareTo(node.data) < 0){
			return find(node.left, key);
		}
		else{
			return find(node.right, key);
		}
	}

	public void insert(T key){
		root = insert(root, key);
	}

	protected BSTNode insert(BSTNode<T> node, T key){
		//base case if not found
		if (node == null){
			return new BSTNode<T>(key);
		}
		
		if (key.compareTo(node.data) < 0){
			node.left = insert(node.left, key);
		}
		else{
			node.right = insert(node.right, key);
		}
		return node;
	}

	public void print(){
		print(root);
	}

	protected void print(BSTNode<T> node){
		if (node != null){
			print(node.left);
			System.out.println(node.data);
			print(node.right);
		}
	}

	public void delete(T key){
		root = delete(root, key);
	}

	protected BSTNode delete(BSTNode<T> node, T key)
	{
		if (node == null)
			return null;
		if (node.data.compareTo(key) < 0){
			node.right = delete(node.right, key);
			return node;
		}
		else if (node.data.compareTo(key) > 0){
			node.left = delete(node.left, key);
			return node;
		}
		else{
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			else{
				if (node.right.left == null){
					node.data = node.right.data;
					node.right = node.right.right;
				}
				else
					node.data = removeSmallest(node.right);
				return node;
			}
		}
	}

	protected T removeSmallest(BSTNode<T> node)
	{
		if (node.left.left == null){
			T smallest = node.left.data;
			node.left = node.left.right;
			return smallest;
		}
		else
			return removeSmallest(node.left);
	}
}