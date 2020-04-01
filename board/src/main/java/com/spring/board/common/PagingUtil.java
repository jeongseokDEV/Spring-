package com.spring.board.common;

import com.spring.board.dto.CommonDto;
import com.spring.board.form.CommonForm;


public class PagingUtil {

	public static CommonDto setPageUtil(CommonForm commonForm) {

		CommonDto commonDto = new CommonDto();

		String pagination = ""; //����¡ �׺���̼� ��� ���� ������ ����
		String functionName = commonForm.getFunction_name(); //����¡ ����� ��û�ϴ� �ڹٽ�ũ��Ʈ  �Լ���
		int currentPage = commonForm.getCurrent_page_no();// ���� ������ ��ȣ
		int countPerList = commonForm.getCount_per_list(); // �� ȭ�鿡 ��µ� �Խù� ��
		int countPerPage = commonForm.getCount_per_page(); // ��ȭ�鿡 ��µ� ������ ��
		int totalListCount = commonForm.getTatal_list_count(); // �� �Խù� ��
		int totalPageCount = totalListCount / countPerList;// �� ������ ��
		if (totalListCount % countPerList > 0) { //�� ������ ���� ���� �� int������ �Ի��ϸ� �������� �ִ� ��� �Խù��� �����ϱ� ������ �� �������� ���� ����
			totalPageCount = totalPageCount + 1;
		}
		/* viewFirstpage - ����Ǵ� ���뿡 1�� ���� ���ϴ� ������ �� ȭ�鿡 ��µǴ� ������ ���� 10�϶� ����¡ �׺���̼ǿ� ��µǴ� ������ ��ȣ�� 1 ~ 10, 11 ~ 20, 21 ~ 30 ó�� ��µǰ�
		 * ������ �������� ��ȣ(viewLastPage)�� �� ȭ�鿡 ��µǴ� �������� ��(countPerPage)�� �������� �� ù �������� ��ȣ(viewFirstPage)���� ������ �������� ��ȣ (viewLastPage)���� ��ȣ����
		 * ���� 1 �����ϴ�. �׷��� �� ȭ�鿡 ��µǴ� ������ ��(countPerPage)�� ������ ���� 1�� �����־�� �մϴ�.*/
		int viewFirstPage = (((currentPage - 1) / countPerPage) * countPerPage) + 1; //�� ȭ�鿡 ù ������ ��ȣ
		/*ù ������ ��ȣ�� 1, 11 ,21ó�� �����ڸ��� 1�� �����ϹǷ� �� ȭ�鿡 ��µǴ� ������ ���� ���� �� 1�� ���־�� �մϴ�.
		 * �׸��� ������ ������ ��ȣ�� ��� �� �������� ���� ����ؾ� ��/ ���� ��� �� �Խñ� ���� 150���� ��� �� �������� ���� 
		 * 15�� �ǰ� ������ ��������ȣ�� 10�� 20�Դϴ�. ��ó�� ������ ������ ��ȣ�� �� ������ ��ȣ���� ū ���� 
		 * 16 ~ 20 ���������� ����� �� �ִ� �Խñ��� ���� ������ ������ ������ ��ȣ�� �� ������ ��ȣ�� �����ؾ� ��*/
		int ViewLastPage = viewFirstPage + countPerPage - 1; // �� ȭ�鿡 ������ ������ ��ȣ
		if (ViewLastPage > totalPageCount) { // ������ �������� ���� �� �������� ������ ū ���� �Խù��� �������� �ʱ� ������ ������ ������ ���� ����
			ViewLastPage = totalPageCount;
		}

		int totalFirstPage = 1; // ��ü ������ �߿� ó�� ������
		int totalLastPage = totalPageCount; //��ü ������ �߿� ������ ������
		int prePerPage = 0;// ���� ȭ�鿡 ù��° ��ȣ
		
		if (viewFirstPage - countPerPage > 0) {
			prePerPage = viewFirstPage - countPerPage;
		} else {
			prePerPage = totalFirstPage;
		}
		int nextPerPage = 0;  // ���� ȭ�鿡 ù��° ��ȣ
		if (viewFirstPage + countPerPage < totalPageCount) {
			nextPerPage = viewFirstPage + countPerPage;
		} else {
			nextPerPage = totalPageCount;
		}

        //������ �׺���̼� ����
		/*������ ��ȣ�� �����ϴ� �κ��� ù ������ ��ȣ, ������ ������ ��ȣ�� �˰� �ִٸ� for�� ����Ͽ� ���� �� �� �ֽ��ϴ�.
		 * for�� �ȿ� if���� ���� ������ ��ȣ�� ��Ÿ���� �߰��ϱ� ���� �߰��߽��ϴ�. a�±� �����ÿ� functionName�� �߰��� ����
		 * ������ ��ȣ�� Ŭ�� �ÿ� �Խ��� ��ȸ �Լ��� ȣ���ϱ� ���� �߰��߽��ϴ�.*/
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

		int offset = ((currentPage - 1) * countPerPage) + 1; //�� ȭ�鿡 ǥ��Ǵ� �Խù��� ���۹�ȣ (���� ������ )

		// LIMIT�� ������ row�� ��, OFFSET�� �� �� �� ROW���� ������ ���� ����
		commonDto.setLimit(countPerList);
		commonDto.setOffset(offset);
		commonDto.setPagination(pagination);

		return commonDto;
	}
}
