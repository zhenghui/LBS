package zhenghui.util;

/**
 * User: zhenghui
 * Date: 13/12/14
 * Time: 16:14
 * λ����Ϣ���뷽��
 * @see http://chenjingbo.iteye.com/blog/2002499
 */
public class GEOUtil {

    // ���뾭γ��
    public static long encode(long lon, long lat) {
        long mergeBits = 0L;
        mergeBits = processBitset(mergeBits, 59, lon, -180000000, 180000000);
        mergeBits = processBitset(mergeBits, 58, lat, -90000000, 90000000);
        return mergeBits;
    }

    // γ�� ���� ����
    private static long processBitset(long mergeBits, int start, long lat, long floor, long ceiling) {
        double dfloor = floor, dceiling = ceiling;
        for (int i = start; i >= 0; i -= 2) {
            double mid = (dfloor + dceiling) / 2;
            if (lat >= mid) {      // �ϰ���
                mergeBits |= (1L << i);     // ��Ϊ��1
                dfloor = mid;       // ����̧��
            } else {               // �°���
                mergeBits &= ~(1L << i);    // bitλ��Ϊ0
                dceiling = mid;     // ���޽���
            }
        }
        return mergeBits;
    }
}
