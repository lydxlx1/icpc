package com.google.distributedjam;

// Sample input 1, in Java.
public class weird_editor {
    public weird_editor() {
    }

    public static long GetNumberLength() {
        return 4L;
    }

    public static long GetDigit(long i) {
        switch ((int)i) {
            case 0: return 3L;
            case 1: return 2L;
            case 2: return 1L;
            case 3: return 0L;
            default: throw new IllegalArgumentException("Invalid argument");
        }
    }
}