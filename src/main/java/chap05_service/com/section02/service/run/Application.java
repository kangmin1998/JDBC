package chap05_service.com.section02.service.run;

import chap05_service.com.section02.service.model.service.MenuService;

public class Application {
    public static void main(String[] args) {
        /* 서비스에 정의한 메소드 호출 확인 */
        new MenuService().registNewMenu();
    }
}
