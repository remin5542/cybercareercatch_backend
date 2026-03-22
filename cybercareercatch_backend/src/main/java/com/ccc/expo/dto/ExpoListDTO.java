package com.ccc.expo.dto;

public class ExpoListDTO {
      private int expoNumber;
      private int expoName;
      private String startDate;
      private String endDate;
      private String location;
      private String regDate;
      public int getExpoNumber() {
         return expoNumber;
      }
      public void setExpoNumber(int expoNumber) {
         this.expoNumber = expoNumber;
      }
      public int getExpoName() {
         return expoName;
      }
      public void setExpoName(int expoName) {
         this.expoName = expoName;
      }
      public String getStartDate() {
         return startDate;
      }
      public void setStartDate(String startDate) {
         this.startDate = startDate;
      }
      public String getEndDate() {
         return endDate;
      }
      public void setEndDate(String endDate) {
         this.endDate = endDate;
      }
      public String getLocation() {
         return location;
      }
      public void setLocation(String location) {
         this.location = location;
      }
      public String getRegDate() {
         return regDate;
      }
      public void setRegDate(String regDate) {
         this.regDate = regDate;
      }
      
      
      @Override
      public String toString() {
      return "ExpoListDTO [expoNumber=" + expoNumber + ", expoName=" + expoName + ", startDate=" + startDate
            + ", endDate=" + endDate + ", location=" + location + ", regDate=" + regDate + "]";
      }
      
      
      
}
