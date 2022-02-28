package com.example.essentialallinone.utility;


import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Fecha
{
  private Calendar calendario1 = Calendar.getInstance();
  private Calendar calendario2 = Calendar.getInstance();
    private Calendar calendario3 = Calendar.getInstance();
  private Date date;
  private String fehaHoy;


  public String getFehaHoy()
  {
      date = new Date();
      int agno = (date.getYear()-100)+2000;
      int mes = date.getMonth();
      int dia = date.getDate();
      int hora = date.getHours();
      int minutos = date.getMinutes();

      fehaHoy = agno+":" + mes +":" + dia + ":"+ hora + ":"+ minutos;

      return  fehaHoy;
  }

  public long getHoras(String fecha)
  {
      String[] fechaArr = fecha.split(":");
      int agno = Integer.parseInt(fechaArr[0]);
      int mes = Integer.parseInt(fechaArr[1]);
      int dia = Integer.parseInt(fechaArr[2]);
      int horas = Integer.parseInt(fechaArr[3]);
      int minutos = Integer.parseInt(fechaArr[4]);

      calendario2.set(agno,mes,dia,horas,minutos);
      date = calendario2.getTime();
      long diferencia = Math.abs(calendario1.getTimeInMillis()-date.getTime());
      long tiempo = TimeUnit.MILLISECONDS.toHours(diferencia);
      return  tiempo;
  }


  public String fechaFutura(float horas,String fechaInicio)
  {
      Date fechaIni = new Date();
      calendario2.set(2021,4,15,00,00);
      date = calendario2.getTime();

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date); // Configuramos la fecha que se recibe
      calendar.add(Calendar.DAY_OF_YEAR, (int)horas);  // numero de horas a añadir, o restar en caso de horas<0
      long years= (calendar.getTime().getYear()-100)+2000;
      long mes= calendar.getTime().getMonth();
      long dia= calendar.getTime().getDate();
      long hours= calendar.getTime().getTime();
      long minutos= calendar.getTime().getMinutes();
      String fechaFutura = dia+"/"+mes+"/"+years;

      return  fechaFutura;
  }
}
