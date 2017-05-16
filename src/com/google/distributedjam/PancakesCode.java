package com.google.distributedjam;

public class PancakesCode {
    static int P = message.NumberOfNodes();

    public static void main(String[] args) {
        int N = (int) pancakes.GetStackSize();
        int D = (int) pancakes.GetNumDiners();
        int myid = message.MyNodeId();
        int[] local = new int[(N + P - 1) / P];
        for (int i = 0; i < local.length; i++) {
            int id = local.length * myid + i;
            if (id >= N)
                local[i] = D - 1;
            else
                local[i] = (int) pancakes.GetStackItem(id);
        }

        int localAns = 0;
        for (int i = 0; i < local.length - 1; i++)
            if (local[i] > local[i + 1])
                localAns++;

        if (myid > 0) {
            message.PutInt(myid - 1, local[0]);
            message.Send(myid - 1);
        }

        if (myid < P - 1) {
            message.Receive(myid + 1);
            if (local[local.length - 1] > message.GetInt(myid + 1))
                localAns++;
        }

        if (myid != 0)
            for (int i = 1; i < P; i++) {
                message.PutInt(0, localAns);
                message.Send(0);
            }
        else
            for (int i = 1; i < P; i++) {
                message.Receive(i);
                localAns += message.GetInt(i);
            }

        if (myid == 0)
            System.out.println(localAns + 1);
    }
}
