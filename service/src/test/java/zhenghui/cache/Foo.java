package zhenghui.cache;

import java.io.Serializable;

/**
 * User: zhenghui
 * Date: 15-1-17
 * Time: ����8:06
 * ����ʵ����
 */
public class Foo implements Serializable{
    private static final long serialVersionUID = 1964371970390599541L;

    private String name;

    public String getName() {
        return name;
    }

    public Foo setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                '}';
    }
}
