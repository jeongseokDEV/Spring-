package com.spring.board.dto;

import java.util.Date;

public class BoardFileDto extends CommonDto {

	int board_seq; // 게시글 번호
	int file_no; // 파일 번호
	String file_name_key; // 파일 암호화 명
	String file_name; // 파일 이름
	String file_path; // 파일 경로
	String file_size; // 파일 크기
	String remark; //  비고
	String del_yn; // 삭제 유무
	String ins_user_id; // insert 사용자 아이디
	Date ins_date; // insert 날짜
	String upd_user_id; // update 사용자 아이디
	Date upd_date;// update 날짜

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public String getFile_name_key() {
		return file_name_key;
	}

	public void setFile_name_key(String file_name_key) {
		this.file_name_key = file_name_key;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getIns_user_id() {
		return ins_user_id;
	}

	public void setIns_user_id(String ins_user_id) {
		this.ins_user_id = ins_user_id;
	}

	public Date getIns_date() {
		return ins_date;
	}

	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}

	public String getUpd_user_id() {
		return upd_user_id;
	}

	public void setUpd_user_id(String upd_user_id) {
		this.upd_user_id = upd_user_id;
	}

	public Date getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
}
