package generator.bean;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

//封装类相关的信息
@Getter
public class ClassInfo implements Serializable {
    /**
     *   类中属性的名称
     */
    private List<TableInfo> tablesList ;

    public ClassInfo(List<TableInfo> tablesList) {
        this.tablesList = tablesList;
    }

    public List<TableInfo> getTablesList() {
        return tablesList;
    }

    public void setTablesList(List<TableInfo> tablesList) {
        this.tablesList = tablesList;
    }
    //',"xhh_ssfk_std" :{"table1":"table1Json+"'
    public String getResultStr() {
        StringBuilder str = new StringBuilder(150);
        str.append("'\"xhh_ssfk_std\" :{\"");
        for (TableInfo tableInfo : tablesList) {
            str.append(tableInfo.getTableName());
            //tableName
            str.append("\":',");
            //tablejaon
            str.append("nvl(");
            str.append("derived_scoring_");
            str.append(tableInfo.getTableName());
            str.append(".jsonResult");
            str.append(",'\"\"')");
            str.append(",',\"");



        }
        String substring = str.substring(0, str.length() - 2);
        return substring+"'";
    }
}
