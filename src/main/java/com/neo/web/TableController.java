package com.neo.web;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.neo.OkHttp.CrawlPage;
import com.neo.entity.TimeTable;
import com.neo.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class TableController {

    @Autowired
    private TableRepository tableRepository;


    /**
     * @param reqMap
     * @return
     */
    @PostMapping("/findAll")
    public List<TimeTable> findAll(@RequestBody Map<String, Object> reqMap) {
        return tableRepository.findByStudentId(reqMap.get("userId").toString());

    }

    /**
     * @param reqMap
     * @return
     */
    @PostMapping("/delete")
    @Transactional  //神奇注解 ，加上可以删除数据库
    public String delete(@RequestBody Map<String, Object> reqMap) {
        tableRepository.deleteByStudentId(reqMap.get("userId").toString());
        return "okDelete";
    }

    /**
     * @param reqMap
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @Transactional  //神奇注解 ，加上可以删除数据库
    public String add(@RequestBody Map<String, Object> reqMap) {
        String id = reqMap.get("userId").toString();
        String password = reqMap.get("password").toString();
        tableRepository.deleteByStudentId(id);
        List<TimeTable> timeTables = new ArrayList<>();
        CrawlPage crawl = new CrawlPage();
        try {
            timeTables = crawl.crawlPageWithoutAnalyseJs("http://score.xaau.edu.cn/eams/localLogin.action",id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (TimeTable e : timeTables) {
            tableRepository.save(e);
        }
        return "okAdd";
    }
}
