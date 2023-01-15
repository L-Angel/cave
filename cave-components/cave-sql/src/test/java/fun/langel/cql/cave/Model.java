package fun.langel.cql.cave;

import fun.langel.cql.annotation.CField;

import java.io.Serializable;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/9 21:24
 **/
public class Model implements Serializable {

    @CField(alias = "field1")
    private String field1;

    @CField(alias = "demo_field2")
    private String field2;

    @CField(alias = "field3")
    private String field3;

    @CField(alias = "field4")
    private int field4;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public int getField4() {
        return field4;
    }

    public void setField4(int field4) {
        this.field4 = field4;
    }
}
