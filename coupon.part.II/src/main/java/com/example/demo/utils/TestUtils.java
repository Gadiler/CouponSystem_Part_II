package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class TestUtils {
    private StringBuilder sb;

    public void printTestHeader(String header) {
        sb = new StringBuilder();
        sb.append("----------------------- ").append(header).append(" ---------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        sb.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(sb);
    }
}
