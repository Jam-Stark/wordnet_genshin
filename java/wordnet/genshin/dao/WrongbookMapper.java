package wordnet.genshin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wordnet.genshin.domain.Wrongbook;
import wordnet.genshin.domain.WrongbookExample;

public interface WrongbookMapper {
    long countByExample(WrongbookExample example);

    int deleteByExample(WrongbookExample example);

    int deleteByPrimaryKey(Integer wrongid);

    int insert(Wrongbook record);

    int insertSelective(Wrongbook record);

    List<Wrongbook> selectByExample(WrongbookExample example);

    Wrongbook selectByPrimaryKey(Integer wrongid);

    int updateByExampleSelective(@Param("record") Wrongbook record, @Param("example") WrongbookExample example);

    int updateByExample(@Param("record") Wrongbook record, @Param("example") WrongbookExample example);

    int updateByPrimaryKeySelective(Wrongbook record);

    int updateByPrimaryKey(Wrongbook record);
}