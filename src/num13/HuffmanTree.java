package num13;

public class HuffmanTree {
	//HuffmanNode root;
	HuffmanNode[] HN;
	public int[][] huffmanCoding(int[] W) {
		int n = W.length;
		int m = 2 * n - 1;
	 HN = new HuffmanNode[m];
		int i;
		for (i = 0; i < n; i++)
			HN[i] = new HuffmanNode(W[i]);

		for (i = n; i < m; i++) {
		
			HuffmanNode min1 = selectMin(HN, i - 1);
			min1.flag=1;
			HuffmanNode min2 = selectMin(HN, i - 1);
			min2.flag=1;

		
			HN[i] = new HuffmanNode();
			min1.parent=HN[i];
			min2.parent=HN[i];
			HN[i].lchild=min1;
			HN[i].rchild=min2;
			HN[i].weight=min1.weight + min2.weight;
		}
	//	root=HN[HN.length-1];

		int[][] HuffCode = new int[n][n];
		for (int j = 0; j < n; j++) {
			int start = n - 1;
			for (HuffmanNode c = HN[j], p = c.parent; p != null; c = p, p = p.parent) {
			
				if (p.lchild.equals(c)) {
					HuffCode[j][start--] = 0;
				}else {
				
					HuffCode[j][start--] = 1;
				}
			}
			HuffCode[j][start] = -1;
		}
		return HuffCode;
	}

	
	public HuffmanNode selectMin(HuffmanNode[] HN, int end) {
		HuffmanNode min = HN[end];
		for (int i = 0; i <= end; i++) {
			HuffmanNode h = HN[i];
			if (h.flag == 0 && h.weight < min.weight)
				min=h;
		}
		return min;
	}
	public int decode(String s,HuffmanNode p) {
	//	HuffmanNode p=root;
		while(!s.isEmpty()) {
			if(s.charAt(0)=='0') {
				p=p.lchild;
				s=s.substring(1);
			}else {
				p=p.rchild;
				s=s.substring(1);
			}
		}
		return p.weight;
	}
	public HuffmanNode[] get() {
		return HN;
	}

}
