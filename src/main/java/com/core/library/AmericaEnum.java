package com.core.library;

public enum AmericaEnum {
	Error_OperationFailed("����ʧ��!"),
	Error_NameOrPwError("�û������������!"),
	Success_OperationSuccess ("�����ɹ�!");

	private final String value;
    //���췽��������private����Ĭ��
    private AmericaEnum(String value) {
        this.value = value;
    }

    public String GetMessage(){
        return value;
    }
}
