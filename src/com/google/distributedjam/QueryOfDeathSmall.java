package com.google.distributedjam;

public class QueryOfDeathSmall {
    static int P = message.NumberOfNodes();
    static int myid = message.MyNodeId();
    static int N = (int) query_of_death.GetLength();

    public static void main(String[] args) {
        int index = -1;
        if (myid == 0) {
            for (int i = 0; i < N; i++) {
                int value = (int) query_of_death.GetValue(i);
                for (int j = 0; j < 100; j++) {
                    if (value != query_of_death.GetValue(i)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1)
                    break;
            }

            message.PutInt(1, index);
            message.Send(1);
        }

        if (myid == 1) {
            message.Receive(0);
            index = message.GetInt(0);

            int ans = 0;
            for (int i = 0; i < N; i++)
                if (i != index)
                    ans += query_of_death.GetValue(i);
            ans += query_of_death.GetValue(index);

            System.out.println(ans);
        }
    }
}
