package template.string;

import java.util.ArrayDeque;

/**
 * http://adilet.org/blog/25-09-14/
 * Applications

    Let's take a look at some problems that can be solved using the palindromic tree.

    1. How many new palindromes are added?
    
    We have a problem that asks how many new palindrome substrings are added to the string if we append a letter to its end. 
    For example appending letter a to the string aba (which already has subpalindromes a, b and aba) adds new palindrome aa.
    We know that the number of new subpalindromes can be either 0 or 1. 
    The solution of this problem is straightforward — we need to build the palindromic tree letter by letter, 
    and for any new letter we can answer how many new palindromes were added just by inspecting the number of newly created nodes of the tree.

    
    2. The number of palindrome substrings.

    In this problem we have to answer how many palindrome substrings the given string has. 
    For example the string aba has four — two times a, one time b and one time aba.
    We will solve this problem along with the construction of the palindromic tree for the given string. 
    When we process a new letter we add to the answer all palindromes that contain this new letter. 
    But these palindromes are the new longest suffix-palindrome t (which contains new letter) 
    and all palindromes that can be reached from it by suffix links. 
    To quickly find the number of them let's store in each node the length of the suffix link chain from it (including this node), 
    then we need just to add this value for t to the answer as we process new letter. This value itself is calculated very simple: for roots it is zero, for other nodes it is just this value in the suffix link node (which was created and handled earlier) plus one.


    3. The number of occurrences of the palindromes.

    Another problem asks for the number of occurrences of each subpalindrome in the string, 
    and it also can be solved using the palindromic tree.
    Note that when we add a new letter, it increases the number of occurrences of the new longest suffix-palindrome t (which contains new letter) 
    and all palindromes that can be reached from it by suffix links.
    To quickly update these values we will increase some lazy flag in t node, 
    which means that we later increase the number of occurrences of it 
    and all nodes reachable from t node by suffix links. 
    After we are done with the construction of the palindromic tree, 
    we iterate through the list of nodes from the end to the beginning (from the lately created nodes to the early ones) 
    and propagate this lazy flag from the node to its suffix link node — update the number of occurrences of the current node by the value of the flag 
    and then add this value to the flag of the current node's suffix link.

    In the end, we get the palindromic tree with each node containing the number of its occurrences and the construction runtime remains the same O(n).
 * @author yuan
 *
 */
public class PalindromicTree {
    private final static int M = 128;
    private int ramIndex = 0;
    // 0 is the root of length -1
    // 1 is the root of length 0
    private int[][] child;
    private int[] parent;
    private char[] charFromParent;
    private int[] length;
    private int[] properPalindromicSuffix;
    private String string;

    public PalindromicTree(String string) {
        int MAX = string.length() + 10;
        child = new int[MAX][M];
        parent = new int[MAX];
        charFromParent = new char[MAX];
        length = new int[MAX];
        properPalindromicSuffix = new int[MAX];
        this.string = string;

        parent[ramIndex] = 0;
        length[ramIndex] = -1;
        properPalindromicSuffix[ramIndex++] = 0;

        parent[ramIndex] = 0;
        length[ramIndex] = 0;
        properPalindromicSuffix[ramIndex++] = 0;

        init();
    }

    private void init() {
        int t = 0;
        for (int i = 0; i < string.length(); i++) {
            // Add letters one by one
            char ch = string.charAt(i);
            int A = t;
            while (i - length[A] - 1 < 0 || string.charAt(i - length[A] - 1) != ch)
                A = properPalindromicSuffix[A];
            int newNode = child[A][ch];
            if (newNode == 0) {
                newNode = ramIndex++;
                child[A][ch] = newNode;
                parent[newNode] = A;
                charFromParent[newNode] = ch;
                length[newNode] = length[A] + 2;
                if (length[newNode] == 1) properPalindromicSuffix[newNode] = 1; // 1 means the node of length 0
                else {
                    for (int B = properPalindromicSuffix[A];; B = properPalindromicSuffix[B]) {
                        if (i - length[B] - 1 >= 0 && string.charAt(i - length[B] - 1) == ch) {
                            properPalindromicSuffix[newNode] = child[B][ch];
                            break;
                        }
                    }
                }
            }
            t = length[newNode] < i + 1 ? newNode : properPalindromicSuffix[newNode];
        }
    }

    public void print(int i, ArrayDeque<Character> deque) {
        if (length[i] == 1) deque.add(charFromParent[i]);
        else if (length[i] == 2) {
            deque.add(charFromParent[i]);
            deque.add(charFromParent[i]);
        } else {
            print(parent[i], deque);
            deque.addFirst(charFromParent[i]);
            deque.addLast(charFromParent[i]);
        }
    }
}