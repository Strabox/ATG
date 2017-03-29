package com.pt.ist.test.tvs;

import java.util.Arrays;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.pt.ist.tvs.InvalidOperationException;
import com.pt.ist.tvs.Question;

public class QuestionClassTest {
	
	private static final int MAXIMUM_WEIGHT = 20;
	
	//Corresponds to the Test Case 1 in Matrix
	@Test
	public void test1_QuestionWithMaximumChoicesAllowed(){
		Question q = null;
		int weight = 15, correctChoice = 5;
		String body = "What is the largest country in the world?";
		String[] subjects = {"Geography"};
		// 10 choices (MAX = 10)
		String[] validChoices = {"Portugal","France","Spain","Belgium","Russia",
				"Chade","Israel","Malta","Cyprus","Australia"};
		try {
			q = new Question(body,Arrays.asList(validChoices), correctChoice, subjects[0], weight);
			assertEquals(weight,q.getWeight());
			assertEquals(weight,q.grade(correctChoice));
			assertEquals(subjects,q.getSubjects().toArray());
			assertEquals(validChoices,q.getChoices().toArray());
		} catch (InvalidOperationException e) {
			fail("Maximum number of choices boundary may have problems!");
		}
	}
	
	//Corresponds to the Test Case 2 in Matrix
	@Test
	public void test2_MaximumNumberOfChoicesOverflow() {
		int weight = 3, correctChoice = 8;
		String body = "What is the best course in MEIC?";
		String[] subjects = {"Education"};
		// 11 Choices (MAX = 10)
		String[] invalidChoices = {"SIRS","OGFI","DAD","AS","VI","AID","CNV","TVS","ES","ETPN","CMU"};
		try{
			new Question(body, Arrays.asList(invalidChoices), correctChoice, subjects[0], weight);
			fail("Maximum number of choices boundary may have problems!");
		}catch(InvalidOperationException e) {
			//Test success
		}
	}
	
	//Corresponds to the Test Case 3 in Matrix
	@Test
	public void test3_MinimumNumberOfChoices() {
		Question q;
		int weight = 17, correctChoice = 2;
		String body = "What is the best Computer Science area?";
		String subjects[] = {"Education"};
		// 2 Choices (MIN = 2)
		String[] validChoices = {"Anything Else","Testing and Software Validation"};
		try {
			q = new Question(body, Arrays.asList(validChoices), correctChoice, subjects[0], weight);
			assertEquals(weight,q.getWeight());
			assertEquals(subjects,q.getSubjects().toArray());
			assertEquals(validChoices,q.getChoices().toArray());
			assertEquals(weight,q.grade(correctChoice));
		} catch (InvalidOperationException e) {
			fail("Minimum number of choices boundary may have problems!");
		}
	}
	
	//Corresponds to the Test Case 6 in Matrix
	@Test
	public void test6_WeightMaximumOverflow(){
		Question q = null;
		int validWeight = 10, correctChoice = 2;
		String body = "What is the best university in Lisbon?";
		String[] subjects = {"Education"};
		String[] validChoices = {"FCUL","IST","ESSA","ISCTE","ISEL"};
		try{
			q = new Question(body, Arrays.asList(validChoices), correctChoice, subjects[0], validWeight);
		}catch(InvalidOperationException e){
			fail("Valid initial state not allowed");
		}
		try{
			q.setWeight(MAXIMUM_WEIGHT + 1);
			fail("Maximum weight boundary may have problems!");
		}catch(InvalidOperationException e) {
			assertEquals(subjects,q.getSubjects().toArray());
			assertEquals(validWeight,q.getWeight());
			assertEquals(validChoices,q.getChoices().toArray());
			assertEquals(validWeight,q.grade(correctChoice));
		}
		
	}
	
	//Corresponds to the Test Case 10 in Matrix
	@Test
	public void test10_TryQuestionWithNoSubjects(){
		Question q = null;
		int validWeight = 6, correctChoice = 3;
		String body = "What is the best country in the world?";
		// 1 Subject (MIN = 1)
		String[] subjects = {"Geography"};
		String[] validChoices = {"Spain","Netherlands","Portugal","Italia"};
		try{
			q = new Question(body, Arrays.asList(validChoices), correctChoice, subjects[0], validWeight);
		}catch(InvalidOperationException e){
			fail("Valid initial state not allowed");
		}
		try{
			q.remove(subjects[0]);
			fail("Minimum number of subjects boundary may have problems!");
		}catch(InvalidOperationException e) {
			assertEquals(validChoices,q.getChoices().toArray());
			assertEquals(subjects,q.getSubjects().toArray());
			assertEquals(validWeight,q.getWeight());
			assertEquals(validWeight,q.grade(correctChoice));
		}
	}
	
	//Corresponds to the Test Case 12 in Matrix
	@Test
	public void test12_MaximumSubjectsOverflow() {
		Question q = null;
		int validWeight = 8, correctChoice = 4;
		String body = "What is the largest animal in the world (in the present)?";
		String[] subjects = {"Biology","Animals","Science","Animal Kingdom"};
		String[] validChoices = {"Ant","Dog","Salmon","Blue Whale","Bald Eagle","Anaconda","Bat"};
		try {
			q = new Question(body, Arrays.asList(validChoices), correctChoice, subjects[0], validWeight);
			q.add(subjects[1]);
			q.add(subjects[2]);
		} catch (InvalidOperationException e) {
			fail("Valid initial state not allowed");
		}
		try{
			q.add(subjects[3]);
			fail("Maximum number of subjects boundary may have problems!");
		}catch(InvalidOperationException e){
			assertEquals(validWeight,q.grade(correctChoice));
			assertEquals(validChoices,q.getChoices().toArray());
			assertEquals(subjects,q.getSubjects().toArray());
			assertEquals(validWeight,q.getWeight());
		}
	}
	
	
}
