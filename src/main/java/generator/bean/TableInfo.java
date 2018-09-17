package generator.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//封装类相关的信息
public class TableInfo implements Serializable {
    /**表名*/
    private String tableName;
    /**流名*/
    private String streamName;
    /**拼接sql*/
    private String resultInfo;
    /**属性名称集合*/
    private List<String> props = new ArrayList<>(19);

    public TableInfo() {
    }

    public TableInfo(String tableName, String streamName) {
        this.tableName = tableName;
        this.streamName = streamName;
    }

    public TableInfo(String tableName, String streamName, List<String> props) {
        this.tableName = tableName;
        this.streamName = streamName;
        this.props = props;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getResultInfo() {
        StringBuilder sb = new StringBuilder(50);
        this.props.remove("user_id");
        this.props.remove("order_id");
        this.props.remove("channel_id");
        this.props.remove("batch_id");
        for (String s : this.props) {
            sb.append(s);
            sb.append("\": \"',");
            sb.append("IF(");
            sb.append(s);
            sb.append(" is null ,'',");
            sb.append(s);
            sb.append(")");
            sb.append(",'\",','\"");
        }
        return sb.substring(0, sb.length() - 7);
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public List<String> getProps() {
        return props;
    }

    public void setProps(List<String> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"tableName\":\"")
                .append(tableName).append('\"');
        sb.append(",\"streamName\":\"")
                .append(streamName).append('\"');
        sb.append(",\"resultInfo\":\"")
                .append(resultInfo).append('\"');
        sb.append(",\"props\":")
                .append(props);
        sb.append('}');
        return sb.toString();
    }
}
