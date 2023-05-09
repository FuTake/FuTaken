package com.file.controller;

import java.util.*;

class Booking{
    private String name;
    private int time;
    private Map<Integer, String> schedule = new HashMap<>();
    List<Integer> timetable = new ArrayList<>();

//    public Booking(String name, int time){
//        this.name = name;
//        this.time = time;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void appointment(String name, int time) {
        this.name = name;
        this.time = time;
        for (int i = 0; i < timetable.size(); i++){
            if(time == timetable.get(i)){
                System.out.println("Hi " + name + ", your appointment is confirmed at " + time +" PM");
                schedule.put(time,name);
                timetable.remove(timetable.get(i));
                break;
            }else{
                System.out.println("Slot Not Available, please choose another slot");
                break;
            }
        }
//        Iterator<Integer> iter = timetable.iterator();
//        while (iter.hasNext()) {
//            int item = iter.next();
//            if (item == time) {
//                System.out.println("Hi " + name + ", your appointment is confirmed at " + time +" PM");
//                schedule.put(time,name);
//                iter.remove();
//                break;
//            } else{
//                System.out.println("Slot Not Available, please choose another slot");
//                break;
//            }
//        }
    }
    public void printSchedule(){
        for(int key : schedule.keySet()){
            System.out.println(key + "-->" + schedule.get(key));
        }
    }

    public int getSize(){
        return schedule.size();
    }

}
class Appointment {
    public static void main(String[] args){
        Map<Integer, String> maps = new HashMap<>();
        maps.put(1, null);
        System.out.println(maps.values().size());
//        Booking b = new Booking();
//        int i = 0;
//        while (b.getSize()<3){
//            Scanner sc = new Scanner(System.in);
//            System.out.print("Enter Name:");
//            String name = sc.nextLine();
////        b.setName(name);
//            System.out.print("Enter time:");
//            int time = Integer.parseInt(sc.nextLine());
////        b.setTime(time);
//            b.appointment(name,time);
//        }
//        b.printSchedule();
    }
}
