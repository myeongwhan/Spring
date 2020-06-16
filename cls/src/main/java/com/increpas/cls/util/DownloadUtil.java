package com.increpas.cls.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 이 클래스는 다운로드를 실제 실행할 특수뷰 클래스
 * 이 클래스가 뷰가 되기 위해서는 상위 클래스로 View 클래스를 이용해야 한다
 * 뷰 클래스를 위한 상위(추상 클래스)가 AbstractView 이다
 * @author	이명환
 * @since	2020.06.16
 * @version	v.1.0
 *
 */
public class DownloadUtil extends AbstractView {
	/*
		누군가 다운로드 뷰를 위해서 이 클래스를 생성하면
		파일 다운로드 방식으로 컨텐트 방식을 변경하고자 한다
	 */
	public DownloadUtil() {
		setContentType("application/download;charset=UTF-8");
		// 이것은 JSP 문서에 있는
		// contentType="text/html; charset=UTF-8" 부분과 같은 역할을 한다
	}
	
	// 반드시 renderMergedOutputModel() 이라는 함수를 오버라이드해줘야 한다
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
											HttpServletResponse response) throws Exception {
		/*
			이 안에서 필요한 기능을 구현하면 된다
			이 안에 코딩하는 내용은 뷰의 역할에 따라서 달라진다
			
			다운로드 처리를 위해서는 
			스트림방식으로 다운로드 처리를 해주면 된다
			
			파라미터
			
				***
				Map model	: 컨트롤러에서 주는 Model을 받는 매개변수
							주의]
								앞으로 이 클래스를 뷰로 사용할 경우에는
								Map 형태로 다운로드해줄 파일의 정보를 알려줘야 한다
								
				HttpServletRequest request,
				HttpServletResponse response
					==> 요청과 응답을 관리할 클래스를 받는 매개변수
		 */
		
		/*
			할일
			1. 다운로드해줄 파일을 File 객체로 만든다
			약속]
				우리는 컨트롤러에서 다운로드해줄 파일의 정보를
				downloadFile 이라는 키값으로 File 형태로 만들어서 입력해주기로 약속한다
		 */
			
		File oriFile = (File) model.get("downloadFile");
		/*
		 	2. 파일의 헤더 부분을 다운로드형태로 변경한다
		 		이 부분은 파일의 형태에 대한 학습이 필요하다	(공부할 것)
		 		지금은 복사해서 사용하도록 한다
		 */
		response.setContentType(getContentType());
		response.setContentLength((int)oriFile.length());
		// 이렇게 해야 zip 파일 등 텍스트 파일이 아닌 경우라도 다운로드가 되기 때문
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		/*
		 	파일 다운로드 팝업창에 표시할 내용을 꾸민다
		 	우리는 파일 팝업창에 다운로드할 파일의 이름과 크기를 출력할 예정이다
		 	파일의 이름이 한글인 경우를 대비해서 인코딩을 해줘야 하는데
		 */
		String fileName = new String(oriFile.getName().getBytes("EUC-KR"), "ISO-8859-1");
		
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";fileLength=\"" + ((int)oriFile.length()) + "\";");
		// 파일 다운로드 방식을 기계어 다운로드 방식으로 지정한다
		
		/*
		 	3. 스트림을 이용해서 파일을 클라이언트에게 전송한다
		 		이 과정은 한 쪽에서 읽어서 다른 쪽에 전송하는
		 		일반적인 파일 복사 방식을 적용한다
		 */
		// 클라이언트에게 연결된 스트림 생성
		OutputStream out = response.getOutputStream();
		// 다운로드해줄 파일과 연결된 스트림을 생성
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(oriFile);
			
			/*
			while(true) {
				byte[] buff = new byte[1024];
				int len = fis.read(buff);
				if(len < 0) {
					break;
				}
				out.write(buff, 0, len);
			}
			*/
			//	스프링은 이 과정을 대신해줄 함수가 이미 만들어져 있다
			FileCopyUtils.copy(fis, out);
			out.flush();	// 맨 마지막 버퍼는 얼마나 채워질지 모르니 강제로 내보냄
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
			fis.close();
		}
	}

}
