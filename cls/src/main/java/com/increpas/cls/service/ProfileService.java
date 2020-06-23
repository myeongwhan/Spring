package com.increpas.cls.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.util.FileUtil;
import com.increpas.cls.vo.ProfileVO;

/**
 * 이 클래스는 파일 업로드를 처리할 클래스
 * @author	이명환
 * @since	2020.06.16
 *
 */
public class ProfileService {
	// 필요한 클래스 자동주입 받기
	@Autowired
	MemberDAO mDAO;
	@Autowired
	FileUtil fileUtil;
	
	// 파일 업로드 처리함수
	public String[] uploadProc(HttpSession session, MultipartFile[] file) {
		/*
		 	이 함수가 파일을 업로드하기 위해서는 
		 	컨트롤러에서 업로드할 파일의 정보를 받아와야 한다
		 	
		 	여러 개의 파일이 업로드될 예정이므로 반복해서 처리해야 한다
		 */
		String[] savename = new String[file.length];
		String path = session.getServletContext().getRealPath("resources") + "/profile";
		
		for(int i=0; i<file.length; i++) {
			String oriname = file[i].getOriginalFilename();
			if(oriname != null || oriname.length() != 0) {
				savename[i] = fileUtil.rename(path, oriname);
			}
			
			// 파일 저장
			try {
				File refile = new File(path, savename[i]);
				file[i].transferTo(refile);	// 서버에 업로드
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("uploadProc() 실행완료");
		return savename;
	}
	
	// 파일 정보 db등록 처리함수
	public int insertFileInfo(ProfileVO fVO, String[] savename) {
		int cnt = 0;
		/*
		 	VO클래스에 파일 정보가 있으므로 받아오고
		 	String[] 에 저장이름이 있으므로 이것도 받아온다
		 */
		/*
		 	할일
		 		여러 개의 파일 정보가 등록이 되어야 하고
		 		insert 질의명령은 한 번에 한 행만 입력할 수 있으므로
		 		반복 처리해야 한다
		 		
		 		방법 1]
		 			이 함수 내에서는 한 개만 처리를 하고 컨트롤러에서 반복해서 처리해주는 방법
		 			
		 		방법 2]
		 			여기서 5번 반복한다
		 */
		
//		fVO.setDir("/profile/");
		
		for(int i=0; i<savename.length; i++) {
			// 한 번 반복할 때마다 저장할 정보를 만든다
			fVO.setOriname(fVO.getFile()[i].getOriginalFilename());
			fVO.setLen(fVO.getFile()[i].getSize());
			fVO.setSavename(savename[i]);
			
			// 이제 한 개씩 데이터베이스 작업을 해주면 된다
			cnt = cnt + mDAO.insertProfile(fVO);
		}
		
//		System.out.println("insertFileInfo() 실행완료");
		return cnt;
	}
	
	public int addProfile(ProfileVO fVO, HttpSession session) {
		int cnt = 0;
		
		MultipartFile[] files = fVO.getFile();
		String[] sname = uploadProc(session, files);
		cnt = insertFileInfo(fVO, sname);
		
//		System.out.println("addProfile() 실행완료");
		return cnt;
	}
}
