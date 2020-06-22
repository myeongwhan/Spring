package com.increpas.cls.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Component
@Aspect
public class MemberAOP {
   
   /*
     @Pointcut("execution(* com.increpas.cls.controller.member.Member.login(..)) || "
     + "execution(* com.increpas.cls.controller.member.Member.join(..)) ")
    */
   /*
     와일드카드
        "execution(접근지정자 반환값타입 경로.함수())"
        ==> 경로.함수 중 선언된 접근지정자와 일치하고 반환값타입이 같은 함수가 실핼될 때를 의미한다.
        매개변수 ( ) 의 의미 :
        ..   0개이상
        *   1개이상           
    */
   /*
          @Pointcut은 언제 실행할지를 정하는 선언적 의미의 함수
    */
   /*
   @Pointcut("execution(* com.increpas.cls.controller.member.Member.*Form(..))")
   public void loginCk() {
      System.out.println("##### pointcut exec");
   }
   */
   
//   @Before("loginCk()")
//   @Pointcut을 Before과 합쳐 사용할 수 있다.
   @Before("execution(* com.increpas.cls.controller.member.Member.*Form(..)) ||" +
		   "execution(* com.increpas.cls.service.BoardService.getBrdList(..))"
		   )
   // @Before은 매개변수에 선언된 내용이 실행되기 이전에 실행한다는 의미 반대의 개념은 @After 가있고
   // 둘을 모두 포함한 개념은 @Around도 있다. 
   public void membAutho(JoinPoint join) {
      System.out.println("********* membAutho()");
      /*
          (@Pointcut 안의 내용을 어드바이스라고한다.)
          JoinPoint   -
             Pointcut에 선언된 함수의 매개변수를 관리할 클래스이다.
             --함수가 갖고있는 매개변수를 언제실행할지를 선택해준다.
                매개변수가 몇개가 될지 모르니까,
                총괄로 관리해주는 어노테이션이 있는데 그것이 JoinPoint이다.--
             
             JoinPoint 객체에 실행함수의 매개변수를 꺼내는 방법
             Object[] obj = 변수.getArgs();
             오브젝트 계열에 어떤타입으로 매개변수가 선언되어 있을지 모르니까
             배열로 처리한다.
             저 함수로 처리해주면 저 반환값 타입이 오브젝트로 반환하게된다.
             따라서 꺼낼때는 인덱스로 꺼내야하고 강제 형변환이 필요하다.
       */
      // 매개변수 꺼내기
      Object[] obj = join.getArgs();
      // 첫 매개변수인 HttpservletRequest객체를 선언해주어야한다.
      HttpServletRequest req = (HttpServletRequest) obj[0];
      
      //URL : 전체경로를 다 뽑아오는거고  , URI는 프로젝트부터 맨 뒤까지 뽑아옴.( URI는 HTTP가 안붙음)
      //tmp는 2가지 View중에 하나로 이동시키기 위해 준비했다.
      String tmp = req.getRequestURI();
      tmp = tmp.substring(tmp.lastIndexOf("/") + 1,tmp.lastIndexOf("."));
      System.out.println("MemberAOP tmp = " + tmp);
      String view = (tmp.equals("join"))?"member/join" :"member/login";
      // /cls/member/login.cls ==> login으로 뽑아온다.
      // view를 비교해보자
      /*
      if(tmp.equals("join")) {
         view = "member/join";
      }
       위 주석을 한줄로 줄여서 삼항연산자로
          String view = (tmp.equals("join"))?"member/join" :"member/login";
          를 만들었다.
       */
      
      
      // req.에서 세션뽑아서 처리해준다
      HttpSession session = req.getSession();
      String sid = (String)session.getAttribute("SID");
      if(sid == null) {
         //이 경우는 login이 안된상태이다.
         ((ModelAndView)obj[1]).setViewName(view);
         ((ModelAndView) obj[1]).addObject("isLogin", false);
      }else {
         //이미 로그인 된 상태
         ((ModelAndView)obj[1]).setView(new RedirectView("/cls/main"));
         ((ModelAndView) obj[1]).addObject("isLogin", true);
      }
   }
   
   @Before("execution(* com.increpas.cls.service.BoardService.writeSrvc(..))")
   public void loginCk1(JoinPoint join) {
	   Object[] obj = join.getArgs();
	   HttpServletRequest req = (HttpServletRequest) obj[0];
	   
	   String view = "board/boardWrite";
	   
	   HttpSession session = req.getSession();
	   String sid = (String)session.getAttribute("SID");
	   if(sid == null) {
		   ((ModelAndView) obj[1]).addObject("isLogin", false);
	   } else {
		   ((ModelAndView) obj[1]).addObject("isLogin", true);
	   }
   }
   
   @Before("execution(* com.increpas.cls.service.BoardService.writeProcSrvc(..))")
   public void loginCk2(JoinPoint join) {
	   Object[] obj = join.getArgs();
	   HttpServletRequest req = (HttpServletRequest) obj[0];
	   
	   String view = "board/boardList";
	   
	   HttpSession session = req.getSession();
	   String sid = (String)session.getAttribute("SID");
	   if(sid == null) {
		   ((ModelAndView) obj[1]).addObject("isLogin", false);
	   } else {
		   ((ModelAndView) obj[1]).addObject("isLogin", true);
	   }
   }
}