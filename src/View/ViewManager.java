/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.io.IOException;

/**
 *
 * @author PC
 */
public class ViewManager {

    public static PatientLoginPage patientLoginPage;
    public static PatientRegPage patientRegPage;
    public static PatientDashboardPage patientDashboardPage;
    public static AdminLoginPage adminLoginPage;
    public static AdminDashboardPage adminDashboardPage;

    private ViewManager() {
    }

    public static void openPatientLoginPage() throws IOException {
        if (patientLoginPage == null) {
            patientLoginPage = new PatientLoginPage();
            patientLoginPage.show();
        } else {
            patientLoginPage.show();
        }
    }

    public static void closePatientLoginPage() {
        if (patientLoginPage != null) {
            patientLoginPage.close();
        }
    }

    public static void openPatientRegPage() throws IOException {
        if (patientRegPage == null) {
            patientRegPage = new PatientRegPage();
            patientRegPage.show();
        } else {
            patientRegPage.show();
        }
    }

    public static void closePatientRegPage() {
        if (patientRegPage != null) {
            patientRegPage.close();
        }
    }

    public static void openAdminLoginPage() throws IOException {
        if (adminLoginPage == null) {
            adminLoginPage = new AdminLoginPage();
            adminLoginPage.show();
        } else {
            adminLoginPage.show();
        }
    }

    public static void closeAdminLoginPage() {
        if (adminLoginPage != null) {
            adminLoginPage.close();
        }
    }

    public static void openAdminDashboardPage() throws IOException {
        if (adminDashboardPage == null) {
            adminDashboardPage = new AdminDashboardPage();
            adminDashboardPage.show();
        } else {
            adminDashboardPage.show();
        }
    }

    public static void closeAdminDashboardPage() {
        if (adminDashboardPage != null) {
            adminDashboardPage.close();
        }
    }

    public static void openPatientDashboardPage() throws IOException {
        if (patientDashboardPage == null) {
            patientDashboardPage = new PatientDashboardPage();
            patientDashboardPage.show();
        } else {
            patientDashboardPage.show();
        }
    }

    public static void closePatientDashboardPage() {
        if (patientDashboardPage != null) {
            patientDashboardPage.close();
        }
    }

}
