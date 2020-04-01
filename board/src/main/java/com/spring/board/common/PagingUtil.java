package com.spring.board.common;

import com.spring.board.dto.CommonDto;
import com.spring.board.form.CommonForm;


public class PagingUtil {

	public static CommonDto setPageUtil(CommonForm commonForm) {

		CommonDto commonDto = new CommonDto();

		String pagination = ""; //페이징 네비게이션 결과 값을 저장할 변수
		String functionName = commonForm.getFunction_name(); //페이징 목록을 요청하는 자바스크립트  함수명
		int currentPage = commonForm.getCurrent_page_no();// 현재 페이지 번호
		int countPerList = commonForm.getCount_per_list(); // 한 화면에 출력될 게시물 수
		int countPerPage = commonForm.getCount_per_page(); // 한화면에 출력될 페이지 수
		int totalListCount = commonForm.getTatal_list_count(); // 총 게시물 수
		int totalPageCount = totalListCount / countPerList;// 총 페이지 수
		if (totalListCount % countPerList > 0) { //총 페이지 수를 구할 때 int형으로 게산하면 나머지가 있는 경우 게시물이 존재하기 때문에 총 페이지의 수를 수정
			totalPageCount = totalPageCount + 1;
		}
		/* viewFirstpage - 연산되는 내용에 1을 빼고 더하는 이유는 한 화면에 출력되는 페이지 수가 10일때 페이징 네비게이션에 출력되는 페이지 번호는 1 ~ 10, 11 ~ 20, 21 ~ 30 처럼 출력되고
		 * 마지막 페이지의 번호(viewLastPage)를 한 화면에 출력되는 페이지의 수(countPerPage)를 나누었을 때 첫 페이지의 번호(viewFirstPage)부터 마지막 페이지의 번호 (viewLastPage)전에 번호보다
		 * 값이 1 많습니다. 그래서 한 화면에 출력되는 페이지 수(countPerPage)로 나누기 전에 1을 더해주어야 합니다.*/
		int viewFirstPage = (((currentPage - 1) / countPerPage) * countPerPage) + 1; //한 화면에 첫 페이지 번호
		/*첫 페이지 번호가 1, 11 ,21처럼 일의자리가 1로 시작하므로 한 화면에 출력되는 페이지 수를 더한 후 1을 빼주어야 합니다.
		 * 그리고 마지막 페이지 번호에 경우 총 페이지의 수를 고려해야 함/ 예를 들어 총 게시글 수가 150개인 경우 총 페이지의 수는 
		 * 15가 되고 마지막 페이지번호는 10과 20입니다. 이처럼 마지막 페이지 번호가 총 페이지 번호보다 큰 경우는 
		 * 16 ~ 20 페이지에는 출력할 수 있는 게시글이 없기 때문에 마지막 페이지 번호에 총 페이지 번호를 저장해야 함*/
		int ViewLastPage = viewFirstPage + countPerPage - 1; // 한 화면에 마지막 페이지 번호
		if (ViewLastPage > totalPageCount) { // 마지막 페이지의 수가 총 페이지의 수보다 큰 경우는 게시물이 존재하지 않기 때문에 마지막 페이지 수를 수정
			ViewLastPage = totalPageCount;
		}

		int totalFirstPage = 1; // 전체 페이지 중에 처음 페이지
		int totalLastPage = totalPageCount; //전체 페이지 중에 마지막 페이지
		int prePerPage = 0;// 이전 화면에 첫번째 번호
		
		if (viewFirstPage - countPerPage > 0) {
			prePerPage = viewFirstPage - countPerPage;
		} else {
			prePerPage = totalFirstPage;
		}
		int nextPerPage = 0;  // 이후 화면에 첫번째 번호
		if (viewFirstPage + countPerPage < totalPageCount) {
			nextPerPage = viewFirstPage + countPerPage;
		} else {
			nextPerPage = totalPageCount;
		}

        //페이지 네비게이션 설정
		/*페이지 번호를 서렁하는 부분은 첫 페이지 번호, 마지막 페이지 번호만 알고 있다면 for을 사용하여 구현 할 수 있습니다.
		 * for문 안에 if문은 현재 페이지 번호에 스타일을 추가하기 위해 추가했습니다. a태그 구현시에 functionName을 추가한 것은
		 * 페이지 번호를 클릭 시에 게시판 조회 함수를 호출하기 위해 추가했습니다.*/
		pagination += "<div class='pagination'>";
		pagination += "<a href='javascript:" + functionName + "(\"" + totalFirstPage + "\");' class=\"direction_left01\">[<<]</a>";
		pagination += "<a href='javascript:" + functionName + "(" + prePerPage + ");' class=\"direction_left01\">[<]</a>";
		for (int a = viewFirstPage; a <= ViewLastPage; a++) {
			if (a == currentPage) {
				pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");' class='onpage'>[" + a + "]</a>";
			} else {
				pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");'>[" + a + "]</a>";
			}
		}
		pagination += "<a href='javascript:" + functionName + "(" + nextPerPage + ");' class=\"direction_right01\">[>]</a>";
		pagination += "<a href='javascript:" + functionName + "(" + totalLastPage + ");' class=\"direction_right01\">[>>]</a>";
		pagination += "</div>";

		int offset = ((currentPage - 1) * countPerPage) + 1; //한 화면에 표출되는 게시물의 시작번호 (쿼리 조건절 )

		// LIMIT은 가져올 row의 수, OFFSET은 몇 번 쨰 ROW부터 가져올 지를 결정
		commonDto.setLimit(countPerList);
		commonDto.setOffset(offset);
		commonDto.setPagination(pagination);

		return commonDto;
	}
}
