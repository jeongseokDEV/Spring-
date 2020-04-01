package com.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.common.ResultUtil;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;
import com.spring.board.service.BoardService;
/* RequestMapping - ��û URL�� � �޼ҵ尡 ó���� �� ���θ� ����
 * AutoWired - �� ��Ȳ�� Ÿ�Կ� �´� IoC �����̳� �ȿ� �����ϴ� Bean�� �ڵ����� �������ְ� ��
 * MDC�� �����ϴ� ������  - �� ��û�� ���� �α��� ������ ���� ������ ������ �� �ֱ� ������ ������
 * 					   �� �ҽ��ڵ忡�� �α��� ������ ���� ������ ���� ������ board_seq���� �߰� �Ͽ��� 
 * */
@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	//�Խ��� ����Ʈ
	@RequestMapping(value = "/boardList")
	public String boardList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardList";
	}


	@RequestMapping(value = "/getBoardList")
	@ResponseBody
	public ResultUtil getBoardList(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		ResultUtil resultUtils = boardService.getBoardList(boardForm);

		return resultUtils;
	}

	//�Խ��� �󼼺���
	@RequestMapping(value = "/boardDetail")
	public String boardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardDetail";
	}


	@RequestMapping(value = "/getBoardDetail")
	@ResponseBody
	public BoardDto getBoardDetail(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));

		BoardDto boardDto = boardService.getBoardDetail(boardForm);

		MDC.remove("TRANSACTION_ID");

		return boardDto;
	}

	//�Խ��� �ۼ��ϱ�
	@RequestMapping(value = "/boardWrite")
	public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardWrite";
	}

	
	@RequestMapping(value = "/insertBoard")
	@ResponseBody
	public BoardDto insertBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		BoardDto boardDto = boardService.insertBoard(boardForm);

		return boardDto;
	}

	//�Խ��� �Խñ� ����
	@RequestMapping(value = "/deleteBoard")
	@ResponseBody
	public BoardDto deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
		
		BoardDto boardDto = boardService.deleteBoard(boardForm);
		
		MDC.remove("TRANSACTION_ID");

		return boardDto;
	}

	//�Խ��� �Խñ� ����
	@RequestMapping(value = "/boardUpdate")
	public String boardUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardUpdate";
	}

	
	@RequestMapping(value = "/updateBoard")
	@ResponseBody
	public BoardDto updateBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
		
		BoardDto boardDto = boardService.updateBoard(boardForm);
		
		MDC.remove("TRANSACTION_ID");

		return boardDto;
		
	
	}

	//�Խ��� �Խñ� �亯
	@RequestMapping(value = "/boardReply")
	public String boardReply(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardReply";
	}


	@RequestMapping(value = "/insertBoardReply")
	@ResponseBody
	public BoardDto insertBoardReply(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
		
		BoardDto boardDto = boardService.insertBoardReply(boardForm);
		
		MDC.remove("TRANSACTION_ID");

		return boardDto;
	}

	//���� �ٿ�ε�
	@RequestMapping("/fileDownload")
	public ModelAndView fileDownload(@RequestParam("fileNameKey") String fileNameKey, @RequestParam("fileName") String fileName,
			@RequestParam("filePath") String filePath) throws Exception {

	
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("fileNameKey", fileNameKey);
		fileInfo.put("fileName", fileName);
		fileInfo.put("filePath", filePath);

		return new ModelAndView("fileDownloadUtil", "fileInfo", fileInfo);
	}
}
