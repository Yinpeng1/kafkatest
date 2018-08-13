package com.yp.kafkatest.controller;

import com.yp.kafkatest.annotation.coming;
import com.yp.kafkatest.aop.ScanYP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello")
public class TestController extends HttpServlet {

    @coming
    private Test test;

    public String getName(int id) {
        test.getName(111);
        System.out.println("-----get name-----");
        return "tom";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自己写的方法");
        TestController testController = (TestController) ScanYP.map.get(TestController.class);
        testController.getName(Integer.valueOf(req.getParameter("id")));
//        getName(Integer.valueOf(req.getParameter("id")));
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
