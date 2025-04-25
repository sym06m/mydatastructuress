public class BST<K extends Comparable<K>, V> {
   
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<K, V> root; 

   
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) return new Node<>(key, value); 
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value); 
        else if (cmp > 0) node.right = put(node.right, key, value); 
        else node.value = value; 
        return node;
    }

    
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
        if (node == null) return null; 
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key); 
        else if (cmp > 0) return get(node.right, key); 
        else return node.value; 
    }

    
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) return null; 
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key); 
        else if (cmp > 0) node.right = delete(node.right, key); 
        else {
           
            if (node.right == null) return node.left; 
            if (node.left == null) return node.right; 
            Node<K, V> temp = node;
            node = min(temp.right); 
            node.right = deleteMin(temp.right);
            node.left = temp.left; 
        }
        return node;
    }

   
    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) return node;
        return min(node.left);
    }

  
    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

   
    public Iterable<K> keys() {
        Queue<K> queue = new Queue<>();
        inOrder(root, queue);
        return queue;
    }

    private void inOrder(Node<K, V> node, Queue<K> queue) {
        if (node == null) return;
        inOrder(node.left, queue); 
        queue.enqueue(node.key); 
        inOrder(node.right, queue); 
    }
}
