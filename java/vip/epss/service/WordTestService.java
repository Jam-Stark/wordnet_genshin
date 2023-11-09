package vip.epss.service;

import vip.epss.domain.Words_en_cn;

import java.util.List;

public interface WordTestService {
    public List<Words_en_cn> getRandomWords(int count);
    public boolean validateWord(String word, String userInput);
    public int calculateScore(int previousScore);
}