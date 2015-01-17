package zhenghui.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * User: zhenghui
 * Date: 15-1-16
 * Time: 下午2:07
 * 序列化方式就用最简单的java序列化。
 */
public class SerializeUtil {

    /**
     * 序列化
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception ignored) {

        }
        return null;
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost");
        Map<String,String> map = new HashMap<String, String>(){
            {
                put("key1","value1");
                put("key2","value2");
                put("key3","value3");
            }

            private static final long serialVersionUID = -5278020639813324497L;
        };
        byte[] bytes = SerializeUtil.serialize(map);
        System.out.println(bytes.length);
    }
}
