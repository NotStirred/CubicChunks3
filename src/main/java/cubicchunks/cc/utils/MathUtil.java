package cubicchunks.cc.utils;

public class MathUtil {
    public static int ceilDiv(int a, int b) {
        return -Math.floorDiv(-a, b);
    }
}