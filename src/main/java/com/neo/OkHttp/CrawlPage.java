package com.neo.OkHttp;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.neo.dataHandle.StringUnit;
import com.neo.dataHandle.TableData;
import com.neo.dataHandle.TableHandle;
import com.neo.entity.TimeTable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CrawlPage {

    @Autowired

    TableHandle tableHandle = new TableHandle();

    /**
     * 功能描述：抓取页面时不解析页面的js
     *
     * @param url
     * @throws Exception
     */
    public List<TimeTable> crawlPageWithoutAnalyseJs(String url, String id, String mpassword) throws Exception {
        List<TimeTable> timeTables = new ArrayList<TimeTable>();
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setRedirectEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setTimeout(800);
            webClient.getCookieManager().setCookiesEnabled(true);
            HtmlPage page = webClient.getPage(url);
            HtmlForm form = page.getFormByName("loginForm");
            final HtmlSubmitInput button = form.getInputByName("submitBtn");
            HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
            HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
            username.setValueAttribute(id);
            password.setValueAttribute(mpassword);
            HtmlPage retPage = (HtmlPage) button.click();
            webClient.waitForBackgroundJavaScript(100);
            System.out.println(retPage.asXml());
            HtmlPage page1 = webClient.getPage("http://score.xaau.edu.cn/eams/courseTableForStd.action");
            webClient.waitForBackgroundJavaScript(100);
            System.out.println(page1.asXml());
            HtmlPage page2 = webClient.getPage("http://score.xaau.edu.cn/eams/home.action");
            webClient.waitForBackgroundJavaScript(100);
            System.out.println(page2.asXml());
            webClient.close();
            System.out.println("lttttt*************************************************");
            String TableHtml = page1.asXml();
            String userHtml = page2.asXml();
            timeTables = dataHandle(TableHtml, userHtml);  //解析数据
        } catch (Exception e) {
            System.out.println("爬取课表异常");
            System.err.println("Exception: " + e);
        }
        return timeTables;
    }

    public List<TimeTable> dataHandle(String table, String user) {
        List<TimeTable> timeTables = null;
        try {
            List<TableData> list = new ArrayList<TableData>();
            Document document0 = Jsoup.parse(user);
            String userdata = document0.select("div[class=banner_area]").select("a[target=_blank]").text();
            System.out.println(userdata);
            Document document = Jsoup.parse(table);
            Elements element = document.select("table[id=manualArrangeCourseTable]").select("td[class=infoTitle]");
            System.out.println(element);
            for (Element ele : element) {
                String title = ele.select("td").text();  //.text()为解析标签中的文本内容
                String id = ele.attr("id");  //.text()为解析标签中的文本内容
                int rowspan = Integer.valueOf(ele.attr("rowspan"));
                System.out.println("课程的标题为:" + title + "课程id:" + id + "节数:" + rowspan);
                list.add(new TableData(id, rowspan, title));
            }
            timeTables = tableHandle.handle(userdata, list);
        } catch (NumberFormatException e) {
            System.out.println("处理数据异常");
            e.printStackTrace();
        }

        return timeTables;
    }

    public List<String> Login(String userId, String mpassword) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setRedirectEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setTimeout(800);
            webClient.getCookieManager().setCookiesEnabled(true);
            HtmlPage page = webClient.getPage("http://score.xaau.edu.cn/eams/localLogin.action");
            HtmlForm form = page.getFormByName("loginForm");
            final HtmlSubmitInput button = form.getInputByName("submitBtn");
            HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
            HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
            username.setValueAttribute(userId);
            password.setValueAttribute(mpassword);
            HtmlPage retPage = (HtmlPage) button.click();
            webClient.waitForBackgroundJavaScript(400);
            String Html = retPage.asXml();
            System.out.println(Html);
            Document document = Jsoup.parse(Html);
            String userdata1 = document.select("div[class=actionError]").select("span").text();
            if (!userdata1.equals("")) {
                list.add(userdata1);
                return list;
            }
            System.out.println("************************************************************************************");
            HtmlPage page2 = webClient.getPage("http://score.xaau.edu.cn/eams/home.action");
            webClient.waitForBackgroundJavaScript(400);
            webClient.close();
            //数据解析
            String userHtml = page2.asXml();
            System.out.println(userHtml);
            Document document0 = Jsoup.parse(userHtml);
            String userdata = document0.select("div[class=banner_area]").select("a[target=_blank]").text();
            StringUnit stringUnit = new StringUnit();
            list = stringUnit.StringHandle(userdata); //处理数据
            System.out.println("list大小" + list.size());
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }
        return list;
    }

}



