package kz.nbt;

import java.util.ArrayList;

public class Storage {
	
	private ArrayList<String> quoteList;
    Storage()
    {
        quoteList = new ArrayList<String>();
        quoteList.add("Начинать всегда стоит с того, что сеет сомнения. \n\nБорис Стругацкий.");
        quoteList.add("80% успеха - это появиться в нужном месте в нужное время.\n\nВуди Аллен");
        quoteList.add("Мы должны признать очевидное: понимают лишь те,кто хочет понять.\n\nБернар Вербер");
    }

    String getRandQuote()
    {
    		//�������� ��������� �������� � ��������� �� 0 �� ������ �������� �������
        int randValue = (int)(Math.random() * quoteList.size());
        //�� ��������� �������� ������ �� ��������� �������� � ���������� ��  
        return quoteList.get(randValue);
    }
}