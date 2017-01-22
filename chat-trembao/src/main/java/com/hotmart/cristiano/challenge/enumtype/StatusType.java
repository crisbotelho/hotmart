package com.hotmart.cristiano.challenge.enumtype;

public enum StatusType {

	ONLINE((short) 1, "Online"), OFFLINE((short) 0, "Offline");
	
	private Short codigo;
	private String descricao;
	
	private StatusType(Short codigo, String descricao) {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
