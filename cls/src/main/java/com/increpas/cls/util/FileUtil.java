package com.increpas.cls.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

/**
 * 이 클래스는 파일 업로드에 필요한 기능을 처리하기 위해서 만든 유틸리티 클래스
 * @author	이명환
 * @since	2020.06.16
 * @version v.1.0
 * 
 */

public class FileUtil {
	/*
	 	파일 이름이 중복되면 덮어쓰기를 하게 된다
	 	따라서 혹시 같은 이름의 파일이 존재하면 파일의 이름을 바꿔서 저장할 필요가 있다
	 	==> 같은 이름의 파일이 존재하면 이름을 바꿔주는 함수를 만들자
	 */
	
	public static String rename(String path, String oldName) {
		/*
		 	규칙]
		 		혹시 같은 이름의 파일이 존재하면 _1 의 형태로 숫자를 붙여서
		 		이름을 바꾸는 방식을 사용하자
		 		
		 	예]
		 		sample.txt	-->	sample_1.txt	-->	sample_2.txt
		 */
		
		int count = 0;	// 뒤에 붙을 번호를 기억할 변수
		String tmpName = oldName;	// 현재 이름을 별도로 기억시켜놓음
		File file = new File(path, oldName);
		while(file.exists()) {
			// 존재하는 경우가 발생하면? ==> 이름을 바꿔야 한다
			// ==> 붙일 '_번호' 를 증가시킨다
			count++;
			// .을 기준으로 앞의 내용과 뒤의 내용을 분리해야 한다
			int len = tmpName.lastIndexOf(".");
			String tmp1 = tmpName.substring(0, len);	// 파일이름
			String tmp2 = tmpName.substring(len);	// 확장자
			
			// 이제 분리된 것을 필요한 번호를 붙여서 다시 합친다
			oldName = tmp1 + "_" + count + tmp2;
			// 위 파일도 존재하는지 확인하기 위해서
			// 다시 File 객체로 만들어서 확인한다
			file = new File(path, oldName);
		}
		
		return oldName;
	}
	
	// null인 파일? 삭제
	public MultipartFile[] setArr(MultipartFile[] file) {
		MultipartFile[] tmp = null;
		List<MultipartFile> list = (List<MultipartFile>) Arrays.asList(file);
		for(int i=0; i<file.length; i++) {
			if(list.get(i) == null) list.remove(i);
		}
		tmp = (MultipartFile[])list.toArray();
		return tmp;
	}
	
	public String[] getSaveName(HttpSession session, MultipartFile[] file, String folder) {
		String[] savename = new String[file.length];
		String path = session.getServletContext().getRealPath("resources") + "/" + folder;
		
		for(int i=0; i<file.length; i++) {
			String oriname = file[i].getOriginalFilename();
			if(oriname != null || oriname.length() != 0) {
				savename[i] = rename(path, oriname);
			}
			
			// 파일 저장
			try {
				File refile = new File(path, savename[i]);
				file[i].transferTo(refile);	// 서버에 업로드
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return savename;
		
	}
}
