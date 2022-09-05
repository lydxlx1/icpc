package google.distributedjam;

public class WeirdEditorSource {
    static int P = message.NumberOfNodes();
    static int myid = message.MyNodeId();


    static int N = (int) weird_editor.GetNumberLength();
    static long MOD = 1000000007;

    static long pow(long a, long n) {
        long res = 1;
        while (n > 0) {
            if (n % 2 == 1)
                res = res * a % MOD;
            a = a * a % MOD;
            n /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        if (N <= P) {
            if (myid == 0) {
                int[] local = new int[N];
                for (int i = 0; i < N; i++)
                    local[i] = (int) weird_editor.GetDigit(i);

                int[] stack = new int[local.length];
                int top = 0;
                for (int num : local) {
                    while (top > 0 && num > stack[top - 1])
                        top--;
                    stack[top++] = num;
                }

                long res = 0;
                for (int i = 0; i < top; i++)
                    res = (res * 10 + stack[i]) % MOD;
                res = res * pow(10, N - top) % MOD;
                System.out.println(res);
            }
        } else {
            int[] local = new int[(N + P - 1) / P];
            for (int i = 0; i < local.length; i++) {
                int id = local.length * myid + i;
                if (id >= N)
                    local[i] = -1;
                else
                    local[i] = (int) weird_editor.GetDigit(id);
            }

            int[] stack = new int[local.length];
            int top = 0;
            for (int num : local)
                if (num != -1) {
                    while (top > 0 && num > stack[top - 1])
                        top--;
                    stack[top++] = num;
                }

//            System.out.println(Arrays.toString(stack) + " " + top + " ....");

            int largestDigit = -1;
            if (myid + 1 < P) {
                message.Receive(myid + 1);
                largestDigit = Math.max(largestDigit, message.GetInt(myid + 1));
            }

            while (top > 0 && largestDigit > stack[top - 1])
                top--;

            if (myid > 0) {
                if (top > 0) {
                    largestDigit = Math.max(largestDigit, stack[0]);
                }
                message.PutInt(myid - 1, largestDigit);
                message.Send(myid - 1);
            }
//            System.out.println("largest = " + largestDigit);
//            System.out.println(Arrays.toString(stack) + " " + top);


            long localSum = 0;
            long prefixLength = top;
            for (int i = 0; i < top; i++)
                localSum = (localSum * 10 + stack[i]) % MOD;
//            System.out.println("localSum = " + localSum);
            if (myid > 0) {
                message.Receive(myid - 1);
                long previousSum = message.GetLL(myid - 1);
                long previousLength = message.GetLL(myid - 1);

                localSum = (previousSum * pow(10, top) % MOD + localSum) % MOD;
                prefixLength += previousLength;
            }
            if (myid + 1 < P) {
                message.PutLL(myid + 1, localSum);
                message.PutLL(myid + 1, prefixLength);
                message.Send(myid + 1);
            }

            if (myid == P - 1) {
//                System.out.println("length = " + prefixLength);
                localSum = (localSum * pow(10, N - prefixLength)) % MOD;
                System.out.println(localSum);
            }
        }
    }
}
