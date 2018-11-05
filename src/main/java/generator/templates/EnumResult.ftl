public enum XrSysDictionaryEnum {
<#list tablesList as table>
    ${table.codeNo}("${table.codeNo}", "${table.codeName}"),
</#list>

	private String code;
	private String name;

	private XrSysDictionaryEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public static String getNameByCode(String code) {
		for (ProductEnum pEnum : ProductEnum.values()) {
			if (pEnum.getCode().equals(code)) {
				return pEnum.getName();
			}
		}
		return "";
	}

}