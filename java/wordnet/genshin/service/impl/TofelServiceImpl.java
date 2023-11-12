package wordnet.genshin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordnet.genshin.dao.TofelMapper;
import wordnet.genshin.domain.Tofel;
import wordnet.genshin.domain.TofelExample;
import wordnet.genshin.service.TofelService;
import wordnet.genshin.service.FamiliarService;

import java.util.*;

@Service(value="TofelService")
public class TofelServiceImpl implements TofelService {

    @Autowired
    private TofelMapper tofelMapper;
    @Autowired
    private FamiliarService familiarService;

    @Override
    public List<Tofel> selectOne(String word) {


        TofelExample tofelexample= new TofelExample();
        TofelExample.Criteria criteria=tofelexample.createCriteria();
        criteria.andWordEqualTo(word);

        return tofelMapper.selectByExample(tofelexample);
    }

    @Override
    public Tofel selectOne(int id){
        return tofelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tofel> selectMuti(Integer from, Integer to,String uname) {
        List<Tofel> tofelList=new ArrayList<Tofel>();
        for(;from<to;from++){
            tofelList.add(tofelMapper.selectByPrimaryKey(from));}
        tofelList.removeIf(item -> familiarService.detectWord(item.getWord(), uname));
        return tofelList;
    }


}