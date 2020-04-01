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
/* RequestMapping - 요청 URL을 어떤 메소드가 처리할 지 여부를 결정
 * AutoWired - 각 상황의 타입에 맞는 IoC 컨테이너 안에 존재하는 Bean을 자동으로 주입해주게 됨
 * MDC를 설정하는 이유는  - 웹 요청에 대한 로그인 정보나 세션 정보를 추적할 수 있기 때문에 설정함
 * 					   이 소스코드에는 로그인 정보나 세션 정보가 없기 떄문에 board_seq값을 추가 하였음 
 * */
@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	//게시판 리스트
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

	//게시판 상세보기
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

	//게시판 작성하기
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

	//게시판 게시글 삭제
	@RequestMapping(value = "/deleteBoard")
	@ResponseBody
	public BoardDto deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
		
		BoardDto boardDto = boardService.deleteBoard(boardForm);
		
		MDC.remove("TRANSACTION_ID");

		return boardDto;
	}

	//게시판 게시글 수정
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

	//게시판 게시글 답변
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

	//파일 다운로드
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
