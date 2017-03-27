package com.pt.ist.test.tvs;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.pt.ist.tvs.InvalidOperationException;
import com.pt.ist.tvs.Question;

public class QuestionClassTest {

	@Test
	public void test1(){
		Question q = null;
		String body = "What is the largest country in the world?";
		String subject = "Geography";
		int weight = 15;
		int choice = 2;
		List<String> choices = new ArrayList<String>();
		choices.add("Portugal");
		choices.add("Russia");
		choices.add("Spain");
		choices.add("Belgium");
		choices.add("France");
		choices.add("Chade");
		choices.add("Israel");
		choices.add("Malta");
		choices.add("Cyprus");
		choices.add("Australia");
		try {
			q = new Question(body,choices, choice, subject, weight);
		} catch (InvalidOperationException e) {
			fail();
		}
		assertEquals(weight,q.getWeight());
		assertEquals(choice,q.grade(choice));
		assertTrue(q.getSubjects().contains(subject));
		assertTrue(q.getSubjects().size() == 1);
	}
	
	@Test
	public void test2(){
		Question q = null;
		String body = "What is the best course in MEIC?";
		String subject = "Education";
		int weight = 3;
		int choice = 28;
		List<String> choices = new ArrayList<String>();
		choices.add("SIRS");
		choices.add("VI");
		choices.add("DAD");
		choices.add("AS");
		choices.add("VI");
		choices.add("AID");
		choices.add("AID");
		choices.add("TVS <- This One!!!");
		choices.add("ETPN");
		choices.add("CNV");
		choices.add("ES");
		try {
			q = new Question(body, choices, choice, subject, weight);
		} catch (InvalidOperationException e) {
			
		}
	}
	
}
