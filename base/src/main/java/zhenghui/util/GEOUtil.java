package zhenghui.util;

/**
 * User: zhenghui
 * Date: 13/12/14
 * Time: 16:14
 * 位置信息编码方法
 * @see http://chenjingbo.iteye.com/blog/2002499
 */
public class GEOUtil {

    // 编码经纬度
    public static long encode(long lon, long lat) {
        long mergeBits = 0L;
        mergeBits = processBitset(mergeBits, 59, lon, -180000000, 180000000);
        mergeBits = processBitset(mergeBits, 58, lat, -90000000, 90000000);
        return mergeBits;
    }

    // 纬度 下限 上限
    private static long processBitset(long mergeBits, int start, long lat, long floor, long ceiling) {
        double dfloor = floor, dceiling = ceiling;
        for (int i = start; i >= 0; i -= 2) {
            double mid = (dfloor + dceiling) / 2;
            if (lat >= mid) {      // 上半阙
                mergeBits |= (1L << i);     // 该为置1
                dfloor = mid;       // 下限抬高
            } else {               // 下半阙
                mergeBits &= ~(1L << i);    // bit位设为0
                dceiling = mid;     // 上限降低
            }
        }
        return mergeBits;
    }
}
