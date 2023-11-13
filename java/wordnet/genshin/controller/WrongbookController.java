package wordnet.genshin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wordnet.genshin.domain.Wrongbook;
import wordnet.genshin.service.WrongbookService;
import wordnet.genshin.utils.MessageAndData;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "wrongbook")
public class WrongbookController {
    @Autowired
    private WrongbookService wrongbookService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public MessageAndData optInsert(
            @RequestParam(value = "word") String word,
            HttpSession httpSession
    ) {
        String uname=(String) httpSession.getAttribute("UserName");
        Date date = new Date();
        boolean b = wrongbookService.InsertOne(word,uname);
        if (b != true) {
            return MessageAndData.error().setMessage("生词本插入单词失败(该单词已加入生词本)");
        } else {
            return MessageAndData.success().add("word", word).add("date", date);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public MessageAndData optDelete(
            @RequestParam(value = "word") String word,
            HttpSession httpSession
    ) {
        String uname=(String) httpSession.getAttribute("UserName");
        boolean b = wrongbookService.DeleteOne(word,uname);
        if (b != true) {
            return MessageAndData.error().setMessage("从生词本删除单词失败(生词本中不存在该单词)");
        } else {
            return MessageAndData.success().add("word", word);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String optSearch(
            @PathVariable(value = "word") String word
    ) {
        Wrongbook wrongbook = wrongbookService.selectOne(word);
        //MessageAndData messageAndData = wrong MessageAndData();
        if (wrongbook == null) {
            return null;
        } else {
            return wrongbook.getWord();
        }
    }
    @ResponseBody
    @RequestMapping("show")
    public MessageAndData showByUser(HttpSession httpSession){

        String uname=(String) httpSession.getAttribute("UserName");

        List<Wrongbook> dataList=wrongbookService.selectByuser(uname);

        return MessageAndData.success().add("datalist",dataList);
    }
}