package com.example.Kwiaty;


public class Flower {


   String rodzaj, gatunek, region, pokroj, typ, stanowisko, temp, kolor, foto;
   long id;

   public Flower(long id, String rodzaj,String gatunek,String region,String pokroj,String typ,String stanowisko,String temp,String kolor, String foto)
     {
        this.id = id;
        this.rodzaj = rodzaj;
        this.gatunek = gatunek;
        this.region = region;
        this.pokroj = pokroj;
        this.typ = typ;
        this.stanowisko = stanowisko;
        this.temp = temp;
        this.kolor = kolor;
        this.foto = foto;
     }

    @Override
    public String toString()
    {
          return rodzaj+" "+gatunek;
    }


    public String getValues()
     {
         String s;

         s="Region: "+region+"\nTyp rośliny:"+typ+"\nPokrój: "+pokroj+"\nKolor kwiatów: "+kolor+"\nWarunki cieplne: "+temp+"\nStanowisko: "+stanowisko;

         return s;

     }

    public String getFoto()
       {
         return foto;
       }


}
