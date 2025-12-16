package com.wipro.candidate.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.DataIncorrectException;

public class CandidateMain {

    public String addCandidate(CandidateBean candBean) {
        try {
            if (candBean == null
                || candBean.getName() == null
                || candBean.getName().trim().isEmpty()
                || candBean.getName().trim().length() < 2
                || candBean.getM1() < 0 || candBean.getM1() > 100
                || candBean.getM2() < 0 || candBean.getM2() > 100
                || candBean.getM3() < 0 || candBean.getM3() > 100) {
                throw new DataIncorrectException();
            }

            CandidateDAO dao = new CandidateDAO();
            String id = dao.generateCandidateId(candBean.getName());
            if (id == null) return "Error"; // sequence failure or cap exceeded
            candBean.setId(id);

            int total = candBean.getM1() + candBean.getM2() + candBean.getM3();
            if (total >= 240) { candBean.setResult("PASS"); candBean.setGrade("Distinction"); }
            else if (total >= 180) { candBean.setResult("PASS"); candBean.setGrade("First Class"); }
            else if (total >= 150) { candBean.setResult("PASS"); candBean.setGrade("Second Class"); }
            else if (total >= 105) { candBean.setResult("PASS"); candBean.setGrade("Third Class"); }
            else { candBean.setResult("FAIL"); candBean.setGrade("No Grade"); }

            String status = dao.addCandidate(candBean);
            return status.equals("SUCCESS") ? candBean.getId() + ":" + candBean.getResult() : "Error";

        } catch (DataIncorrectException e) {
            return e.toString();
        }
    }

    public ArrayList<CandidateBean> displayAll(String criteria) {
        try {
            if (!(criteria.equalsIgnoreCase("PASS")
               || criteria.equalsIgnoreCase("FAIL")
               || criteria.equalsIgnoreCase("ALL"))) {
                throw new DataIncorrectException();
            }
            CandidateDAO dao = new CandidateDAO();
            return dao.getByResult(criteria);
        } catch (DataIncorrectException e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        CandidateMain candidateMain = new CandidateMain();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Candidate Management System ===");
        System.out.println("1. Add Candidate");
        System.out.println("2. Display Candidates by Result");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        if (choice == 1) {
            CandidateBean cb = new CandidateBean();

            System.out.print("Enter Candidate Name: ");
            cb.setName(sc.nextLine());

            System.out.print("Enter marks in Subject 1: ");
            cb.setM1(sc.nextInt());

            System.out.print("Enter marks in Subject 2: ");
            cb.setM2(sc.nextInt());

            System.out.print("Enter marks in Subject 3: ");
            cb.setM3(sc.nextInt());

            String result = candidateMain.addCandidate(cb);
            System.out.println("Result: " + result);

        } else if (choice == 2) {
            System.out.print("Enter criteria (PASS/FAIL/ALL): ");
            String criteria = sc.nextLine();

            ArrayList<CandidateBean> list = candidateMain.displayAll(criteria);
            if (list == null) {
                System.out.println("No records found or Data Incorrect");
            } else {
                for (CandidateBean c : list) {
                    System.out.println(c.getId() + " | " + c.getName() + " | "
                        + c.getM1() + "," + c.getM2() + "," + c.getM3()
                        + " | " + c.getResult() + " | " + c.getGrade());
                }
            }
        }
        sc.close();
    }
}
