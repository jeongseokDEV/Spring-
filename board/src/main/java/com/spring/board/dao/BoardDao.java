package com.spring.board.dao;

import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardFileDto;
import com.spring.board.form.BoardFileForm;
import com.spring.board.form.BoardForm;
/* @Repository - DAO�� �޼ҵ忡�� �߻��� �� �ִ� unchecked exception���� �������� DataAccessException���� ó���� �� ����
 * @Resource - autowired�� ���������� �� ��ü�� �������ִµ� �������� Autowired�� Ÿ������, Resource�� �̸����� ���� ����
 * */
@Repository
public class BoardDao {

	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.spring.board.boardMapper";

	
	public int getBoardCnt(BoardForm boardForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardCnt", boardForm);
	}

	
	public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {

		return sqlSession.selectList(NAMESPACE + ".getBoardList", boardForm);
	}

	
	public int updateBoardHits(BoardForm boardForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoardHits", boardForm);
	}


	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardDetail", boardForm);
	}
	
	
	public List<BoardFileDto> getBoardFileList(BoardFileForm boardFileForm) throws Exception {

		return sqlSession.selectList(NAMESPACE + ".getBoardFileList", boardFileForm);
	}


	public int getBoardReRef(BoardForm boardForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardReRef", boardForm);
	}
	
	
	public int insertBoard(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoard", boardForm);
	}
	
	
	public int insertBoardFile(BoardFileForm boardFileForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardFile", boardFileForm);
	}

	
	public int deleteBoard(BoardForm boardForm) throws Exception {

		return sqlSession.delete(NAMESPACE + ".deleteBoard", boardForm);
	}

	
	public int updateBoard(BoardForm boardForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoard", boardForm);
	}
	

	public BoardDto getBoardReplyInfo(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardReplyInfo", boardForm);
	}
	

	public int updateBoardReSeq(BoardForm boardForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoardReSeq", boardForm);
	}
	

	public int insertBoardReply(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardReply", boardForm);
	}
	

	public int deleteBoardFile(BoardFileForm boardFileForm) throws Exception {
		return sqlSession.update(NAMESPACE + ".deleteBoardFile", boardFileForm);
	}
	
}
