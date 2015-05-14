/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.form;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class AddGradeForm {
    private int studentId;
    private int studentGrade;

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the studentGrade
     */
    public int getStudentGrade() {
        return studentGrade;
    }

    /**
     * @param studentGrade the studentGrade to set
     */
    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }
    
}
