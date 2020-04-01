package com.spring.board.dto;

public class CommonDto {

	int limit; // 가져올 row의 수
	int offset; // 몇번쨰 row부터 가져올 지
	String pagination;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

}
