
import java.util.Scanner;

public class LinkedList {
	static class ColumnHead{
		int column,rows;
		double values;
		ColumnHead columnDown,columnRight;
	
	public ColumnHead(int row,int col) {this(row,col,0,null,null);}
	public ColumnHead(int row,int col,int val) {this(row,col,val,null,null);}
	public ColumnHead(int row,int col,double val,ColumnHead down,ColumnHead right) {
		rows=row;
		column=col;
		values=val;
		columnDown=down;
		columnRight=right;
	}
	public void PrintVector(ColumnHead v1) {
		ColumnHead startVector=v1;
		for(ColumnHead p=v1;p!=null;p=p.columnDown) {
			System.out.println("Row="+p.rows+" Column="+p.column+" Value="+p.values);
		}
	}
	ColumnHead start=null;
	public void makeLinkedList(VectorHead v1) {
		VectorHead starting=v1;
		while(starting.next!=null) {
			int rowLoc=starting.VecRows;
			int colLoc=starting.VecColumn;
			double spval=starting.values;
			System.out.println(rowLoc+" "+colLoc+" "+spval);
			v1=v1.next;
			starting=starting.next;
			ColumnHead newNode=start;
			ColumnHead spNode=new ColumnHead(rowLoc,colLoc,spval,null,null);
			for(int c=-1;c<=colLoc;c++) {
				//System.out.println(c);
				if(newNode.column==colLoc) {
					//System.out.println("True"+" "+c+" "+colLoc+" "+spval+"--------");
					while(newNode.columnDown!=null) {newNode=newNode.columnDown;}
					newNode.columnDown=spNode;
				}
				else {
					//System.out.println("IT IN HERE");
					newNode=newNode.columnRight;}
			}
			newNode=start;
			for(int r=-1;r<=rowLoc;r++) {
				if(newNode.rows==rowLoc) {
					while(newNode.columnRight!=null) {newNode=newNode.columnRight;}
					newNode.columnRight=spNode;
				}
				else {newNode=newNode.columnDown;}
			}
			
		}
		//System.out.println(start.rows+" "+start.column+" "+start.values+" "+start.columnRight+" "+start.columnDown);
		//System.out.println(start.columnRight.rows+" "+start.columnRight.column+" "+start.columnRight.values+" "+start.columnRight.columnRight+" "+start.columnRight.columnDown);
		//System.out.println(start.columnRight.columnDown.rows+" "+start.columnRight.columnDown.column+" "+start.columnRight.columnDown.values+" "+start.columnRight.columnDown.columnRight+" "+start.columnRight.columnDown.columnDown+" "+start.columnRight.columnDown.columnDown.columnDown+" "+start.columnRight.columnDown.columnDown.columnDown.columnDown);
		//System.out.println(start.columnDown.rows+" "+start.columnDown.column+" "+start.columnDown.values+" "+start.columnDown.columnRight+" "+start.columnDown.columnRight.columnDown+" "+start.columnDown.columnRight.columnDown.columnDown+" "+start.columnDown.columnRight.columnDown.columnDown.columnDown);	
	}
	public void vectorTranspose(ColumnHead c1){
		int temp;
		for(ColumnHead p=c1;p!=null;p=p.columnDown) {
				temp=p.rows;
				p.rows=p.column;
				p.column=temp;
		}
	}
	
	
	public void printAll(ColumnHead c1) {
		//System.out.print("\nList(");
		for(ColumnHead p=c1;p!=null;p=p.columnRight) {
			System.out.print("("+p.column +")"+",");
		}
		System.out.println("");
		for(ColumnHead p=c1.columnDown;p!=null;p=p.columnDown) {
			System.out.print("List("+"("+p.rows+",)"+" ");
			for(ColumnHead q=p.columnRight;q!=null;q=q.columnRight) {
				System.out.print(q.values+"("+q.rows+","+q.column+")"+q+" ");
			}
			System.out.println();
		}
		
	} 
	
	};
	static class VectorHead{
		int VecRows,VecColumn;
		double values;
		VectorHead next;
	public VectorHead(int verow,int vecol,double veval) {this(verow,vecol,veval,null);	}
	public VectorHead(int verow,int vecol,double veval,VectorHead nex) {
		VecRows=verow;
		VecColumn=vecol;
		values=veval;
		next=nex;
	}
	};
	VectorHead vectorZero=null;
	VectorHead vectorLast=null;
	int rowSize,colSize;
	
	ColumnHead start=null;
	ColumnHead main=null;
	ColumnHead last=null;
	ColumnHead forRow=null;
	int sizeRight,sizeDown;
	int counter;
	
	public LinkedList(double[][] SparseMatrix,int colsize,int rowsize) {
		rowSize=rowsize;
		colSize=colsize;
		MakeVector(SparseMatrix,rowsize,colsize);
		PrintVector();
		for(int i=-1;i<=colsize;i++) {
			Append(i);
		}
		forRow=start;
		for(int j=0;j<=rowsize;j++) {
			AppendDown(j);
		}
		makeLinkedList();
	}
	
	public void Append(int e) {
		if(isEmpty()) {
			start=new ColumnHead(-1,e);
			last=start;
		}
		else {
			last.columnRight=new ColumnHead(0,e);
			last=last.columnRight;
		}
		sizeRight++;
	}
	public void dotProduct(double dot) {
		if(VectorisEmpty()) {System.out.print("The vector is empty");}
		else {
			for(VectorHead p=vectorZero;p!=null;p=p.next) {
				p.values=p.values*dot;
			}
		}
	}
	public void AppendDown(int f) {
		forRow.columnDown=new ColumnHead(f,0);
		forRow=forRow.columnDown;
		sizeDown++;
	} 
	
	public boolean isEmpty() {return(start==null);}	
	public boolean VectorisEmpty() {return(vectorZero==null);}
	
	public void print() {
		System.out.print("\nList(");
		for(ColumnHead p=start;p!=null;p=p.columnRight) {
			System.out.print("("+p.rows+","+p.column +")"+",");
		}
		System.out.print(") : length:"+sizeRight);
	}
	
	public void printDown() {
		System.out.println("");
		for(ColumnHead p=start.columnDown;p!=null;p=p.columnDown) {
			System.out.println("List("+"("+p.rows+","+p.column +")"+"");
			}
		System.out.print(" : length:"+ ++sizeDown);
	}
	
	public void makeLinkedList() {
		VectorHead starting=vectorZero;
		for(int i=0;i<counter;i++) {
			int rowLoc=starting.VecRows;
			int colLoc=starting.VecColumn;
			double spval=starting.values;
			System.out.println(rowLoc+" "+colLoc+" "+spval);
			
			starting=starting.next;
			ColumnHead newNode=start;
			ColumnHead spNode=new ColumnHead(rowLoc,colLoc,spval,null,null);
			for(int c=-1;c<=colLoc;c++) {
				//System.out.println(c);
				if(newNode.column==colLoc) {
					//System.out.println("True"+" "+c+" "+colLoc+" "+spval+"--------");
					while(newNode.columnDown!=null) {newNode=newNode.columnDown;}
					newNode.columnDown=spNode;
				}
				else {
					//System.out.println("IT IN HERE");
					newNode=newNode.columnRight;}
			}
			newNode=start;
			for(int r=-1;r<=rowLoc;r++) {
				if(newNode.rows==rowLoc) {
					while(newNode.columnRight!=null) {newNode=newNode.columnRight;}
					newNode.columnRight=spNode;
				}
				else {newNode=newNode.columnDown;}
			}
			
		}
		//Testing 
		//System.out.println(start.rows+" "+start.column+" "+start.values+" "+start.columnRight+" "+start.columnDown);
		//System.out.println(start.columnRight.rows+" "+start.columnRight.column+" "+start.columnRight.values+" "+start.columnRight.columnRight+" "+start.columnRight.columnDown);
		//System.out.println(start.columnRight.columnDown.rows+" "+start.columnRight.columnDown.column+" "+start.columnRight.columnDown.values+" "+start.columnRight.columnDown.columnRight+" "+start.columnRight.columnDown.columnDown+" "+start.columnRight.columnDown.columnDown.columnDown+" "+start.columnRight.columnDown.columnDown.columnDown.columnDown);
		//System.out.println(start.columnDown.rows+" "+start.columnDown.column+" "+start.columnDown.values+" "+start.columnDown.columnRight+" "+start.columnDown.columnRight.columnDown+" "+start.columnDown.columnRight.columnDown.columnDown+" "+start.columnDown.columnRight.columnDown.columnDown.columnDown);
		printAll();
	}
	public void MatrixTranspose() {
		vectorTranspose();
		makeLinkedList();
		printAll();
	}
	public void multiplyVector(VectorHead v1,ColumnHead m1) {
		VectorHead multiVector;
		VectorHead runVector;
		if(VectorisEmpty()) {System.out.print("The vector is empty");}
		int checkCol=0,checkRow=0;
		runVector=v1;
		while(runVector.next!=null) {
			if(runVector.VecColumn>checkCol) {checkCol=runVector.VecColumn;}
			if(runVector.VecRows>checkRow) {checkRow=runVector.VecRows;}
			runVector=runVector.next;
		}
		if(checkCol>sizeRight && checkRow>sizeDown) {
			System.out.print("Incompatible vector it");
		}
		else {
			runVector=v1;
			//runVector.vectorTranspose();
			//while()
		}
		
	}
	
	public void printAll() {
		//System.out.print("\nList(");
		for(ColumnHead p=start;p!=null;p=p.columnRight) {
			System.out.print("("+p.column +")"+",");
		}
		System.out.println("");
		for(ColumnHead p=start.columnDown;p!=null;p=p.columnDown) {
			System.out.print("List("+"("+p.rows+",)"+" ");
			for(ColumnHead q=p.columnRight;q!=null;q=q.columnRight) {
				System.out.print(q.values+"("+q.rows+","+q.column+")"+q+" ");
			}
			System.out.println();
		}
		
	}
	public void vectorTranspose(){
		int temp;
		if(VectorisEmpty()) {System.out.print("The vector is empty");}
		else {
			for(VectorHead p=vectorZero;p!=null;p=p.next) {
				temp=p.VecRows;
				p.VecRows=p.VecColumn;
				p.VecColumn=temp;
			}
		}
	}
	
	public void MakeVector(double[][] SparseMatrix,int rowSize,int colSize) {
		counter=0;
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				if(SparseMatrix[i][j]!=0) {
					if(VectorisEmpty()) {
						//System.out.println("TEST PRINT"+(int)i+" "+(int)j+" "+SparseMatrix[i][j]);
						vectorZero=new VectorHead((int)i,(int)j,SparseMatrix[i][j]);
						vectorLast=vectorZero;
					}
					else {
						//System.out.println("TEST PRINT"+(int)i+" "+(int)j+" "+SparseMatrix[i][j]);
						vectorLast.next=new VectorHead((int)i,(int)j,SparseMatrix[i][j]);
						vectorLast=vectorLast.next;
					}
					
					//System.out.print("row "+i+" column "+j+" val "+SparseMatrix[i][j]);
					++counter;
				}
				//System.out.println("");
			}
		}
	}
	public void PrintVector() {
		VectorHead startVector=vectorZero;
		for(VectorHead p=vectorZero;p!=null;p=p.next) {
			System.out.println("Row="+p.VecRows+" Column="+p.VecColumn+" Value="+p.values);
		}
	}
	//public ColumnHead MatrixMultiply() {	}
	public ColumnHead sliceRow(int rowNum) {
		//ColumnHead rows=null;
		ColumnHead p=start;
		for(;p.rows!=rowNum;p=p.columnDown) {	}
		ColumnHead rows=p.columnRight;
		return(rows);
	}
	public ColumnHead sliceCol(int ColNum) {
		//ColumnHead rows=null;
		ColumnHead p=start;
		for(;p.column!=ColNum;p=p.columnRight) {	}
		ColumnHead cols=p.columnDown;
		return(cols);
	}
	public static void main(String args[]) {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter column size of array");
		int colSize= s.nextInt();
		System.out.println("Enter row height of array");
		int rowSize= s.nextInt();
		//Input matrix
		double[][] sparseMatrix=new double[rowSize][colSize];
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				sparseMatrix[i][j]=s.nextDouble();
			}
		}
		s.close();
		//Printing the matrix
		for(int i=0;i<rowSize;i++) {
			System.out.print("[");
			for(int j=0;j<colSize;j++) {
				System.out.print(sparseMatrix[i][j]+", ");
			}
			System.out.println("];");
		}
		
		//Check for non zero values
		int count=0;
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				if(sparseMatrix[i][j]!=0) {
					++count;
				}
			}
		}
		System.out.println("The number of sparse elements"+count);

		LinkedList a1=new LinkedList(sparseMatrix,colSize,rowSize);
		a1.print();
		//a1.printDown();
		//a1.makeLinkedList();
		a1.printAll();
		System.out.println("ColumnSilce");
		a1.sliceCol(1).PrintVector(a1.sliceCol(1));
		//a1.printAll();
		System.out.println("RowSilce");
		a1.sliceRow(1).PrintVector(a1.sliceRow(0));
		System.out.println("Dot Product of 2");
		a1.dotProduct(2);
		a1.PrintVector();
		//System.out.println("Multiply 1,0 with 2");
		//a1.multiplyVector(1, 0, 2);
		//a1.PrintVector();
		System.out.println("Transpose");
		a1.vectorTranspose();
		a1.PrintVector();
		//a1.MatrixTranspose();
		//(a1.silceCol(1)).printVector();
		System.out.println();
		
				
		//Multiplying vector
}

}
