package com.neo.web;

import com.neo.OkHttp.CrawlPage;
import com.neo.entity.UserData;
import com.neo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/userLogin")
    @ResponseBody
    public String login(@RequestBody Map<String, Object> data) {
        String userId = data.get("userId").toString();
        String password = data.get("password").toString();
        List<String> str = new ArrayList<String>();
        CrawlPage crawlPage = new CrawlPage();

        try {
            str = crawlPage.Login(userId, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str.size() <= 1) {

            return "账号或密码错误";
        }
        UserData userData = new UserData(str.get(1), str.get(0), str.get(1));
        UserData userData1 = userRepository.findByStudentId(userId);
        if (userData1 == null) {
            userRepository.save(userData);
        }
        return "okLogin";
    }


    @PostMapping("/autoLogin")
    @ResponseBody
    public String autoLogin(@RequestBody Map<String, Object> data) {
        String userId = data.get("userId").toString();
        String password = data.get("password").toString();
        UserData userData = userRepository.findByStudentId(userId);
        if(userData !=null&&userId.equals(userData.getStudentId())&&password.equals(userData.getPassword())){
            return "ok";
        }
        return "error";
    }
}