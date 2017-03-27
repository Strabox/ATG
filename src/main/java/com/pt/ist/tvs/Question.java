package com.pt.ist.tvs;

import java.util.List;

public class Question {
	public Question(String body, List<String> choices, int correctChoice, String subject,
	int weight) throws InvalidOperationException { /* .... */ }
	
	// Removes a subject
	public void remove(String subject) throws InvalidOperationException { /* .... */ }
	
	// Adds a new subject
	public void add(String subject) throws InvalidOperationException { /* .... */ }
	
	// Returns all subjects of this question
	public List<String> getSubjects() { /* .... */ return null;}
	
	// Computes the grade considering the weight of this question and the selected choice
	public float grade(int selectedChoice) { /* .... */ return 0;}
	
	// Changes the weight og this question
	public void setWeight(int weight) throws InvalidOperationException { /* .... */ }
	
	// Returns the weight of this question
	public int getWeight() { /* .... */ return 0;}
}
