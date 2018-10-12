package com.neo.dataHandle;


import com.neo.entity.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class TableHandle {
    private String studentId;
    private String name;
    private String course;
    private String teacher;
    private int starWeek;
    private int endWeek;
    private int oneTow;  //单双周 1为单周，2为双周
    private String classRoom;
    //课程定位坐标 X:week ;Y:section ,sectionSpan
    private int week; //周几
    private int section; //从第几节课开始
    private int sectionSpan; //跨几节课
    private StringUnit stringUnit =new StringUnit();


    public List<TimeTable> handle(String user, List<TableData> tableData){
        List<String> list = stringUnit.StringHandle(user);
        List<TimeTable> timeTableList = new ArrayList<>();
        studentId = list.get(1);
        name = list.get(0);
        for (TableData e:tableData) {
          List<String> list1 = stringUnit.StringHandle(e.getContent());
            if (list1.size() <5) {
                course = list1.get(0);
                teacher = list1.get(2);
                Week myweek = stringUnit.weekHandle(list1.get(3));
                starWeek = myweek.getStarWeek();
                endWeek = myweek.getEndWeek();
                oneTow = myweek.getOneTow();
                classRoom = list1.get(3);
                Location location = stringUnit.handle(e.getLocationId());
                week = location.getX();
                section = location.getY();
                sectionSpan = e.getRowspan();
                timeTableList.add(new TimeTable(studentId,name,course,teacher,starWeek,endWeek,oneTow,classRoom
                ,week,section,sectionSpan));
            }else {
                List<String> list2 = stringUnit.StringHandle(e.getContent());
                course = list2.get(0);
                teacher = list2.get(2);
                Week myweek1 = stringUnit.weekHandle(list2.get(3));
                starWeek = myweek1.getStarWeek();
                endWeek = myweek1.getEndWeek();
                oneTow = myweek1.getOneTow();
                classRoom = list2.get(3);
                Location location = stringUnit.handle(e.getLocationId());
                week = location.getX();
                section = location.getY();
                sectionSpan = e.getRowspan();
                timeTableList.add(new TimeTable(studentId,name,course,teacher,starWeek,endWeek,oneTow,classRoom
                        ,week,section,sectionSpan));

                course = list2.get(4);
                teacher = list2.get(6);
                Week myweek2 = stringUnit.weekHandle(list2.get(7));
                starWeek = myweek2.getStarWeek();
                endWeek = myweek2.getEndWeek();
                oneTow = myweek2.getOneTow();
                classRoom = list2.get(7);
                Location location1 = stringUnit.handle(e.getLocationId());
                week = location1.getX();
                section = location1.getY();
                sectionSpan = e.getRowspan();
                timeTableList.add(new TimeTable(studentId,name,course,teacher,starWeek,endWeek,oneTow,classRoom
                        ,week,section,sectionSpan));
            }

        }
        return timeTableList;
    }

}
