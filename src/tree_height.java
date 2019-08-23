import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		int depth[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			depth= new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}
		/*void readFromFile() throws IOException{
			BufferedReader br = new BufferedReader(new FileReader("24"));
			try {
				
				n = in.nextInt();
				parent = new int[n];
				for (int i = 0; i < n; i++) {
					parent[i] = in.nextInt();
				}
			} finally {
			    br.close();
			}
		}*/

		int computeHeight() {
                        // Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}
		
		int computeHeightFast(){
			System.out.println("Starting Search");
			int maxHeight=1;
			int rootVertex=0;
			
			for (int vertex=0;vertex<n;vertex++){
				if(parent[vertex]==-1){
					rootVertex=vertex;
					break;
				}
			}
			
			for (int vertex=0;vertex<n;vertex++){
				if(parent[vertex]==rootVertex){
					int height = recurseHeight(vertex,1);
					if (height > maxHeight){
						maxHeight=height;
					}
				}
			}
			
			return maxHeight;
		}
		int computeHeightFast2(){
			
			 for (int i = 0; i < n; i++) {
		            depth[i] = 0;
		       }
			 
			 for (int i=0; i<n; i++){
				 recurseHeight(i);
			 }
			 
			 int maxDepth=0;
			 for (int i=0; i<n; i++){
				 if(depth[i]>maxDepth){
					 maxDepth=depth[i];
				 }
			 }
			
			
			return maxDepth;
		}
		
		int recurseHeight(int parent, int levelHeight){
			levelHeight++;
			int maxHeight=levelHeight;
			
			for (int vertex=0; vertex<n; vertex++){
				if(this.parent[vertex]==parent){
					int height = recurseHeight(vertex,levelHeight);
					if (height > maxHeight){
						maxHeight=height;
					}
				}
			}
			return maxHeight;
		}
		void recurseHeight(int i){
	        if (depth[i] != 0) {
	            return;
	        }
	
	        if (parent[i] == -1) {
	            depth[i] = 1;
	            return;
	        }
	 
	        if (depth[parent[i]] == 0) {
	            recurseHeight(parent[i]);
	        }
	 
	        depth[i] = depth[parent[i]] + 1;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		//System.out.println(tree.computeHeight());
		System.out.println(tree.computeHeightFast2());
	}
}
