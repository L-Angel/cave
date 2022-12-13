package fun.langel.cql.cave;

import fun.langel.cql.annotation.Cave;
import fun.langel.cql.annotation.Param;
import fun.langel.cql.annotation.Select;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/9 21:24
 **/
@Cave
public interface CaveDemo {

    @Select(sql = "select field1,field2 from index1 where f1 = #{param1}", direct = "defaultEs")
    String query(@Param(name = "param1") String param1);


    @Select(sql = "select field1,field2 from demo_index where field1 = #{param1}",
            direct = "defaultEs"
            , returnType = Model.class)
    List<Model> queryList(@Param(name = "param1") String param1);


    @Select(sql = "select fielda from demo_index where c_exists('fieldb') and c_script(`doc['safe@limit'].value>doc['available'].value`)")
    List<Model> queryListWithDefaultEs(@Param(name = "param1") String param1);
}
