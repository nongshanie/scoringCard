package mybatis.simple.model;

public class XrSysDictionary {
    private Integer id;

    private String codeKey;

    private String codeNo;

    private String codeName;

    private String status;

    private String sort;

    private Integer cid;

    private Integer ctime;

    private Integer mid;

    private Integer mtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey == null ? null : codeKey.trim();
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo == null ? null : codeNo.trim();
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getMtime() {
        return mtime;
    }

    public void setMtime(Integer mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"codeKey\":\"")
                .append(codeKey).append('\"');
        sb.append(",\"codeNo\":\"")
                .append(codeNo).append('\"');
        sb.append(",\"codeName\":\"")
                .append(codeName).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"sort\":\"")
                .append(sort).append('\"');
        sb.append(",\"cid\":")
                .append(cid);
        sb.append(",\"ctime\":")
                .append(ctime);
        sb.append(",\"mid\":")
                .append(mid);
        sb.append(",\"mtime\":")
                .append(mtime);
        sb.append('}');
        return sb.toString();
    }
}