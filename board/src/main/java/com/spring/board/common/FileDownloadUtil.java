package com.spring.board.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
/*SuppressWarning - unchecked - 검증되지 않은 연산자 관련 경고 억제*/
public class FileDownloadUtil extends AbstractView {
 
    public FileDownloadUtil() {
       
        setContentType("application/download; charset=utf-8");
    }
 
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
         
        @SuppressWarnings("unchecked")
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("fileInfo"); 
        
        String fileNameKey 	= (String) fileInfo.get("fileNameKey");	// 파일 암호화 명
        String fileName 	= (String) fileInfo.get("fileName");	// 파일 명
        String filePath 	= (String) fileInfo.get("filePath");	// 파일 경로
         
        File file = new File(filePath, fileNameKey);
         
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());

   
        String userAgent = request.getHeader("User-Agent");
        
        /* 파일 다운로드 시 한글 및 깨짐 방지 */
        // IE 다운로드
        if (userAgent.indexOf("MSIE") > -1) {
        	
        	fileName = URLEncoder.encode(fileName, "UTF-8");
        }
		 
        // IE 11 다운로드
        if (userAgent.indexOf("Trident") > -1) {
        	fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		
        else {
        	//크롬 다운로드
        	fileName = new String( fileName.getBytes("UTF-8"), "8859_1");
		}
 
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        OutputStream out = response.getOutputStream();
 
        FileInputStream fis = null;
 
        try {
        	
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
            
        } finally {
        	
            if(fis != null) {
            	fis.close();
            }
        }
        
        out.flush();
    }
}