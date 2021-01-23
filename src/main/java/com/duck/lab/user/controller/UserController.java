package com.duck.lab.user.controller;

import com.duck.lab.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class UserController {
    //@Autowired도 되지만 권고되지는 않는 방식임
    @Resource(name="com.duck.springlab.user.service.UserService")
    UserService userService;

    @RequestMapping(value="/user/{no}", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @ResponseBody가 없으면 아래와 같은 메세지가 나온다.
     * 그 이유는 ResponseBody가 있으면 리턴 값을 HTTP 응답 데이터로 사용한다는 의미이기 때문이다.
     * Resolved [org.springframework.http.converter.HttpMessageNotReadableException: I/O error while reading input message; nested exception is java.io.IOException: Stream closed]
     * response는 아래와 같다.
     * {
     *     "timestamp": "2021-01-23T15:01:41.934+00:00",
     *     "status": 400,
     *     "error": "Bad Request",
     *     "message": "",
     *     "path": "/user/12"
     * }
     */
    /**
     * sample request
     * http://localhost:8080/user/12 (Post)
     * body : { "name": "aa" }
     */
    public String createUser(
            @RequestBody Map<String, Object> info,
            @PathVariable int no) {
        String name = (String) info.get("name");
        System.out.println(name + "," + no);

        userService.createUser(name, no);
        System.out.println("finished..");

        return name;
    }
}
