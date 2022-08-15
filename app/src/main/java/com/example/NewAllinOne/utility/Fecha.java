package com.example.NewAllinOne.utility;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class Fecha
{
  private static Calendar calendario1 = Calendar.getInstance();
  private static Calendar calendario2 = Calendar.getInstance();
  private static Date date;
  private static Date dateInit= new Date();
  private static Date dateFinal= new Date();

  public static String getFehaHoy()
  {
      String fehaHoy;
      date = new Date();
      int agno = (date.getYear()-100)+2000;
      int mes = date.getMonth();
      int dia = date.getDate();
      int hora = date.getHours();
      int minutos = date.getMinutes();

      fehaHoy = agno+":" + mes +":" + dia + ":"+ hora + ":"+ minutos;

      return  fehaHoy;
  }

  public static long getHoras(String f)
  {
      calendario1  = Calendar.getInstance();
      calendario2  = Calendar.getInstance();
      String[] fechaArr = f.split(":");
      int agno = Integer.parseInt(fechaArr[0]);
      int mes = Integer.parseInt(fechaArr[1]);
      int dia = Integer.parseInt(fechaArr[2]);
      int horas = Integer.parseInt(fechaArr[3]);
      int minut = Integer.parseInt(fechaArr[4]);

      calendario2.set(agno,mes,dia,horas,minut);

      date = calendario2.getTime();
      long dif = Math.abs(calendario1.getTimeInMillis()-date.getTime());
      long t = TimeUnit.MILLISECONDS.toHours(dif);
      return  t;
  }

  public static long getMinutos(String fechaInicial, FechaInterna fechaFinal)
    {
        FechaInterna fechaInic = new FechaInterna();
        String[] fechaIn = fechaInicial.split(":");
        fechaInic.setYear(Integer.parseInt(fechaIn[0]));
        fechaInic.setMonth(Integer.parseInt(fechaIn[1]));
        fechaInic.setDay(Integer.parseInt(fechaIn[2]));
        fechaInic.setHour(Integer.parseInt(fechaIn[3]));
        fechaInic.setMinute(Integer.parseInt(fechaIn[4]));

        calendario2.set(fechaInic.getYear(),fechaInic.getMonth(),fechaInic.getDay(),fechaInic.getHour(),fechaInic.getMinute());


        calendario1.set(fechaFinal.getYear(),fechaFinal.getMonth(),fechaFinal.getDay(),fechaFinal.getHour(),fechaFinal.getMinute());

        dateInit = calendario2.getTime();
        dateFinal = calendario1.getTime();

        long diferencia = ( calendario1.getTimeInMillis()-dateInit.getTime());
        long tiempo = TimeUnit.MILLISECONDS.toMinutes(diferencia);
        return  tiempo;
    }

  public static FechaInterna fechaFutura(float horas,String fHoy)
  {
      String[] fechaIni = fHoy.split(":");
      FechaInterna fechaInterna = new FechaInterna();

      fechaInterna.setYear (Integer.parseInt(fechaIni[0]));
      fechaInterna.setMonth(Integer.parseInt(fechaIni[1]));
      fechaInterna.setDay(Integer.parseInt(fechaIni[2]));
      fechaInterna.setHour(Integer.parseInt(fechaIni[3]));
      fechaInterna.setMinute(Integer.parseInt(fechaIni[4]));


      calendario2.set(fechaInterna.getYear(),fechaInterna.getMonth(),fechaInterna.getDay(),fechaInterna.getHour(),fechaInterna.getMinute());
      date = calendario2.getTime();

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date); // Configuramos la fecha que se recibe
      calendar.add(Calendar.HOUR_OF_DAY, (int)horas);  // numero de horas a añadir, o restar en caso de horas<0

      fechaInterna.setYear ((calendar.getTime().getYear()-100)+2000);
      fechaInterna.setMonth(calendar.getTime().getMonth());
      fechaInterna.setDay(calendar.getTime().getDate());
      fechaInterna.setHour(calendar.getTime().getHours());
      fechaInterna.setMinute(calendar.getTime().getMinutes());


      return  fechaInterna;
  }

  public static class  FechaInterna
  {
      private int year =0;
      private int month =0;
      private int day= 0;
      private int hour =0;
      private int minute =0;

      public FechaInterna() {
      }

      public int getYear() {
          return year;
      }

      public void setYear(int year) {
          this.year = year;
      }

      public int getMonth() {
          return month;
      }

      public void setMonth(int month) {
          this.month = month;
      }

      public int getDay() {
          return day;
      }

      public void setDay(int day) {
          this.day = day;
      }

      public int getHour() {
          return hour;
      }

      public void setHour(int hour) {
          this.hour = hour;
      }

      public int getMinute() {
          return minute;
      }

      public void setMinute(int minute) {
          this.minute = minute;
      }
  }



}
