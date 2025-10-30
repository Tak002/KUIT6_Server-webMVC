package jwp.support;

import jwp.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> mappings = new HashMap<>();

    public RequestMapper() {
        mappings.put("/:GET", new HomeController());
        mappings.put("/user/signup:POST", new CreateUserController());
        mappings.put("/user/list:GET", new ListUserController());
        mappings.put("/user/login:POST", new LoginController());
        mappings.put("/user/logout:GET", new LogoutController());
        mappings.put("/user/update:POST", new UpdateUserController());
        mappings.put("/user/updateForm:GET", new UserUpdateFormController());
        mappings.put("/qna/form:GET", new CreateQuestionFormController());
        mappings.put("/user/loginForm:GET", new LoginFormController());
    }

    public Controller getMapping(String requestURI, String method) {
        return mappings.get(requestURI + ":" + method);
    }
}
