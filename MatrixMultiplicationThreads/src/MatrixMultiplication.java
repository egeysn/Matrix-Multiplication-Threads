import java.util.Scanner;

class MatrixProduct extends Thread {
    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int row,column;
    private int dim;
    long startTime;
    long endTime;



    public MatrixProduct(int[][] A,int[][] B,int[][] C,int row, int column,int dim_com)
    {
        this.A=A;
        this.B=B;
        this.C=C;
        this.row=row;
        this.column=column;
        this.dim=dim_com;
    }

    public void run()
    {
        for(int i=0;i<dim;i++){
            this.startTime = System.nanoTime();
            C[row][column]+=A[row][i]*B[i][column];
            this.endTime = System.nanoTime();
        }
        System.out.println("Thread "+row+","+column+" tamamlandi. TIME :" +(endTime - startTime)+"ms" );
    }
}

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner In = new Scanner(System.in);

        System.out.print("A matrisi satir sayisini giriniz: ");
        int rA = In.nextInt();
        System.out.print("A matrisi sütun sayisini giriniz: ");
        int cA = In.nextInt();
        System.out.print("B matrisi satir sayisini giriniz: ");
        int rB = In.nextInt();
        System.out.print("B matrisi sütun sayisini giriniz:  ");
        int cB = In.nextInt();
        System.out.println();

        if (cA != rB) {
            System.out.println("Satir ve sütun sayisini hatali girdiniz lütfen tekrar deneyiniz.!!");
            System.exit(-1);
        }

        int[][] A = new int[rA][cA];
        int[][] B = new int[rB][cB];
        int[][] C = new int[rA][cB];

        MatrixProduct[][] thread = new MatrixProduct[rA][cB];

        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cA; j++)
                A[i][j] = (int) (Math.random() * 10);


        }

        for (int a = 0; a < rB; a++) {
            for (int b = 0; b < cB; b++)
                B[a][b] = (int) (Math.random() * 10);
        }

        System.out.println("*********************************-----------------------------------------------*************************");
        System.out.println("A Matrisi :");
        displayMatrix(A, rA, cA);
        System.out.println("B Matrisi :");
        displayMatrix(B, rB, cB);


        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cB; j++) {
                thread[i][j] = new MatrixProduct(A, B, C, i, j, cA);
                thread[i][j].start();
            }
        }

        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cB; j++) {
                try {
                    thread[i][j].join();
                } catch (InterruptedException e) {
                }
            }
        }

        System.out.println();
        System.out.println("A x B = C matrisi: \n");
        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cB; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static void displayMatrix(int[][] matrix, int row, int column) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }


}